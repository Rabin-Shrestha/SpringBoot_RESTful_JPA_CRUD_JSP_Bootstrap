package com.example.SpringBootDemo.service;

import com.example.SpringBootDemo.dao.StudentRepository;
import com.example.SpringBootDemo.errorHandler.StudentNotFoundException;
import com.example.SpringBootDemo.model.Student;
import com.example.SpringBootDemo.util.DataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements IService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private DataLoader dataLoader;

    private Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public List<Student> findAll() {
        List<Student> result = repository.findAll();
        return result;
    }

    public Student addNewStudent(Student newStudent) {
        return repository.save(newStudent);
    }

    public Student findOneStudentId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public List<Student> findBySearchCriteria(Student searchCriteria) {
        // Goal is to return Union of various search result based on criteria
        List<Student> result = findAll();
        if (searchCriteria.getName() == null
                && searchCriteria.getCampus() == null
                && searchCriteria.getGradeLevel() == null
                && searchCriteria.getSchoolYear() == null)
            return result;

        HashMap<Long, Student> studentHashMap = new HashMap<>();
        System.out.println("search Criteria" + searchCriteria);
        if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) {
            repository
                    .findByNameContainingIgnoreCase(searchCriteria.getName())
                    .forEach(s -> studentHashMap.put(s.getStudentId(), s));

        } else if (searchCriteria.getCampus() != null) {
            result.stream()
                    .filter(a -> a.getCampus().equals(searchCriteria.getCampus()))
                    .forEach(s -> studentHashMap.put(s.getStudentId(), s));

        } else if (searchCriteria.getGradeLevel() != null) {
            result.stream()
                    .filter(a -> a.getGradeLevel().equals(searchCriteria.getGradeLevel()))
                    .forEach(s -> studentHashMap.put(s.getStudentId(), s));

        } else if (searchCriteria.getSchoolYear() != null) {
            result.stream()
                    .filter(a -> a.getSchoolYear().equals(searchCriteria.getSchoolYear()))
                    .forEach(s -> studentHashMap.put(s.getStudentId(), s));
        }
        return new ArrayList<Student>(studentHashMap.values());
    }

    private List<Student> priorityBasedSearch(Student searchCriteria) {
        List<Student> result = new ArrayList<>();
        if (searchCriteria.getName() != null || !searchCriteria.getName().isEmpty()) {
            result = repository.findByNameContainingIgnoreCase(searchCriteria.getName());
        } else if (searchCriteria.getCampus() != null) {
            result = repository.findByCampusEquals(searchCriteria.getCampus());
        } else if (searchCriteria.getGradeLevel() != null) {
            result = repository.findByGradeLevelEquals(searchCriteria.getGradeLevel());
        } else if (searchCriteria.getSchoolYear() != null) {
            result = repository.findBySchoolYearEquals(searchCriteria.getSchoolYear());
        }
        return result;
    }

    public Student saveOrUpdate(Student newStudent, Long id) {
        if (null != id) {
            Student student = repository.getOne(id);
            if (student != null) {
                student.setSchoolYear(newStudent.getSchoolYear());
                student.setCampus(newStudent.getCampus());
                student.setEntryDate(newStudent.getEntryDate());
                student.setGradeLevel(newStudent.getGradeLevel());
                student.setName(newStudent.getName());
                return repository.save(student);
            }
        }
        return repository.save(newStudent);
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    public void loadInitialDataFromFile(String fileName) {
        try {
            List<Student> students = dataLoader.loadStudentRecords(fileName);
            students.stream().peek(s -> System.out.println("saving" + s)).forEach(s -> repository.save(s));
        } catch (Exception ex) {
            logger.error("Could not load the student record in system: " + ex.getMessage());
        }
    }

    private Date toSqlDate(String rawDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return Date.valueOf(LocalDate.parse(rawDate, formatter));
    }
}

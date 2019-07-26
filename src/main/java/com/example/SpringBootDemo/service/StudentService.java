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
        System.out.println("The Search Result is" + result);
        return result;
    }

    @Override
    public List<Student> search() {
        return null;
    }

    public Student addNewStudent(Student newStudent) {
        return repository.save(newStudent);
    }

    public Student findOneStudentId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public List<Student> findBySearchCriteria(Student searchCriteria) {
        // Minimizing the database hit
        List<Student> result = findAll();
        if (null == searchCriteria) {
            return result;
        }
        if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) {
            result = repository.findByNameContainingIgnoreCase(searchCriteria.getName());
            //result = result.stream().filter(a -> a.getName().toLowerCase().equals(searchCriteria.getName())).collect(Collectors.toList());
            System.out.println("inside name" + result);
        } else if (searchCriteria.getCampus() != null) {
            result = result.stream().filter(a -> a.getCampus().equals(searchCriteria.getCampus())).collect(Collectors.toList());
            System.out.println("inside campus" + result);

        } else if (searchCriteria.getGradeLevel() != null) {
            result = result.stream().filter(a -> a.getGradeLevel().equals(searchCriteria.getGradeLevel())).collect(Collectors.toList());
            System.out.println("inside gradeLevel" + result);

        } else if (searchCriteria.getSchoolYear() != null) {
            result = result.stream().filter(a -> a.getSchoolYear().equals(searchCriteria.getSchoolYear())).collect(Collectors.toList());
            System.out.println("inside Year" + result);

        }
        return result;
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

    public Student update(Student newStudent,Long id) {

        if (newStudent.getStudentId() != null) {
            Student student = repository.getOne(id);
            if (null != student) {
                student.setStudentId(id);
                student.setSchoolYear(newStudent.getSchoolYear());
                student.setCampus(newStudent.getCampus());
                student.setStudentId(newStudent.getStudentId());
                student.setEntryDate(newStudent.getEntryDate());
                student.setGradeLevel(newStudent.getGradeLevel());
                student.setName(newStudent.getName());
                return repository.save(student);
            }
        }
        return repository.save(newStudent);
    }
    public Student save(Student newStudent)
    {
        return repository.save(newStudent);
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    public void loadInitialDataFromFile(String fileName) {
        try {
            repository.save(new Student(2010, 999, toSqlDate("02/12/1993"), 10, "Rabin Shrestha"));
            repository.save(new Student(2010, 888, toSqlDate("02/12/1993"), 10, "Alina Shrestha"));
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

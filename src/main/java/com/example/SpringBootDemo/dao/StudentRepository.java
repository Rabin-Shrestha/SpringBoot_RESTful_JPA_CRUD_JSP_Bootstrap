package com.example.SpringBootDemo.dao;

import com.example.SpringBootDemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);
    List<Student> findByCampusEquals(Integer campus);
    List<Student> findByGradeLevelEquals(Integer gradeLevel);
    List<Student> findBySchoolYearEquals(Integer schoolYear);

}

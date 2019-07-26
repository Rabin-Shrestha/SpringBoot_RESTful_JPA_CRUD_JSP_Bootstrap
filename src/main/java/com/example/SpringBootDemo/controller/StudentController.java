package com.example.SpringBootDemo.controller;

import com.example.SpringBootDemo.model.Student;
import com.example.SpringBootDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("index");
        List<Student> allStudents = loadAll();
        model.addObject("list", allStudents);
        return model;
    }

    @GetMapping("/student")
    List<Student> loadAll() {
        return studentService.findAll();
    }

    @PostMapping("/student/search")
    ModelAndView findAll(Student searchCriteria) {
        System.out.println("This is the search Criteria Object:" + searchCriteria);
        ModelAndView model = new ModelAndView("index");
        List<Student> result = studentService.findBySearchCriteria(searchCriteria);
        System.out.println("The search with critetia result is :" + result);
        model.addObject("list", result);
        return model;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    ModelAndView addNewStudent(@ModelAttribute("addStudent") Student newStudent) {
        System.out.println("New student records is: "+newStudent);
        studentService.save(newStudent);
        return home();
    }

    @RequestMapping(value = "/student/edit/{id}", method = RequestMethod.POST)
    ModelAndView updateStudentRecord(@ModelAttribute("editStudent") Student newStudent,@PathVariable("id") Long id) {
        System.out.println("Updated student records is: "+newStudent);
        studentService.update(newStudent,id);
        return home();
    }

    @GetMapping("/student/{id}")
    Student findStudent(@PathVariable Long id) {
        return studentService.findOneStudentId(id);
    }

    @RequestMapping("/student/delete")
    ModelAndView deleteStudent(@RequestParam String studentId) {
        System.out.println("inside delete" + studentId);
        studentService.deleteStudent(Long.valueOf(studentId));
        return home();
    }

    @RequestMapping(value = "/student/edit")
    ModelAndView editStudent(@RequestParam String studentId) {
        System.out.println("inside edit for std id:" + studentId);
        ModelAndView model = new ModelAndView("edit");
        Student student = studentService.findOneStudentId(Long.valueOf(studentId));
        model.addObject("student", student);
        return model;
    }
    @RequestMapping(value = "/student/add", method = RequestMethod.GET)
    ModelAndView addStudent() {
        System.out.println("inside addStudent ");
        ModelAndView model = new ModelAndView("addStudent");
        return model;
    }


}

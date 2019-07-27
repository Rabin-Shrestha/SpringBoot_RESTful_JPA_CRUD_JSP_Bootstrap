package com.example.SpringBootDemo.controller;

import com.example.SpringBootDemo.model.Student;
import com.example.SpringBootDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("index");
        List<Student> allStudents = studentService.findAll();
        model.addObject("list", allStudents);
        return model;
    }

    @PostMapping("/student/search")
    ModelAndView findAll(Student searchCriteria) {
        ModelAndView model = new ModelAndView("index");
        List<Student> result = studentService.findBySearchCriteria(searchCriteria);
        model.addObject("list", result);
        return model;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    ModelAndView addNewStudent(@ModelAttribute("addStudent") Student newStudent) {
        studentService.saveOrUpdate(newStudent, null);
        return home();
    }

    @RequestMapping(value = "/student/edit/{id}", method = RequestMethod.POST)
    ModelAndView updateStudentRecord(@ModelAttribute("editStudent") Student newStudent, @PathVariable("id") Long id) {
        studentService.saveOrUpdate(newStudent, id);
        return home();
    }

    @RequestMapping("/student/delete")
    ModelAndView deleteStudent(@RequestParam String studentId) {
        studentService.deleteStudent(Long.valueOf(studentId));
        return home();
    }

    @RequestMapping(value = "/student/edit")
    ModelAndView editStudent(@RequestParam String studentId) {
        ModelAndView model = new ModelAndView("edit");
        Student student = studentService.findOneStudentId(Long.valueOf(studentId));
        model.addObject("student", student);
        return model;
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.GET)
    ModelAndView addStudent() {
        ModelAndView model = new ModelAndView("addStudent");
        return model;
    }
}

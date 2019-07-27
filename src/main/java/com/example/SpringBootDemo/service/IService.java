package com.example.SpringBootDemo.service;

import com.example.SpringBootDemo.model.Student;

import java.util.List;

public interface IService {
    List<Student> findAll();
}

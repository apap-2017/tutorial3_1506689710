package com.example.demo.service;

import com.example.demo.model.StudentModel;

import java.util.List;

public interface StudentService
{
    StudentModel selectStudent(String npm);
    List<StudentModel> selectAllStudents();
    void addStudent(StudentModel student);
    void removeStudent(StudentModel student);
}
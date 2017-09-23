package com.example.demo.service;

import com.example.demo.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStudentService implements StudentService
{
    private static List<StudentModel> studentList = new ArrayList<StudentModel>();

    @Override
    public StudentModel selectStudent(String npm) {
        StudentModel result = null;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getNpm().equals(npm))
            {
                result = studentList.get(i);
                i = studentList.size();
            }
        }
        return result;
    }

    @Override
    public List<StudentModel> selectAllStudents() {
        return studentList;
    }

    @Override
    public void addStudent(StudentModel student) {
        studentList.add(student);
    }

    @Override
    public void removeStudent(StudentModel student) {
        studentList.remove(student);
    }
}

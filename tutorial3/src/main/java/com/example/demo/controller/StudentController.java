package com.example.demo.controller;

import com.example.demo.model.StudentModel;
import com.example.demo.service.InMemoryStudentService;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController
{
    private final StudentService studentService;

    public StudentController()
    {
        studentService = new InMemoryStudentService();
    }

    @RequestMapping("student/add")
    public String add(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "npm", required = true) String npm,
            @RequestParam(value = "gpa", required = true) double gpa
            )
    {
        StudentModel student = new StudentModel(name,npm, gpa);
        studentService.addStudent(student);
        return "add";
    }

    //Terjadi error ambigouity jika tidak ditutup/dihapus
    /*
    @RequestMapping("/student/view")
    public String view(Model model,
                       @RequestParam(value = "npm", required = true) String npm)
    {
        StudentModel student = studentService.selectStudent(npm);
        model.addAttribute("student", student);
        return "view";
    }
    */

    @RequestMapping("/student/viewall")
    public String viewAll(Model model)
    {
        List<StudentModel> students = studentService.selectAllStudents();
        model.addAttribute("students", students);
        return "viewall";
    }

    @RequestMapping(value = {"/student/view", "/student/view/{npm}"})
    public String viewStudent(@PathVariable Optional<String> npm, Model model)
    {
        if (npm.isPresent())
        {
            StudentModel student = studentService.selectStudent(npm.get());
            if (student != null)
            {
                model.addAttribute("student", student);
                return "view";
            }
        }
        return "viewerror";
    }

    @RequestMapping(value = {"/student/delete", "/student/delete/{npm}"})
    public String delete(@PathVariable Optional<String> npm, Model model)
    {
        if (npm.isPresent())
        {
            StudentModel student = studentService.selectStudent(npm.get());
            if (student != null)
            {
                studentService.removeStudent(student);
                model.addAttribute("npm", npm.get());
                return "remove";
            }
        }
        return "removeerror";
    }


}

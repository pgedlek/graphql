package com.pgedlek.graphql.controller;

import com.pgedlek.graphql.entity.Student;
import com.pgedlek.graphql.request.CreateStudentRequest;
import com.pgedlek.graphql.response.StudentResponse;
import com.pgedlek.graphql.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("getAll")
    public List<StudentResponse> getAllStudents () {
        List<Student> studentList = studentService.getAllStudents();
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("getFirstNameById/{id}")
    public String getFirstNameById (@PathVariable long id) {
        return studentService.getFirstNameById(id);
    }

    @GetMapping("getLastNameById/{id}")
    public String getLastNameById (@PathVariable long id) {
        return studentService.getLastNameById(id);
    }

    @PostMapping("create")
    public StudentResponse createStudent (@Valid @RequestBody CreateStudentRequest createStudentRequest) {
        Student student = studentService.createStudent(createStudentRequest);

        return new StudentResponse(student);
    }
}


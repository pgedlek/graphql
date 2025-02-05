package com.pgedlek.graphql.query;

import com.pgedlek.graphql.request.SampleRequest;
import com.pgedlek.graphql.response.StudentResponse;
import com.pgedlek.graphql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Query {

    @Autowired
    StudentService studentService;

    @QueryMapping
    public String firstQuery() {
        return "First query";
    }

    @QueryMapping
    public String fullName(@Argument SampleRequest sampleRequest) {
        return sampleRequest.getFirstName() + " " + sampleRequest.getLastName();
    }

    @QueryMapping
    public StudentResponse student(@Argument long id) {
        return new StudentResponse(studentService.getStudentById(id));
    }
}

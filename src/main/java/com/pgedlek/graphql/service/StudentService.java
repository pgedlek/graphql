package com.pgedlek.graphql.service;

import com.pgedlek.graphql.entity.Address;
import com.pgedlek.graphql.entity.Student;
import com.pgedlek.graphql.entity.Subject;
import com.pgedlek.graphql.repository.AddressRepository;
import com.pgedlek.graphql.repository.StudentRepository;
import com.pgedlek.graphql.repository.SubjectRepository;
import com.pgedlek.graphql.request.CreateStudentRequest;
import com.pgedlek.graphql.request.CreateSubjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> getAllStudents () {
        return studentRepository.findAll();
    }

    public String getFirstNameById (long id) {
        return studentRepository.findById(id).get().getFirstName();
    }

    public String getLastNameById (long id) {
        return studentRepository.findById(id).get().getLastName();
    }

    public Student createStudent (CreateStudentRequest createStudentRequest) {
        Student student = new Student(createStudentRequest);

        Address address = new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());

        address = addressRepository.save(address);

        student.setAddress(address);
        student = studentRepository.save(student);

        List<Subject> subjectsList = new ArrayList<Subject>();

        if(createStudentRequest.getSubjectsLearning() != null) {
            for (CreateSubjectRequest createSubjectRequest :
                    createStudentRequest.getSubjectsLearning()) {
                Subject subject = new Subject();
                subject.setSubjectName(createSubjectRequest.getSubjectName());
                subject.setMarksObtained(createSubjectRequest.getMarksObtained());
                subject.setStudent(student);

                subjectsList.add(subject);
            }

            subjectRepository.saveAll(subjectsList);

        }

        student.setLearningSubjects(subjectsList);

        return student;
    }

}


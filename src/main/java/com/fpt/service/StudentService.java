package com.fpt.service;

import com.fpt.entity.Student;
import com.fpt.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    public List<Student> getList() {
        return studentRepository.findAll();
    }
    public Student create(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setCreateAt(Calendar.getInstance().getTimeInMillis());
        student.setUpdateAt(Calendar.getInstance().getTimeInMillis());
        student.setStatus(1);
        return studentRepository.save(student);
    }

    public Student getDetail(long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public Student getByEmail(String email){
        return studentRepository.findByEmail(email);
    }
}

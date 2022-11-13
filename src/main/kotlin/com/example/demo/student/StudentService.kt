package com.example.demo.student

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping

@Service
class StudentService {
    @GetMapping
    fun getStudents(): List<Student> {
        return mutableListOf(Student(1L, "Miriam", "miriam@gmail.com", 14))
    }
}
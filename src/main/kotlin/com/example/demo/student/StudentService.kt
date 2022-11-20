package com.example.demo.student

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping

@Service
class StudentService(private val studentRepository: StudentRepository) {
    @GetMapping
    fun getStudents(): List<Student> {
        return studentRepository.findAll()
    }
}
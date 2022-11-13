package com.example.demo.student

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["api/v1/student"])
class StudentController(studentService: StudentService) {
    private final var studentService: StudentService = studentService

    @GetMapping
    fun getStudents(): List<Student> {
        return studentService.getStudents()
    }
}
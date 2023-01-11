package com.example.demo.student

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["api/v1/student"])
class StudentController(private val studentService: StudentService) {

    @GetMapping
    fun findStudents(): List<Student> {
        return studentService.findStudents()
    }

    @GetMapping("/{id}")
    fun findStudent(@PathVariable id: Long): Student {
        return studentService.findStudentBy(id)
    }

    @PostMapping
    fun addStudent(@RequestBody student: Student) {
        studentService.addStudent(student)
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Long) {
        studentService.deleteStudent(id)
    }

    @PutMapping("/{id}")
    fun updateStudent(@PathVariable  id: Long,
                    @RequestParam(required = false) name: String?,
                    @RequestParam(required = false) email: String?) {
        studentService.updateStudent(id, name, email)
    }
}
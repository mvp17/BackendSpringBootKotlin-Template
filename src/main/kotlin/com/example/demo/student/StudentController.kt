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
    fun getStudents(): List<Student> {
        return studentService.getStudents()
    }

    @PostMapping
    fun registerNewStudent(@RequestBody student: Student) {
        studentService.addNewStudent(student)
    }

    @DeleteMapping(path = ["{studentId}"])
    fun deleteStudent(@PathVariable("studentId") studentId: Long) {
        studentService.deleteStudent(studentId)
    }

    @PutMapping(path = ["{studentId}"])
    fun updateStudent(@PathVariable("studentId") studentId: Long,
                    @RequestParam(required = false) name: String?,
                    @RequestParam(required = false) email: String?) {
        studentService.updateStudent(studentId, name, email)
    }
}
package com.example.demo.student

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.server.ResponseStatusException
import java.util.Objects


@Service
class StudentService(private val studentRepository: StudentRepository) {
    @GetMapping
    fun findStudents(): List<Student> {
        return studentRepository.findAll()
    }

    @GetMapping
    fun findStudentBy(id: Long): Student {
        return studentRepository.findById(id).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found!")}
    }

    fun addStudent(student: Student) {
        val studentEmail: Student? = studentRepository.findStudentByEmail(student.email)
        // If studentEmail isPresent (JAVA)
        if (studentEmail != null)
            throw IllegalStateException("email taken")
        studentRepository.save(student)
    }

    fun deleteStudent(studentId: Long) {
        val exists: Boolean = studentRepository.existsById(studentId)
        if (!exists)
            throw IllegalStateException("Student with id $studentId does not exists.")
        studentRepository.deleteById(studentId)
    }

    // In the update method, if it is needed to provide a body to update the entire object, use @RequestBody
    @Transactional
    fun updateStudent(studentId: Long, name: String?, email: String?) {
        val student: Student = studentRepository.findById(studentId)
            .orElseThrow {
                IllegalStateException("Student with id $studentId does not exist")
            }
        if (!name.isNullOrEmpty() && !Objects.equals(student.name, name))
            student.name = name
        if (!email.isNullOrEmpty() && !Objects.equals(student.email, email)) {
            val studentEmail: Student? = studentRepository.findStudentByEmail(email)
            if (studentEmail != null)
                throw IllegalStateException("email taken")
            student.email = email
        }
    }
}
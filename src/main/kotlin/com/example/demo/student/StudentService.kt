package com.example.demo.student

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import java.util.Objects


@Service
class StudentService(private val studentRepository: StudentRepository) {
    @GetMapping
    fun getStudents(): List<Student> {
        return studentRepository.findAll()
    }

    fun addNewStudent(student: Student) {
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
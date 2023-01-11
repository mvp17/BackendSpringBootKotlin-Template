package com.example.demo.student

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository: JpaRepository<Student, Long> {
    @Query("select s from Student s where s.email = ?1")
    fun findStudentByEmail(email: String): Student?
}
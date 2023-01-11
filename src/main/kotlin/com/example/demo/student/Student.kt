package com.example.demo.student

import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.SequenceGenerator
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Table
data class Student(
    var name: String,
    var email: String,
    val age: Int,
    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    val id: Long,
)

package com.example.demo.student

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StudentConfig {
    @Bean
    fun commandLineRunner(repository: StudentRepository) = CommandLineRunner {
            val miriam = Student("Miriam", "miriam@gmail.com", 14, 0)
            val alex = Student("Alex", "alex@gmail.com", 16, 0)
            val rose = Student("Rose", "rose@hotmail.com", 30, 0)
            repository.saveAll(arrayListOf(miriam, alex, rose))
        }
    }

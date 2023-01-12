package com.example.demo

import com.example.demo.student.Student
import com.example.demo.student.StudentService
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import com.ninjasquad.springmockk.MockkBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest
class WebTierTests(@Autowired val mockMvc: MockMvc) {
    private val url = "/api/v1/student"

    @MockkBean
    private lateinit var service: StudentService

    @Test
    fun `find students`() {
        every { service.findStudents() } returns
                listOf(Student("Miriam", "miriam@gmail.com", 14, 1),
                       Student("Alex", "alex@gmail.com", 16, 2)
                )
        mockMvc.get(url)
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$.[0].name") { value("Miriam") }
                jsonPath("\$.[0].email") { value("miriam@gmail.com") }
                jsonPath("\$.[0].age") { value(14) }
                jsonPath("\$.[0].id") { value(1) }

                jsonPath("\$.[1].name") { value("Alex") }
                jsonPath("\$.[1].email") { value("alex@gmail.com") }
                jsonPath("\$.[1].age") { value(16) }
                jsonPath("\$.[1].id") { value(2) }
            }
        verify(exactly = 1) { service.findStudents() }
    }
}
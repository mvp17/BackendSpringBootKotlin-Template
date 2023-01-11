package com.example.demo

import com.example.demo.student.Student
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.test.web.client.postForObject

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class ApplicationTests (@Autowired val client: TestRestTemplate) {
	val url = "/api/v1/student"
	@Test
	fun `testing if we can post and retrieve the data`() {
		val student = Student("Marc", "m@gmail.com", 25, 0)
		client.postForObject<Student>(url, student)
		val entity = client.getForObject<Student>("$url/4")!! // !! for non null
		assertThat(entity.name).isEqualTo("Marc")
	}

	@Test
	fun `testing if we can update the name of a student`() {
		//val params: MutableMap<String, String> = HashMap()
		//params["name"] = "Jordi"
		val student = Student("Marc", "visa@gmail.com", 25, 0)
		client.postForObject<Student>(url, student)
		val updatedStudent = Student("Jordi", "mvicual@gmail.com", 45, 0)

		client.put("$url/4?name=${updatedStudent.name}", null)
		val entity = client.getForObject<Student>("$url/4")!!
		assertThat(entity.name).isEqualTo(updatedStudent.name)
	}

	@Test
	fun `testing if we can update the email of a student`() {
		val student = Student("Marc", "pascual@gmail.com", 25, 0)
		client.postForObject<Student>(url, student)
		val updatedStudent = Student("Jordi", "sfsdfsdf@gmail.com", 45, 0)

		client.put("$url/4?email=${updatedStudent.email}", null)
		val entity = client.getForObject<Student>("$url/4")!!
		assertThat(entity.email).isEqualTo(updatedStudent.email)
	}

	@Test
	fun `testing if we can update both email and name of a student`() {
		val student = Student("Marc", "rrref@gmail.com", 25, 0)
		client.postForObject<Student>(url, student)
		val updatedStudent = Student("Jordi", "kuykyunh@gmail.com", 45, 0)

		client.put("$url/4?email=${updatedStudent.email}&name=${updatedStudent.name}", null)
		val entity = client.getForObject<Student>("$url/4")!!
		assertThat(entity.email).isEqualTo(updatedStudent.email)
		assertThat(entity.name).isEqualTo(updatedStudent.name)
	}
}

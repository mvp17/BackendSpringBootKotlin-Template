package com.example.demo

import com.example.demo.student.Student
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.test.web.client.postForObject
import org.springframework.http.HttpStatus

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class ApplicationTests (@Autowired val client: TestRestTemplate) {
	private val url = "/api/v1/student"
	@Test
	fun `testing if we can post and retrieve the data`() {
		val id = 4
		val student = Student("Marc", "m@gmail.com", 25, 0)
		client.postForObject<Student>(url, student)
		val entity = client.getForObject<Student>("$url/$id")!! // !! for non null
		assertThat(entity.name).isEqualTo("Marc")
	}

	@Test
	fun `student not found`() {
		val id = 9
		val entity = client.getForEntity<String>("$url/$id")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
	}

	@Test
	fun `testing if we can update the name of a student`() {
		//val params: MutableMap<String, String> = HashMap()
		//params["name"] = "Jordi"
		val id = 4
		val student = Student("Marc", "visa@gmail.com", 25, 0)
		client.postForObject<Student>(url, student)
		val updatedStudent = Student("Jordi", "mvicual@gmail.com", 45, 0)

		client.put("$url/$id?name=${updatedStudent.name}", null)
		val entity = client.getForObject<Student>("$url/$id")!!
		assertThat(entity.name).isEqualTo(updatedStudent.name)
	}

	@Test
	fun `testing if we can update the email of a student`() {
		val id = 4
		val student = Student("Marc", "pascual@gmail.com", 25, 0)
		client.postForObject<Student>(url, student)
		val updatedStudent = Student("Jordi", "sfsdfsdf@gmail.com", 45, 0)

		client.put("$url/$id?email=${updatedStudent.email}", null)
		val entity = client.getForObject<Student>("$url/$id")!!
		assertThat(entity.email).isEqualTo(updatedStudent.email)
	}

	@Test
	fun `testing if we can update both email and name of a student`() {
		val id = 4
		val student = Student("Marc", "rrref@gmail.com", 25, 0)
		client.postForObject<Student>(url, student)
		val updatedStudent = Student("Jordi", "kuykyunh@gmail.com", 45, 0)

		client.put("$url/$id?email=${updatedStudent.email}&name=${updatedStudent.name}", null)
		val entity = client.getForObject<Student>("$url/$id")!!
		assertThat(entity.email).isEqualTo(updatedStudent.email)
		assertThat(entity.name).isEqualTo(updatedStudent.name)
	}

	@Test
	fun `testing if we can delete a student from db`() {
		val id = 3
		client.delete("$url/$id")
		val entity = client.getForEntity<String>("$url/$id")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
	}
}

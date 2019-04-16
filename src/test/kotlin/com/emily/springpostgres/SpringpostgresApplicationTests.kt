package com.emily.springpostgres

import com.emily.springpostgres.model.Teacher
import com.emily.springpostgres.repository.TeacherRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.assertj.core.api.Assertions.*


@ActiveProfiles("test")
@RunWith(SpringRunner::class)
@DataJpaTest
class SpringpostgresApplicationTests {

	@Autowired
	private val entityManager: TestEntityManager? = null

	@Autowired
	private val teacherRepository: TeacherRepository? = null

	@Test
	fun testCreateTeacher() {
		// given
		val testData = hashMapOf(
				"name" to "Alex",
				"Description" to "Description for Alex"
		)
		val alex = Teacher()
		alex.name = testData["name"]!!
		alex.description = testData["Description"]!!
		entityManager!!.persist(alex)
		entityManager.flush()

		// when
		val found = teacherRepository!!.findByName(alex.name)

		// then
		assertThat(found.name).isEqualTo(alex.name)

	}

}

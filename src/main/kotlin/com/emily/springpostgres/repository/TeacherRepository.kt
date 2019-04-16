package com.emily.springpostgres.repository

import com.emily.springpostgres.model.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model

@Repository
interface TeacherRepository : JpaRepository<Teacher, Long> {
    fun findByName(name: String?): Teacher
}

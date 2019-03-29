package com.emily.springpostgres.repository

import com.emily.springpostgres.model.Answer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model

@Repository
interface AnswerRepository : JpaRepository<Answer, Long> {
    fun findByQuestionId(questionId: Long?): List<Answer>
}
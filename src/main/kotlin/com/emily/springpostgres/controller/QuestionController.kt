package com.emily.springpostgres.controller

import com.emily.springpostgres.exception.ResourceNotFoundException
import com.emily.springpostgres.model.Question
import com.emily.springpostgres.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import javax.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class QuestionController {

    @Autowired
    private val questionRepository: QuestionRepository? = null

    @GetMapping("/questions")
    fun getQuestions(pageable: Pageable): Page<Question> {
        return questionRepository!!.findAll(pageable)
    }


    @PostMapping("/questions")
    fun createQuestion(@Valid @RequestBody question: Question): Question {
        return questionRepository!!.save(question)
    }

    @PutMapping("/questions/{questionId}")
    fun updateQuestion(
        @PathVariable questionId: Long,
        @Valid @RequestBody questionRequest: Question
    ): Question {
        return questionRepository!!.findById(questionId)
            .map { question ->
                question.title = questionRequest.title
                question.description = questionRequest.description
                questionRepository.save(question)
            }.orElseThrow { ResourceNotFoundException("Question not found with id " + questionId) }
    }


    @DeleteMapping("/questions/{questionId}")
    fun deleteQuestion(@PathVariable questionId: Long): ResponseEntity<*> {
        return questionRepository!!.findById(questionId)
            .map { question ->
                questionRepository.delete(question)
                ResponseEntity.ok().build<Any>()
            }.orElseThrow { ResourceNotFoundException("Question not found with id " + questionId) }
    }
}
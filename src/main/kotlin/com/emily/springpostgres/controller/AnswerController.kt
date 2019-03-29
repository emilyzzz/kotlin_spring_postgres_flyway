package com.emily.springpostgres.controller

import com.emily.springpostgres.exception.ResourceNotFoundException
import com.emily.springpostgres.model.Answer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import kotlin.collections.List
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import com.emily.springpostgres.repository.QuestionRepository
import com.emily.springpostgres.repository.AnswerRepository
import org.springframework.web.bind.annotation.RestController


@RestController
class AnswerController {

    @Autowired
    private val answerRepository: AnswerRepository? = null

    @Autowired
    private val questionRepository: QuestionRepository? = null

    @GetMapping("/questions/{questionId}/answers")
    fun getAnswersByQuestionId(@PathVariable questionId: Long?): List<Answer> {
        return answerRepository!!.findByQuestionId(questionId)
    }

    @PostMapping("/questions/{questionId}/answers")
    fun addAnswer(
        @PathVariable questionId: Long,
        @Valid @RequestBody answer: Answer
    ): Any {
        return questionRepository!!.findById(questionId)
            .map<Any> { question ->
                answer.setQuestion(question)
                answerRepository!!.save(answer)
            }.orElseThrow { ResourceNotFoundException("Question not found with id " + questionId) }
    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    fun updateAnswer(
        @PathVariable questionId: Long,
        @PathVariable answerId: Long,
        @Valid @RequestBody answerRequest: Answer
    ): Answer {
        if (!questionRepository!!.existsById(questionId)) {
            throw ResourceNotFoundException("Question not found with id " + questionId)
        }

        return answerRepository!!.findById(answerId)
            .map { answer ->
                answer.setText(answerRequest.getText())
                answerRepository.save(answer)
            }.orElseThrow { ResourceNotFoundException("Answer not found with id " + answerId) }
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    fun deleteAnswer(
        @PathVariable questionId: Long,
        @PathVariable answerId: Long
    ): ResponseEntity<*> {
        if (!questionRepository!!.existsById(questionId)) {
            throw ResourceNotFoundException("Question not found with id " + questionId)
        }

        return answerRepository!!.findById(answerId)
            .map { answer ->
                answerRepository.delete(answer)
                ResponseEntity.ok().build<Any>()
            }.orElseThrow { ResourceNotFoundException("Answer not found with id " + answerId) }

    }
}

package com.emily.springpostgres.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction


@Entity
@Table(name = "answers")
class Answer : AuditModel() {
    @Id
    @GeneratedValue(generator = "answer_generator")
    @SequenceGenerator(name = "answer_generator", sequenceName = "answer_sequence", initialValue = 1000)
    private var id: Long? = null

    @Column(columnDefinition = "text")
    private var text: String? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private var question: Question? = null

    // Getters and Setters (Omitted for brevity)
    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getText(): String {
        return text.orEmpty()
    }

    fun setText(text: String) {
        this.text = text
    }

    fun getQuestion(): Question {
        return question!!
    }

    fun setQuestion(question: Question) {
        this.question = question
    }

}
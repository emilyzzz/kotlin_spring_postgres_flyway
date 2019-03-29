package com.emily.springpostgres.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
@Table(name = "questions")
class Question : AuditModel() {
    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(name = "question_generator", sequenceName = "question_sequence", initialValue = 1000)
    private var id: Long? = null

    @NotBlank
    @Size(min = 3, max = 100)
    private var title: String? = null

    @Column(columnDefinition = "text")
    private var description: String? = null
    
    // Getters and Setters (Omitted for brevity)
    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getTitle(): String {
        return title.orEmpty()
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getDescription(): String {
        return description.orEmpty()
    }

    fun setDescription(description: String) {
        this.description = description
    }

}
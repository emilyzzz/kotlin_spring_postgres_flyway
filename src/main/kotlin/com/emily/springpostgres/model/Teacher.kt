package com.emily.springpostgres.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
@Table(name = "teachers")
class Teacher : AuditModel() {
    @Id @GeneratedValue(generator = "teacher_generator")
    @SequenceGenerator(name = "teacher_generator", sequenceName = "teacher_sequence", initialValue = 1000)
    var id: Long? = null

    @NotBlank @Size(min = 3, max = 100)
    var name: String = "unset"

    @Column
    var description: String? = null

}
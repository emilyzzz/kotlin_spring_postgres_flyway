package com.emily.springpostgres.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
@Table(name = "teachers")
class Teacher : AuditModel() {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank @Size(min = 3, max = 100)
    var name: String? = null

    @Column
    var description: String? = null

}
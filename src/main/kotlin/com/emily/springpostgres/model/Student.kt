package com.emily.springpostgres.model

import com.fasterxml.jackson.annotation.*
import javax.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction


@Entity
@Table(name = "students")
@JsonIgnoreProperties(value = ["teacherId"], allowGetters = true)
class Student : AuditModel() {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    var name: String? = null

    @Column
    var description: String? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property="id")
    @JsonIdentityReference(alwaysAsId = true)
    var teacherId: Teacher? = null     // a student has 1 teacherId, a teacherId has many students, ForeignKey relation
}
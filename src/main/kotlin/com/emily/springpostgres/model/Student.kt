package com.emily.springpostgres.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction


@Entity
@Table(name = "students")
class Student : AuditModel() {
    @Id @GeneratedValue(generator = "student_generator")
    @SequenceGenerator(name = "student_generator", sequenceName = "student_sequence", initialValue = 1000)
    var id: Long? = null

    @Column
    var name: String? = null

    @Column
    var description: String? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    var teacher: Teacher? = null     // a student has 1 teacher, a teacher has many students, ForeignKey relation
}
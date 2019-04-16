package com.emily.springpostgres.repository

import com.emily.springpostgres.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface StudentRepository : JpaRepository<Student, Long> {
    fun findByTeacherId(teacherId: Long?): List<Student>
}
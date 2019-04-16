package com.emily.springpostgres.controller

import com.emily.springpostgres.exception.ResourceNotFoundException
import com.emily.springpostgres.model.Teacher
import com.emily.springpostgres.repository.TeacherRepository
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
class TeacherController {

    @Autowired
    private val teacherRepository: TeacherRepository? = null

    @GetMapping("/teachers")
    fun getTeachers(pageable: Pageable): Page<Teacher> {
        return teacherRepository!!.findAll(pageable)
    }


    @PostMapping("/teachers")
    fun createTeacher(@Valid @RequestBody teacher: Teacher): Teacher {
        return teacherRepository!!.save(teacher)
    }

    @PutMapping("/teachers/{teacherId}")
    fun updateTeacher(
        @PathVariable teacherId: Long,
        @Valid @RequestBody teacherRequest: Teacher
    ): Teacher {
        return teacherRepository!!.findById(teacherId)
            .map { t ->
                t.name = teacherRequest.name
                t.description = teacherRequest.description
                teacherRepository.save(t)
            }.orElseThrow { ResourceNotFoundException("Teacher not found with id: " + teacherId) }
    }


    @DeleteMapping("/teachers/{teacherId}")
    fun deleteTeacher(@PathVariable teacherId: Long): ResponseEntity<*> {
        return teacherRepository!!.findById(teacherId)
            .map { t ->
                teacherRepository.delete(t)
                ResponseEntity.ok().build<Any>()
            }.orElseThrow { ResourceNotFoundException("Teacher not found with id: " + teacherId) }
    }
}
package com.emily.springpostgres.controller

import com.emily.springpostgres.exception.ResourceNotFoundException
import com.emily.springpostgres.model.Teacher
import com.emily.springpostgres.getNullPropertyNames
import com.emily.springpostgres.repository.TeacherRepository
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
class TeacherController {

    @Autowired
    private val teacherRepository: TeacherRepository? = null

    @GetMapping("/teachers")
    fun getTeachers(
            pageable: Pageable,
            @RequestParam(required=false) id: Long?
    ): Page<Teacher> {
        when (id == null) {
            true -> return teacherRepository!!.findAll(pageable)
            false -> {
                val teacher = teacherRepository!!.findByIdOrNull(id)?: throw ResourceNotFoundException("teacher not found for id = $id")
                return PageImpl<Teacher>(listOf(teacher), pageable, 1)
            }
        }
    }

    @PostMapping("/teachers")
    fun createTeacher(@Valid @RequestBody teacher: Teacher): Teacher {
        return teacherRepository!!.save(teacher) }

    @PutMapping("/teachers/{teacherId}")
    fun updateTeacher(
            @PathVariable teacherId: Long,
            @RequestBody payload: Teacher
     ): Teacher {
        val objInDb: Teacher = teacherRepository!!.findById(teacherId)?.orElseThrow { ResourceNotFoundException("Teacher not found for id=$teacherId") }
        BeanUtils.copyProperties(payload, objInDb, *getNullPropertyNames(payload))
        return teacherRepository.save(objInDb)
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
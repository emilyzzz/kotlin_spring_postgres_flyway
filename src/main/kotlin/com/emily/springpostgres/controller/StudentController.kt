package com.emily.springpostgres.controller

import com.emily.springpostgres.exception.ResourceNotFoundException
import com.emily.springpostgres.model.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import javax.validation.Valid
import kotlin.collections.List
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import com.emily.springpostgres.repository.TeacherRepository
import com.emily.springpostgres.repository.StudentRepository
import org.springframework.web.bind.annotation.RestController
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


@RestController
class StudentController {

    @Autowired
    private val studentRepository: StudentRepository? = null

    @Autowired
    private val teacherRepository: TeacherRepository? = null

    @GetMapping("/students")
    fun getStudents(pageable: Pageable): Page<Student> {
        return studentRepository!!.findAll(pageable)
    }

    @GetMapping("/teachers/{tId}/students")
    fun getStudentsByTeacherId(@PathVariable teacherId: Long?): List<Student> {
        return studentRepository!!.findByTeacherId(teacherId)
    }

    @PostMapping("/teachers/{teacherId}/students")
    fun addStudentsForTeacher(
        @PathVariable teacherId: Long,
        @Valid @RequestBody student: Student
    ): Any {
        return teacherRepository!!.findById(teacherId)
            .map<Any> { teacher ->
                student.teacherId = teacher
                studentRepository!!.save(student)
            }.orElseThrow { ResourceNotFoundException("Teacher not found with id: " + teacherId) }
    }

    @PutMapping("/teachers/{teacherId}/students/{studentId}")
    fun updateStudentsForTeacher(
        @PathVariable teacherId: Long,
        @PathVariable studentId: Long,
        @Valid @RequestBody studentRequestBody: Student
    ): Student {
        if (!teacherRepository!!.existsById(teacherId)) {
            throw ResourceNotFoundException("Teacher not found with id: " + teacherId)
        }

        return studentRepository!!.findById(studentId)
            .map { student ->
                student.teacherId = studentRequestBody.teacherId
                studentRepository.save(student)
            }.orElseThrow { ResourceNotFoundException("Student not found with id: " + studentId) }
    }

    @DeleteMapping("/teachers/{teacherId}/students/{studentId}")
    fun deleteStudent(
        @PathVariable teacherId: Long,
        @PathVariable studentId: Long
    ): ResponseEntity<*> {
        if (!teacherRepository!!.existsById(teacherId)) {
            throw ResourceNotFoundException("Teacher not found with id " + teacherId)
        }

        return studentRepository!!.findById(studentId)
            .map { student ->
                studentRepository.delete(student)
                ResponseEntity.ok().build<Any>()
            }.orElseThrow { ResourceNotFoundException("Student not found with id: " + studentId) }

    }
}

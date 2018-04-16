package com.dan.SD_2.controllers;

import com.dan.SD_2.service.StudentService;
import com.dan.SD_2.service.dto.StudentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;
@RestController
@RequestMapping("/students")
@Api(value = "onlineLab", description="Operations regarding students on the website")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "View a list of available students",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(method = GET, value = "/getAll", produces = "application/json")
    public List<StudentDTO> getAllStudent() {
        try {
            return studentService.getAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "Search a student with an ID",response = StudentDTO.class)
    @RequestMapping(method = GET, value = "/getById/{id}", produces = "application/json")
    public StudentDTO getStudentById(@RequestParam int studentId) {
        try {
            return studentService.getStudentById(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "Add a student")
    @RequestMapping(method = POST, value = "/add", produces = "application/json")
    public ResponseEntity saveStudent(@RequestBody StudentDTO student) {
        studentService.insertStudent(student);
        return new ResponseEntity("Student added succesfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a student")
    @RequestMapping(method = PUT, value = "/{studentId}", produces = "application/json")
    public ResponseEntity updateStudent(@RequestParam int studentId, @RequestBody StudentDTO student) {
        studentService.updateStudent(student, studentId);
        return new ResponseEntity("Product update succesfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a student")
    @RequestMapping(method = DELETE, value = "/{studentId}", produces = "application/json")
    public ResponseEntity deleteStudentById(@PathVariable int studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity("Product deleted succesfully", HttpStatus.OK);
    }

}

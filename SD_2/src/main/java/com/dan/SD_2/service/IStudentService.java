package com.dan.SD_2.service;

import com.dan.SD_2.model.Student;
import com.dan.SD_2.service.dto.StudentDTO;

import java.util.List;

public interface IStudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(int studentId);

    StudentDTO insertStudent(StudentDTO Student);

    void updateStudent(StudentDTO Student, int studentId);

    void deleteStudent(int studentId);
}

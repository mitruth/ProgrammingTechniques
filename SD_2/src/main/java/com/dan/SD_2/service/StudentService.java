package com.dan.SD_2.service;

import com.dan.SD_2.model.Student;
import com.dan.SD_2.repository.StudentRepository;
import com.dan.SD_2.service.dto.StudentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentsToReturn = new ArrayList<StudentDTO>();
        for(Student s : students) {
            StudentDTO student = modelMapper.map(s, StudentDTO.class);
            studentsToReturn.add(student);
        }
        return studentsToReturn;
    }

    @Override
    public StudentDTO getStudentById(int studentId) {
        Student student = studentRepository.findOne(studentId);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO insertStudent(StudentDTO studentDTO){
        Student student = modelMapper.map(studentDTO, Student.class);

        if(studentRepository.findByName(student.getName()) == null) {
            studentRepository.save(student);
            Student studentFromDB = studentRepository.findByName(student.getName());
            return modelMapper.map(studentFromDB, StudentDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void updateStudent(StudentDTO studentDTO, int studentId){
        Student student = studentRepository.findOne(studentId);
        System.out.println(student.toString());
        student = modelMapper.map(studentDTO, Student.class);
        student.setId(studentId);
        System.out.println(student.toString());
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int studentId) {
        studentRepository.delete(studentId);
    }
}

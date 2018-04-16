//package com.dan.SD_2.service;
//
//import com.dan.SD_2.model.TeacherModel;
//import com.dan.SD_2.repository.TeacherRepository;
//import com.dan.SD_2.service.dto.TeacherDTO;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class TeacherService implements ITeacherService {
//
//    private final TeacherRepository teacherRepository;
//    private ModelMapper modelMapper;
//
//    @Autowired
//    public TeacherService(TeacherRepository teacherRepository) {
//        this.teacherRepository = teacherRepository;
//        this.modelMapper = new ModelMapper();
//    }
//
//    @Override
//    public List<TeacherModel> getAllTeachers() {
//        List<TeacherDTO> teachersDTO = teacherRepository.findAll();
//        List<TeacherModel>  teachersToReturn = new ArrayList<TeacherModel>();
//        for(TeacherDTO t : teachersDTO) {
//            TeacherModel teacher = modelMapper.map(t, TeacherModel.class);
//            teachersToReturn.add(teacher);
//        }
//        return teachersToReturn;
//    }
//
//    @Override
//    public TeacherModel getTeacherById(int teacherId) {
//        TeacherDTO teacher = teacherRepository.findOne(teacherId);
//        return modelMapper.map(teacher, TeacherModel.class);
//    }
//
//    @Override
//    public void insertTeacher(TeacherModel teacherModel) throws InsertException {
//        TeacherDTO teacherDTO = modelMapper.map(teacherModel, TeacherDTO.class);
//
//        if(teacherRepository.findByName(teacherModel.getName()) == null) {
//            teacherRepository.save(teacherDTO);
//        } else {
//            throw new InsertException("Teacher name already in the database!");
//        }
//    }
//
//    @Override
//    public void updateTeacher(TeacherModel teacherModel, int teacherId) throws InsertException {
//        TeacherDTO teacherFromDB = teacherRepository.findOne(teacherId);
//        if(teacherFromDB != null) {
//            teacherFromDB = modelMapper.map(teacherModel, TeacherDTO.class);
//            if(teacherRepository.findByName(teacherFromDB.getName()) == null) {
//                teacherRepository.save(teacherFromDB);
//            } else {
//                throw new InsertException("Teacher name already in the database!");
//            }
//        }
//    }
//
//    @Override
//    public void deleteTeacher(int teacherId) {
//        teacherRepository.delete(teacherId);
//    }
//}

package com.dan.SD_2.repository;

import com.dan.SD_2.model.Teacher;
import com.dan.SD_2.service.dto.TeacherDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    TeacherDTO findByName(String name);
}

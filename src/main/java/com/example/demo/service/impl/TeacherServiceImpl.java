package com.example.demo.service.impl;

import com.example.demo.model.entity.*;
import com.example.demo.model.repository.SubjectRepository;
import com.example.demo.model.repository.TeacherRepository;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findByUser(User user) {
        return teacherRepository.findByUser(user);
    }

    @Override
    public List<Teacher> findBySchool(School school) {
        return teacherRepository.findByUser_School(school);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Optional<Teacher> findById(Integer teacherId) {
        return teacherRepository.findById(teacherId);
    }

    @Override
    public void update(Integer id, Teacher teacher) {
        Teacher editingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

        editingTeacher.setName(teacher.getName());
        editingTeacher.setEnabled(teacher.getEnabled());

        if (teacher.getUser() != null) {
            User user = userRepository.findById(teacher.getUser().getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));

            if (!user.getRoles().contains(Role.ROLE_TEACHER)) {
                throw new IllegalStateException("Can only link a user with a role ROLE_TEACHER");
            }

            Optional<Teacher> existing = teacherRepository.findByUser(user);
            if (existing.isPresent() && !existing.get().getId().equals(id)) {
                throw new IllegalStateException("This user is already linked");
            }

            editingTeacher.setUser(user);
        } else {
            editingTeacher.setUser(null);
        }

        if (teacher.getSubjects() != null) {
            List<Integer> subjectIds = new ArrayList<>();
            for (Subject s : teacher.getSubjects()) {
                subjectIds.add(s.getId());
            }

            List<Subject> subjects = subjectRepository.findAllById(subjectIds);
            editingTeacher.setSubjects(subjects);
        } else {
            editingTeacher.setSubjects(new ArrayList<>());
        }

        teacherRepository.save(editingTeacher);
    }
}

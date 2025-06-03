package com.example.demo.service.impl;

import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.entity.Schoolchild;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.SchoolchildRepository;
import com.example.demo.service.SchoolchildService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolchildServiceImpl implements SchoolchildService {
    private final SchoolchildRepository schoolchildRepository;

    @Override
    public Optional<Schoolchild> findById(Integer id) {
        return schoolchildRepository.findById(id);
    }

    @Override
    public List<Schoolchild> findBySchoolClassesContaining(SchoolClass schoolClass) {
        return schoolchildRepository.findBySchoolClassesContaining(schoolClass);
    }

    @Override
    public Schoolchild save(Schoolchild schoolchild) {
        return schoolchildRepository.save(schoolchild);
    }

    @Override
    public Optional<Schoolchild> findByUser(User user) {
        return schoolchildRepository.findByUser(user);
    }

    @Override
    public void update(Integer id, Schoolchild schoolchild) {
        Schoolchild editingSchoolchild = schoolchildRepository.findById(id).orElseThrow();
        editingSchoolchild.setName(schoolchild.getName());
        editingSchoolchild.setSchoolClasses(schoolchild.getSchoolClasses());
        editingSchoolchild.setEnabled(schoolchild.getEnabled());

        if (schoolchild.getUser() != null) {
            if (!schoolchild.getUser().getRoles().contains(Role.ROLE_SCHOOLCHILD)) {
                throw new IllegalStateException("Можно привязать только пользователя с ролью ROLE_SCHOOLCHILD");
            }

            Optional<Schoolchild> existing = schoolchildRepository.findByUser(schoolchild.getUser());
            if (existing.isPresent() && !existing.get().getId().equals(id)) {
                throw new IllegalStateException("Этот пользователь уже привязан к другому ученику");
            }
        }

        editingSchoolchild.setUser(schoolchild.getUser());
        schoolchildRepository.save(editingSchoolchild);
    }
}

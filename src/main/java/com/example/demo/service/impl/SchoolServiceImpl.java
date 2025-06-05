package com.example.demo.service.impl;

import com.example.demo.model.entity.School;
import com.example.demo.model.repository.SchoolRepository;
import com.example.demo.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;

    @Override
    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    @Override
    public School update(School school) {
        School schoolWithChanges = schoolRepository.findById(school.getId()).orElseThrow();

        Optional<School> byTitle = schoolRepository.findByTitle(school.getTitle());
        if (byTitle.isPresent() && !byTitle.get().getId().equals(school.getId())) {
            throw new RuntimeException("School with title '" + school.getTitle() + "' already exists");
        }

        Optional<School> byEmail = schoolRepository.findByEmail(school.getEmail());
        if (byEmail.isPresent() && !byEmail.get().getId().equals(school.getId())) {
            throw new RuntimeException("School with email '" + school.getEmail() + "' already exists");
        }

        schoolWithChanges.setTitle(school.getTitle());
        schoolWithChanges.setAddress(school.getAddress());
        schoolWithChanges.setPhone(school.getPhone());
        schoolWithChanges.setEmail(school.getEmail());
        schoolWithChanges.setDirectorName(school.getDirectorName());

        schoolRepository.save(schoolWithChanges);

        return schoolWithChanges;
    }
}

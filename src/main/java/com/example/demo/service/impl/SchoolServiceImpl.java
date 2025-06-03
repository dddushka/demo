package com.example.demo.service.impl;

import com.example.demo.model.entity.School;
import com.example.demo.model.repository.SchoolRepository;
import com.example.demo.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        schoolWithChanges.setTitle(school.getTitle());
        schoolWithChanges.setAddress(school.getAddress());
        schoolWithChanges.setPhone(school.getPhone());
        schoolWithChanges.setEmail(school.getEmail());
        schoolWithChanges.setDirectorName(school.getDirectorName());

        schoolRepository.save(schoolWithChanges);

        return schoolWithChanges;
    }
}

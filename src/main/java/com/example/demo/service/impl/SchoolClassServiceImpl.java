package com.example.demo.service.impl;

import com.example.demo.model.entity.School;
import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.repository.SchoolClassRepository;
import com.example.demo.service.SchoolClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolClassServiceImpl implements SchoolClassService {
    private final SchoolClassRepository schoolClassRepository;

    @Override
    public List<SchoolClass> findBySchool(School school) {
        return schoolClassRepository.findBySchoolOrderByGradeLevelAscLetterAsc(school);
    }

    @Override
    public Optional<SchoolClass> findById(Integer id) {
        return schoolClassRepository.findById(id);
    }

    @Override
    public void save(SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
    }

    @Override
    public void update(Integer id, SchoolClass schoolClass) {
        SchoolClass editingSchoolClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("School class not found"));

        editingSchoolClass.setGradeLevel(schoolClass.getGradeLevel());
        editingSchoolClass.setLetter(schoolClass.getLetter());

        schoolClassRepository.save(editingSchoolClass);
    }

    @Override
    public void enable(Integer id) {
        SchoolClass schoolClass = schoolClassRepository.findById(id).orElseThrow();
        schoolClass.setEnabled(true);
        schoolClassRepository.save(schoolClass);
    }

    @Override
    public void disable(Integer id) {
        SchoolClass schoolClass = schoolClassRepository.findById(id).orElseThrow();
        schoolClass.setEnabled(false);
        schoolClassRepository.save(schoolClass);
    }
}

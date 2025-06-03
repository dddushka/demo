package com.example.demo.service.impl;

import com.example.demo.model.entity.Subject;
import com.example.demo.model.repository.SubjectRepository;
import com.example.demo.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> findById(Integer id) {
        return subjectRepository.findById(id);
    }
}

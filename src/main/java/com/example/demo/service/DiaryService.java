package com.example.demo.service;

import com.example.demo.dto.DiaryDataDto;
import com.example.demo.model.entity.User;

public interface DiaryService {
    DiaryDataDto getDiaryData(User user, int weekOffset);
}

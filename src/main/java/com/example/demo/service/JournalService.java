package com.example.demo.service;

import com.example.demo.dto.JournalDataDto;
import com.example.demo.dto.JournalSaveDto;
import com.example.demo.model.entity.User;
import org.springframework.ui.Model;

import java.util.Map;

public interface JournalService {
    JournalDataDto fillJournal(User user, Integer subjectId, Integer schoolClassId);
    void processAndSaveJournal(JournalSaveDto dto, Integer schoolClassId, Integer subjectId);
    void saveJournalChanges(Map<Integer, Map<Integer, String>> grades,
                            Map<Integer, String> topics,
                            Map<Integer, String> homeworks,
                            Integer schoolClassId,
                            Integer subjectId);
}

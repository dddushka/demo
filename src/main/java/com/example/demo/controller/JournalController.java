package com.example.demo.controller;

import com.example.demo.dto.JournalSaveDto;
import com.example.demo.model.entity.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class JournalController {
    private final JournalService journalService;

    @GetMapping("/journal/{schoolClassId}/{subjectId}")
    public String getJournal(@PathVariable Integer schoolClassId,
                             @PathVariable Integer subjectId,
                             @AuthenticationPrincipal User user,
                             Model model) {

        try {
            journalService.fillJournal(user, subjectId, schoolClassId, model);
            return "journal";
        } catch (RuntimeException e) {
            return "redirect:/error";
        }
    }

    @PostMapping("/journal/{schoolClassId}/{subjectId}/save")
    public String saveJournal(@ModelAttribute JournalSaveDto dto,
                              @PathVariable Integer schoolClassId,
                              @PathVariable Integer subjectId){
        journalService.processAndSaveJournal(dto, schoolClassId, subjectId);
        return "redirect:/journal/" + schoolClassId + "/" + subjectId;
    }
}

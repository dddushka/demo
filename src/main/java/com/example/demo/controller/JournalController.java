package com.example.demo.controller;

import com.example.demo.dto.JournalDataDto;
import com.example.demo.dto.JournalSaveDto;
import com.example.demo.model.entity.User;
import com.example.demo.service.JournalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JournalController {
    private final JournalService journalService;

    //get request to journal page
    @GetMapping("/journal/{schoolClassId}/{subjectId}")
    public String getJournal(@PathVariable Integer schoolClassId,
                             @PathVariable Integer subjectId,
                             @AuthenticationPrincipal User user,
                             Model model) {
        try {
            JournalDataDto journal = journalService.fillJournal(user, subjectId, schoolClassId);

            model.addAttribute("subject", journal.getSubject());
            model.addAttribute("schoolClass", journal.getSchoolClass());
            model.addAttribute("schoolchildren", journal.getSchoolchildren());
            model.addAttribute("lessons", journal.getLessons());
            model.addAttribute("grades", journal.getGrades());

            return "journal";
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    //post request to save journal changes
    @PostMapping("/journal/{schoolClassId}/{subjectId}/save")
    public String saveJournal(@ModelAttribute @Valid JournalSaveDto dto,
                              BindingResult bindingResult,
                              @PathVariable Integer schoolClassId,
                              @PathVariable Integer subjectId){
        if (bindingResult.hasErrors()) { //dto validation
            return "journal";
        }
        journalService.processAndSaveJournal(dto, schoolClassId, subjectId);
        return "redirect:/journal/" + schoolClassId + "/" + subjectId;
    }
}

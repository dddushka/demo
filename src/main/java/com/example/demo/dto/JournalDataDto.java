package com.example.demo.dto;

import com.example.demo.model.entity.Lesson;
import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.entity.Schoolchild;
import com.example.demo.model.entity.Subject;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class JournalDataDto {
    @NotNull
    private Subject subject;

    @NotNull
    private SchoolClass schoolClass;

    private List<Schoolchild> schoolchildren;
    private List<Lesson> lessons;
    private Map<Integer, Map<Integer, Integer>> grades;
}

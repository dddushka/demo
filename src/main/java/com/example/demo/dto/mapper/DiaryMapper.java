package com.example.demo.dto.mapper;

import com.example.demo.dto.DiaryDataDto;
import com.example.demo.model.entity.Grade;
import com.example.demo.model.entity.Lesson;
import com.example.demo.model.entity.Schoolchild;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class DiaryMapper {
    public DiaryDataDto toDto(Schoolchild schoolchild,
                              Map<LocalDate, List<Lesson>> currentWeekLessons,
                              Map<Integer, Grade> gradesMap,
                              Integer weekOffset,
                              LocalDate weekStart,
                              LocalDate weekEnd) {
        DiaryDataDto diaryDataDto = new DiaryDataDto();
        diaryDataDto.setSchoolchild(schoolchild);
        diaryDataDto.setLessonsByDate(currentWeekLessons);
        diaryDataDto.setGradesMap(gradesMap);
        diaryDataDto.setWeekOffset(weekOffset);
        diaryDataDto.setWeekStart(weekStart);
        diaryDataDto.setWeekEnd(weekEnd);

        return diaryDataDto;
    }
}

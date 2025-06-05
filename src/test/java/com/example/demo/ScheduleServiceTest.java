package com.example.demo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.example.demo.model.entity.Schedule;
import com.example.demo.model.entity.Subject;
import com.example.demo.model.entity.Teacher;
import com.example.demo.model.repository.ScheduleRepository;
import com.example.demo.service.impl.ScheduleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.DayOfWeek;
import java.util.Optional;

public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateSuccess() {
        Subject subject = new Subject();
        subject.setId(1);
        subject.setSubjectName("Математика");

        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setName("ФТМ");
        teacher.setEnabled(true);

        Schedule existingSchedule = new Schedule();
        existingSchedule.setDayOfWeek(DayOfWeek.MONDAY);
        existingSchedule.setLessonNumber(1);
        existingSchedule.setClassroom(101);
        existingSchedule.setSubject(subject);
        existingSchedule.setTeacher(teacher);

        Subject newSubject = new Subject();
        newSubject.setId(2);
        newSubject.setSubjectName("Физика");

        Teacher newTeacher = new Teacher();
        newTeacher.setId(2);
        newTeacher.setName("ТАИ");
        newTeacher.setEnabled(true);

        Schedule newSchedule = new Schedule();
        newSchedule.setDayOfWeek(DayOfWeek.FRIDAY);
        newSchedule.setLessonNumber(3);
        newSchedule.setClassroom(202);
        newSchedule.setSubject(newSubject);
        newSchedule.setTeacher(newTeacher);

        when(scheduleRepository.findById(1)).thenReturn(Optional.of(existingSchedule));

        scheduleService.update(1, newSchedule);

        assertEquals(DayOfWeek.FRIDAY, existingSchedule.getDayOfWeek());
        assertEquals(3, existingSchedule.getLessonNumber());
        assertEquals(202, existingSchedule.getClassroom());
        assertEquals(newSubject, existingSchedule.getSubject());
        assertEquals(newTeacher, existingSchedule.getTeacher());

        verify(scheduleRepository).save(existingSchedule);
    }
}

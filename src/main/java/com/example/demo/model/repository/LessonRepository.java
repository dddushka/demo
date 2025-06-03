package com.example.demo.model.repository;

import com.example.demo.model.entity.Lesson;
import com.example.demo.model.entity.Schedule;
import com.example.demo.model.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query("SELECT l FROM Lesson l WHERE l.schedule IN :schedule")
    List<Lesson> findBySchedule(List<Schedule> schedule);

    List<Lesson> findAllBySchedule_SchoolClass(SchoolClass schoolClass);

}

package com.example.demo;

import com.example.demo.dto.SchoolClassDto;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class SchoolClassDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidDto() {
        SchoolClassDto dto = new SchoolClassDto();
        dto.setId(1);
        dto.setGradeLevel(5);
        dto.setLetter("А");
        dto.setEnabled(true);

        Set<ConstraintViolation<SchoolClassDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "DTO should be valid");
    }

    @Test
    void testInvalidGradeLevel() {
        SchoolClassDto dto = new SchoolClassDto();
        dto.setId(1);
        dto.setGradeLevel(0);
        dto.setLetter("Б");
        dto.setEnabled(true);

        Set<ConstraintViolation<SchoolClassDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("gradeLevel")));
    }

    @Test
    void testInvalidLetter() {
        SchoolClassDto dto = new SchoolClassDto();
        dto.setId(1);
        dto.setGradeLevel(5);
        dto.setLetter("");
        dto.setEnabled(true);

        Set<ConstraintViolation<SchoolClassDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("letter")));
    }
}

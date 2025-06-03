package com.example.demo.dto;

import lombok.Data;

import java.util.Map;

@Data
public class JournalSaveDto {
    private Map<Integer, Map<Integer, String>> grades;
    private Map<Integer, String> topics;
    private Map<Integer, String> homeworks;
}

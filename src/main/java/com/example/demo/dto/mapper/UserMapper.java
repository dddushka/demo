package com.example.demo.dto.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles());
        dto.setEnabled(user.isEnabled());
        return dto;
    }
}

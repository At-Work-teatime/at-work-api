package com.atwork.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private String description;

    @Builder
    public UserUpdateRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

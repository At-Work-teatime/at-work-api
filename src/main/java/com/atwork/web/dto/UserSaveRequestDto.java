package com.atwork.web.dto;

import com.atwork.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String email;
    private String name;
    private String description;

    @Builder
    public UserSaveRequestDto(String email, String name, String description) {
        this.email = email;
        this.name = name;
        this.description = description;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .description(description)
                .build();
    }

}

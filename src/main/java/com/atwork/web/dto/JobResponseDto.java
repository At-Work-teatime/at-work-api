package com.atwork.web.dto;

import com.atwork.domain.job.Job;
import lombok.Getter;

@Getter
public class JobResponseDto {
    private Long id;
    private String name;

    public JobResponseDto(Job entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}

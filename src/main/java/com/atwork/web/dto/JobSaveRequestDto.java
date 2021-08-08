package com.atwork.web.dto;

import com.atwork.domain.job.Job;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JobSaveRequestDto {
    private String name;

    @Builder
    public JobSaveRequestDto(String name) {
        this.name = name;
    }

    public Job toEntity() {
        return Job.builder()
                .name(name)
                .build();
    }
}

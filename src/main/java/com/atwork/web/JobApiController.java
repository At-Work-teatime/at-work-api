package com.atwork.web;

import com.atwork.service.job.JobService;
import com.atwork.web.dto.JobResponseDto;
import com.atwork.web.dto.JobSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class JobApiController {
    private final JobService jobService;

    @PostMapping("/api/v1/jobs")
    public Long save(@RequestBody JobSaveRequestDto jobSaveDto) {
        return jobService.save(jobSaveDto);
    }

    @GetMapping("/api/v1/jobs")
    public List<JobResponseDto> findAll() {
        return jobService.findAll();
    }
}

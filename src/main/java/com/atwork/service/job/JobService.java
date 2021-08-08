package com.atwork.service.job;

import com.atwork.domain.job.JobRepository;
import com.atwork.web.dto.JobResponseDto;
import com.atwork.web.dto.JobSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobService {
    private final JobRepository jobRepository;

    @Transactional
    public Long save(JobSaveRequestDto requestDto) {
        return jobRepository.save(requestDto.toEntity()).getId();
    }

    public List<JobResponseDto> findAll() {
        return jobRepository.findAll().stream().map(JobResponseDto::new)
                .collect(Collectors.toList());
    }
}

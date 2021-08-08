package com.atwork.web;

import com.atwork.domain.job.Job;
import com.atwork.domain.job.JobRepository;
import com.atwork.web.dto.JobSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JobRepository jobRepository;

    @After
    public void tearDown() throws Exception {
        jobRepository.deleteAll();
    }

    @Test
    public void saveJob() throws Exception {
        String name = "백엔드 개발자";
        JobSaveRequestDto requestDto = JobSaveRequestDto.builder()
                .name(name)
                .build();
        String url = "http://localhost:" + port + "/api/v1/jobs";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Job> all = jobRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
    }


}

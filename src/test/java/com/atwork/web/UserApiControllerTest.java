package com.atwork.web;

import com.atwork.domain.user.User;
import com.atwork.domain.user.UserRepository;
import com.atwork.web.dto.UserSaveRequestDto;
import com.atwork.web.dto.UserUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void saveUser() throws Exception {
        // given
        String email = "tester@naver.com";
        String name = "test";
        String description = "Hello! I'm test.";
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .email(email)
                .name(name)
                .description(description)
                .build();
        String url = "http://localhost:" + port + "/api/v1/users";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getEmail()).isEqualTo(email);
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getDescription()).isEqualTo(description);
    }

    @Test
    public void updateUser() throws Exception {
        User savedUser = userRepository.save(User.builder().email("test@naver.com").name("tester").description("test user").build());

        Long updateId = savedUser.getId();
        String expectedName = "tester-2";
        String expectedDescription = "updated Descrtipion";

        UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder().name(expectedName).description(expectedDescription).build();

        String url = "http://localhost:" + port + "/api/v1/users/" + updateId;

        HttpEntity<UserUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(expectedName);
        assertThat(all.get(0).getDescription()).isEqualTo(expectedDescription);
    }
}

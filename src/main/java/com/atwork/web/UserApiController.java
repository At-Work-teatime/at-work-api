package com.atwork.web;

import com.atwork.service.user.UserService;
import com.atwork.web.dto.UserResponseDto;
import com.atwork.web.dto.UserSaveRequestDto;
import com.atwork.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/users")
    public Long save(@RequestBody UserSaveRequestDto requestDto) {
        return userService.save(requestDto);
    }

    @PutMapping("/api/v1/users/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(id, requestDto);
    }

    @GetMapping("/api/v1/users/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/api/v1/users/{id}")
    public Long delete(@PathVariable Long id) {
        userService.delete(id);
        return id;
    }



}

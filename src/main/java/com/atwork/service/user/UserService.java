package com.atwork.service.user;

import com.atwork.domain.user.User;
import com.atwork.domain.user.UserRepository;
import com.atwork.web.dto.UserResponseDto;
import com.atwork.web.dto.UserSaveRequestDto;
import com.atwork.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        user.update(requestDto.getName(), requestDto.getDescription());

        return id;
    }

    public UserResponseDto findById(Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return new UserResponseDto(entity);
    }

    @Transactional
    public Long inactive(Long id) {   // 실제론 soft delete 되어야함
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        user.inactive();
        return id;
    }

    @Transactional
    public Long delete(Long id) {   // 실제론 soft delete 되어야함
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        user.delete();
        return id;
    }
}

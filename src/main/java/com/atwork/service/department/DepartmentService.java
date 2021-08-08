package com.atwork.service.department;

import com.atwork.domain.department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    // TODO: save하는 기능 구현
}

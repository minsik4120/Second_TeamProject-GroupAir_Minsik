package org.spring.groupAir.department.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.department.repository.DepartmentRepository;
import org.spring.groupAir.department.service.serviceImpl.DepartmentServiceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService implements DepartmentServiceImpl {

    private final DepartmentRepository departmentRepository;

}

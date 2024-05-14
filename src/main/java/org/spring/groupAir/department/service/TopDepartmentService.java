package org.spring.groupAir.department.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.department.repository.TopDepartmentRepository;
import org.spring.groupAir.department.service.serviceImpl.TopDepartmentServiceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopDepartmentService implements TopDepartmentServiceImpl {

    private final TopDepartmentRepository topDepartmentRepository;

}

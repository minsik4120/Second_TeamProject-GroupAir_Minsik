package org.spring.groupAir.department.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.department.dto.TopDepartmentDto;
import org.spring.groupAir.department.entity.TopDepartmentEntity;
import org.spring.groupAir.department.repository.TopDepartmentRepository;
import org.spring.groupAir.department.service.serviceImpl.TopDepartmentServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopDepartmentService implements TopDepartmentServiceImpl {

    private final TopDepartmentRepository topDepartmentRepository;


    @Override
    public Page<TopDepartmentDto> pageList(Pageable pageable) {

        Page<TopDepartmentEntity> topDepartmentEntities = topDepartmentRepository.findAll(pageable);

        Page<TopDepartmentDto> topDepartmentDtos = topDepartmentEntities.map(TopDepartmentDto::toDepartmentDto);

        return topDepartmentDtos;
    }
}

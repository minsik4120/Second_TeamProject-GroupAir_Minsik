package org.spring.groupAir.department.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.department.dto.DepartmentDto;
import org.spring.groupAir.department.entity.DepartmentEntity;
import org.spring.groupAir.department.repository.DepartmentRepository;
import org.spring.groupAir.department.service.serviceImpl.DepartmentServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService implements DepartmentServiceImpl {

    private final DepartmentRepository departmentRepository;

    @Override
    public void write(DepartmentDto departmentDto) {

        DepartmentEntity departmentEntity = DepartmentDto.toWriteDeEntity(departmentDto);

        departmentRepository.save(departmentEntity);
    }

    @Override
    public DepartmentDto detail(Long id) {

        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("부서없음");
        });


        DepartmentDto departmentDto = DepartmentEntity.toUpdateDe(departmentEntity);

        return departmentDto;
    }

    @Override
    public void update(DepartmentDto departmentDto) {

        DepartmentEntity departmentEntity = departmentRepository.save(DepartmentEntity.builder()
                .id(departmentDto.getId())
                .departmentName(departmentDto.getDepartmentName())
                .topDepartmentEntity(departmentDto.getTopDepartmentEntity())
                .memberEntityList(departmentDto.getMemberEntityList())
                .build());

    }

    @Override
    public void delete(Long id) {

        departmentRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("삭제할 부서 X");
        });

        departmentRepository.deleteById(id);

    }
}

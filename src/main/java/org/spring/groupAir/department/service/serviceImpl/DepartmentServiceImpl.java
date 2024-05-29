package org.spring.groupAir.department.service.serviceImpl;

import org.spring.groupAir.department.dto.DepartmentDto;

import java.util.List;

public interface DepartmentServiceImpl {
    void write(DepartmentDto departmentDto);

    DepartmentDto detail(Long id);

    void update(DepartmentDto departmentDto);

    void delete(Long id);

    List<DepartmentDto> getSubDepartments(Long topDepartmentId);

    List<DepartmentDto> subDepartments();
}

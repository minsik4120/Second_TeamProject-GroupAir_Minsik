package org.spring.groupAir.department.service.serviceImpl;

import org.spring.groupAir.department.dto.DepartmentDto;

public interface DepartmentServiceImpl {
    void write(DepartmentDto departmentDto);

    DepartmentDto detail(Long id);

    void update(DepartmentDto departmentDto);

    void delete(Long id);
}

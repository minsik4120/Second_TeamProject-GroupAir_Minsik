package org.spring.groupAir.department.service.serviceImpl;

import org.spring.groupAir.department.dto.TopDepartmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopDepartmentServiceImpl {
    Page<TopDepartmentDto> pageList(Pageable pageable);
}

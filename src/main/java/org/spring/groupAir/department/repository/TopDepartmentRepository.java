package org.spring.groupAir.department.repository;

import org.spring.groupAir.department.entity.TopDepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopDepartmentRepository extends JpaRepository<TopDepartmentEntity, Long> {
}

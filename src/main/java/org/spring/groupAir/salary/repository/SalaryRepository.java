package org.spring.groupAir.salary.repository;

import org.spring.groupAir.salary.entity.SalaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryEntity, Long> {
    Page<SalaryEntity> findByMemberEntityNameContains(Pageable pageable, String search);

    Page<SalaryEntity> findByMemberEntityPositionEntityPositionNameContains(Pageable pageable, String search);

    SalaryEntity findByMemberEntityId(Long id);
}

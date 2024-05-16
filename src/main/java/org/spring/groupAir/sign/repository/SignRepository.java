package org.spring.groupAir.sign.repository;

import org.spring.groupAir.sign.entity.SignEntity;
import org.spring.groupAir.sign.entity.SignFileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignRepository  extends JpaRepository<SignEntity, Long> {
    Page<SignEntity> findByTitleContaining(Pageable pageable, String search);

    Page<SignEntity> findByApproveContaining(Pageable pageable, String search);
}

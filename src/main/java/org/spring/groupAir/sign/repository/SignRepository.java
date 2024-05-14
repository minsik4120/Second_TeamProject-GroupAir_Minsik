package org.spring.groupAir.sign.repository;

import org.spring.groupAir.sign.entity.SignEntity;
import org.spring.groupAir.sign.entity.SignFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignRepository  extends JpaRepository<SignEntity, Long> {
}

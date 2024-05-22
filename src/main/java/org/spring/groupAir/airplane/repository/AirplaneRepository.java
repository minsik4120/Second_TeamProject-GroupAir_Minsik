package org.spring.groupAir.airplane.repository;

import org.spring.groupAir.airplane.entity.AirPlaneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<AirPlaneEntity, Long> {
    Page<AirPlaneEntity> findByFromAreaContains(Pageable pageable, String search);

    Page<AirPlaneEntity> findByToAreaContains(Pageable pageable, String search);

    Page<AirPlaneEntity> findByAirplaneContains(Pageable pageable, String search);
}

package org.spring.groupAir.airplane.repository;

import org.spring.groupAir.airplane.entity.AirPlaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<AirPlaneEntity, Long> {
}

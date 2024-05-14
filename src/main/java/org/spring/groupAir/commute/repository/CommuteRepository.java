package org.spring.groupAir.commute.repository;

import org.spring.groupAir.commute.entity.CommuteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuteRepository extends JpaRepository<CommuteEntity, Long> {

}

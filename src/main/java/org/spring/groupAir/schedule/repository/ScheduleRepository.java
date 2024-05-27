package org.spring.groupAir.schedule.repository;

import org.spring.groupAir.schedule.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Integer> {


}

package org.spring.groupAir.commute.repository;

import org.spring.groupAir.commute.entity.VacationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VacationRepository extends JpaRepository<VacationEntity, Long> {

    @Query(value = " SELECT COUNT(*) FROM vacation v WHERE v.vac_start_date <= :date AND v.vac_end_date >= :date  AND v.vac_type = '연차'", nativeQuery = true)
    int findAllByDate(@Param("date") LocalDate now);
//    AND v.vac_type = '휴가'
    @Query(value = " SELECT COUNT(*) FROM vacation v WHERE v.vac_start_date <= :date AND v.vac_end_date >= :date AND v.vac_type = '병가'", nativeQuery = true)
    int findBySickVacationPeople(@Param("date")LocalDate now);
}

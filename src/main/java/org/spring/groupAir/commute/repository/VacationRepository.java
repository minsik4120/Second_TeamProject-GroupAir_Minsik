package org.spring.groupAir.commute.repository;

import org.spring.groupAir.commute.entity.VacationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<VacationEntity, Long> {

    @Query(value = " SELECT COUNT(*) FROM vacation v WHERE v.vac_start_date <= :date AND v.vac_end_date >= :date AND v.vac_type = '휴가'", nativeQuery = true)
    int findAllByDate(@Param("date") LocalDate now);

    @Query(value="SELECT * FROM vacation v WHERE v.vac_start_date <= :date AND v.vac_end_date >= :date AND (v.vac_type= '병가' or v.vac_type = '휴가')", nativeQuery = true)
    List<VacationEntity> findVacationPerson(@Param("date") LocalDate now);

    @Query("SELECT v FROM VacationEntity v WHERE v.memberEntity.id = :employeeId AND (v.vacStartDate <= :endDate AND v.vacEndDate >= :startDate)")
    List<VacationEntity> findOverlappingVacations(@Param("employeeId") Long employeeId,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);

    @Query(value = " SELECT COUNT(*) FROM vacation v WHERE v.vac_start_date <= :date AND v.vac_end_date >= :date AND v.vac_type = '병가'", nativeQuery = true)
    int findBySickVacationPeople(@Param("date")LocalDate now);
}

package org.spring.groupAir.commute.repository;

import org.spring.groupAir.commute.entity.CommuteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Repository
public interface CommuteRepository extends JpaRepository<CommuteEntity, Long> {

    List<CommuteEntity> findByMemberEntityId(Long id);


    @Query(value = " select count(*) from commute where in_time >= :date  and status ='퇴근' " , nativeQuery = true)
    int findByWorkOutPeople(@Param("date") LocalDate now);
    @Query(value = " select count(*) from commute where in_time >= :date  and status ='출근' " , nativeQuery = true)
    int findByWorkPeople(@Param("date") LocalDate now);
    @Query(value = " select count(*) from commute where in_time >= :date  and status ='조퇴' " , nativeQuery = true)
    int findByLeaveEarlyPeople(@Param("date") LocalDate now);
    @Query(value = " select count(*) from commute where in_time >= :date  and status ='지각' " , nativeQuery = true)
    int findByLatePeople(@Param("date") LocalDate now);
}

package org.spring.groupAir.commute.repository;

import org.spring.groupAir.commute.entity.CommuteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Repository
public interface CommuteRepository extends JpaRepository<CommuteEntity, Long> {

    Page<CommuteEntity> findByMemberEntityNameContains(Pageable pageable, String search);

    List<CommuteEntity> findByMemberEntityId(Long id);
}

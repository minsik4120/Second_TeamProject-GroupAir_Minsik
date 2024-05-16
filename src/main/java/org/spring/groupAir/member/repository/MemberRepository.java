package org.spring.groupAir.member.repository;

import org.spring.groupAir.member.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByUserEmail(String userEmail);

    Page<MemberEntity> findByNameContains(Pageable pageable, String search);


    Page<MemberEntity> findByUserEmailContains(Pageable pageable, String search);



}

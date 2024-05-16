package org.spring.groupAir.commute.service.serviceInterface;

import org.spring.groupAir.commute.dto.CommuteDto;
import org.spring.groupAir.member.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommuteServiceInterface {

    Long workIn(Long id);

    Long workOut(Long id);

    List<CommuteDto>  commuteList(Long id);

    void createCommute(Long id);
}

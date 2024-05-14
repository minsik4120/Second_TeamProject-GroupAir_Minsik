package org.spring.groupAir.commute.service.serviceInterface;

import org.spring.groupAir.commute.dto.CommuteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommuteServiceInterface {
    Page<CommuteDto> commuteList(Pageable pageable, String subject, String search);

    void workIn(Long id);

    void workOut(Long id);
}

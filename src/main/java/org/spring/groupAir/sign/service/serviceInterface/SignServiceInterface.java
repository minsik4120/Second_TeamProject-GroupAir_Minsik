package org.spring.groupAir.sign.service.serviceInterface;

import org.spring.groupAir.sign.dto.SignDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SignServiceInterface {


    public int write(SignDto signDto);

//    public List<SignDto> signBox();



//    Page<SignDto> signBox(Pageable pageable);
//
//    SignDto signOne(Long id);
//
//
//  List<SignDto> signSearchList(Pageable pageable, String subject, String search);

    Page<SignDto> signSearchPagingList(Pageable pageable, String subject, String search);

    SignDto signOne(Long id);

    public int deleteSign(Long id);

    void update(SignDto signDto);
}

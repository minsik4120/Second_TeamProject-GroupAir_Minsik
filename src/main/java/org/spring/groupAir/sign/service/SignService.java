package org.spring.groupAir.sign.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.sign.dto.SignDto;
import org.spring.groupAir.sign.entity.SignEntity;
import org.spring.groupAir.sign.entity.SignStatusEntity;
import org.spring.groupAir.sign.repository.SignFileRepository;
import org.spring.groupAir.sign.repository.SignRepository;
import org.spring.groupAir.sign.service.serviceInterface.SignServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SignService implements SignServiceInterface {

    private final SignRepository signRepository;
    private final SignFileRepository signFileRepository;


    @Transactional
    @Override
    public int write(SignDto signDto) {
        SignEntity signEntity = SignEntity.towriteSignEntity(signDto);
        Long id = signRepository.save(signEntity).getId();
        if (id != null) {
            return 1;
        }

        return 0;
    }
//
//    @Override
//    public List<SignDto> signBox() {
//
//        List<SignEntity> signEntities = signRepository.findAll();
//        List<SignDto> signBox = new ArrayList<>();
//
//
//            for (SignEntity signEntity : signEntities) {
//                signBox.add(SignDto.toSelectSignDto(signEntity));
//            }
//            return signBox;
//
//
//
//    }

//    @Override
//    public List<SignDto> signSearchList(Pageable pageable, String subject, String search) {
//        return null;
//    }

    @Override
    public Page<SignDto> signSearchPagingList(Pageable pageable, String subject, String search) {
        Page<SignEntity> signEntities = null;
        if (subject == null || search == null) {
            signEntities = signRepository.findAll(pageable);
        } else {
            if (subject.equals("title")) {
                signEntities = signRepository.findByTitleContaining(pageable, search);
            } else if (subject.equals("approve")) {
                signEntities = signRepository.findByApproveContaining(pageable, search);
            } else {
                signEntities = signRepository.findAll(pageable);
            }
        }
        Page<SignDto> signDtos = signEntities.map(SignDto::toSelectSignDto);


        return signDtos;
    }

    @Override
    public SignDto signOne(Long id) {
        Optional<SignEntity> optionalSignEntity = signRepository.findById(id);
        if (optionalSignEntity.isPresent()) {
            SignEntity signEntity = optionalSignEntity.get();
            SignDto signDto = SignDto.toSelectSignDto(signEntity);
            return signDto;
        }

        return null;
    }

    @Transactional
    @Override
    public int deleteSign(Long id) {
        signRepository.deleteById(id);
        return 0;
    }

    @Override
    public void update(SignDto signDto) {
        SignEntity signEntity =SignEntity.toUpdateEntity(signDto);
        Long id = signRepository.save(signEntity).getId();

        Optional<SignEntity> optionalSignEntity=signRepository.findById(id);
        if(optionalSignEntity.isPresent()){
            System.out.println("수정성공");
    return;
        }
        System.out.println("수정실패");
        throw new IllegalArgumentException("수정실패");




    }


}
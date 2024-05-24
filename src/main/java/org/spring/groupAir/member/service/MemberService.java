package org.spring.groupAir.member.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.dto.MemberFileDto;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.member.entity.MemberFileEntity;
import org.spring.groupAir.member.repository.MemberFileRepository;
import org.spring.groupAir.member.repository.MemberRepository;
import org.spring.groupAir.member.service.memberServiceInterface.MemberServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements MemberServiceInterface {

    private final MemberRepository memberRepository;
    private final MemberFileRepository memberFileRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Page<MemberDto> memberList(Pageable pageable, String subject, String search) {
        Page<MemberEntity> memberEntityPage;

        if (subject == null || search == null) {
            memberEntityPage = memberRepository.findAll(pageable);
        } else if (subject.equals("name")) {
            memberEntityPage = memberRepository.findByNameContains(pageable, search);
        } else if (subject.equals("userEmail")) {
            memberEntityPage = memberRepository.findByUserEmailContains(pageable, search);
        } else {
            memberEntityPage = memberRepository.findAll(pageable);
        }
        Page<MemberDto> memberDtoPage = memberEntityPage.map(MemberDto::toMemberDto);

        return memberDtoPage;
    }

    @Override
    public Long memberJoin(MemberDto memberDto) throws IOException {
        memberRepository.findByUserEmail(memberDto.getUserEmail()).ifPresent(email -> {
            throw new RuntimeException(memberDto.getUserEmail() + " 이메일이 이미 존재합니다!");
        });
        if(memberDto.getMemberFile().isEmpty()){
            MemberEntity memberEntity1=MemberEntity.toMemberJoinEntity0(memberDto, passwordEncoder);
            memberRepository.save(memberEntity1);

            return memberEntity1.getId();
        }else {
            MultipartFile memberFile = memberDto.getMemberFile();
            String oldFileName = memberFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();

            String newFileName = uuid + "_" + oldFileName;
            String filePath = "c:/groupAir/" + newFileName;
            memberFile.transferTo(new File(filePath));

            memberDto.setMemberFileName(newFileName);

            MemberEntity memberEntity1 = MemberEntity.toMemberJoinEntity1(memberDto, passwordEncoder);
            Long memberId = memberRepository.save(memberEntity1).getId();

            MemberEntity memberEntity2 =
                memberRepository.findById(memberId).orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 아이디가 존재하지 않습니다.");
                });

            MemberFileDto memberFileDto =MemberFileDto.builder()
                .memberOldFile(oldFileName)
                .memberNewFile(newFileName)
                .memberEntity(memberEntity2)
                .build();

            MemberFileEntity memberFileEntity=MemberFileEntity.builder()
                .memberEntity(memberFileDto.getMemberEntity())
                .memberOldFile(memberFileDto.getMemberOldFile())
                .memberNewFile(memberFileDto.getMemberNewFile())
                .build();

            memberFileRepository.save(memberFileEntity);

            return memberId;
        }
    }


    
    //sign추가
    @Override
    public Page<MemberDto> findMembersByNameContaining(String name, Pageable pageable) {
        Page<MemberEntity> memberEntities = memberRepository.findByNameContains(pageable, name);
        return memberEntities.map(MemberDto::toMemberDto);
    }


 







    @Override
    public String findName(Long id) {

        String name = memberRepository.findById(id).get().getName();

        return name;
    }

    @Override
    public List<MemberDto> findBujang() {

        String position = "부장";
        List<MemberEntity> memberEntityList
                = memberRepository.findByPositionEntityPositionName(position);

        List<MemberDto> memberDtoList =
                memberEntityList.stream().map(MemberDto :: toMemberDto).collect(Collectors.toList());

        return memberDtoList;
    }

    @Override
    public String findPosition(String name) {

        MemberEntity memberEntity = memberRepository.findByName(name).get();

        String position = memberEntity.getPositionEntity().getPositionName();

        return position;
    }

}

package org.spring.groupAir.member.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.department.entity.DepartmentEntity;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.dto.MemberFileDto;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.member.entity.MemberFileEntity;
import org.spring.groupAir.member.entity.PositionEntity;
import org.spring.groupAir.member.repository.MemberFileRepository;
import org.spring.groupAir.member.repository.MemberRepository;
import org.spring.groupAir.member.service.memberServiceInterface.MemberServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLSession;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

        if (subject != null) {
            if (subject.equals("name")) {
                memberEntityPage = memberRepository.findByNameContains(pageable, search);
            } else if (subject.equals("phone")) {
                memberEntityPage = memberRepository.findByPhoneContains(pageable, search);
            } else if (subject.equals("userEmail")) {
                memberEntityPage = memberRepository.findByUserEmailContains(pageable, search);
            } else {
                memberEntityPage = memberRepository.findAll(pageable);
            }
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
        if (memberDto.getMemberFile().isEmpty()) {
            MemberEntity memberEntity1 = MemberEntity.toMemberJoinEntity0(memberDto, passwordEncoder);
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
            MemberFileDto memberFileDto = MemberFileDto.builder()
                .memberOldFile(oldFileName)
                .memberNewFile(newFileName)
                .memberEntity(memberEntity2)
                .build();

            MemberFileEntity memberFileEntity = MemberFileEntity.builder()
                .memberEntity(memberFileDto.getMemberEntity())
                .memberOldFile(memberFileDto.getMemberOldFile())
                .memberNewFile(memberFileDto.getMemberNewFile())
                .build();

            memberFileRepository.save(memberFileEntity);

            return memberId;
        }
    }

    @Override
    public MemberDto memberDetail(Long id) {

        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 아이디가 없습니다.");
        });
        MemberDto memberDto = MemberDto.toMemberDto(memberEntity);
        return memberDto;
    }

    @Override
    public void memberUpdate(MemberDto memberDto) throws IOException {
        if (memberDto == null || memberDto.getId() == null) {
            throw new IllegalArgumentException("MemberDto 또는 ID가 null입니다.");
        }

        MemberEntity memberEntity = memberRepository.findById(memberDto.getId())
            .orElseThrow(() -> new RuntimeException("해당 아이디가 없습니다"));

        Optional<MemberFileEntity> optionalMemberFileEntity = memberFileRepository
            .findByMemberEntityId(memberDto.getId());

        optionalMemberFileEntity.ifPresent(memberFileEntity -> {
            String newFileName = memberFileEntity.getMemberNewFile();
            String filePath = "c:/groupAir/" + newFileName;
            File deleteFile = new File(filePath);
            if (deleteFile.exists()) {
                if (!deleteFile.delete()) {
                    System.out.println("파일 삭제에 실패했습니다.");
                }
            } else {
                System.out.println("파일이 존재하지 않습니다.");
            }
            memberFileRepository.delete(memberFileEntity);
        });

        String oldPw = memberEntity.getUserPw();
        boolean isPasswordChanged = !memberDto.getUserPw().equals(oldPw);
        boolean isFilePresent = memberDto.getMemberFile() != null && !memberDto.getMemberFile().isEmpty();

        if (isFilePresent) {
            processFile(memberDto); // 이 부분에서 NullPointerException이 발생할 수 있습니다.
        }

        if (isPasswordChanged) {
            memberDto.setUserPw(passwordEncoder.encode(memberDto.getUserPw()));
        }

        //!!!!
        memberDto.setDepartmentEntity(DepartmentEntity.builder().id(memberDto.getDepartmentId()).build());
        memberDto.setPositionEntity(PositionEntity.builder().id(memberDto.getPositionId()).build());
        memberDto.setAddress(memberDto.getAddress());


        if (isFilePresent) {
            memberEntity = MemberEntity.toMemberUpdateEntity1(memberDto);
        } else {
            memberEntity = MemberEntity.toMemberUpdateEntity0(memberDto);
        }

        memberRepository.save(memberEntity);
    }



    private void processFile(MemberDto memberDto) throws IOException {
        if (memberDto.getMemberFile() == null) {
            throw new IllegalArgumentException("MemberDto에 파일이 없습니다.");
        }

        MultipartFile memberFile = memberDto.getMemberFile();
        String oldFileName = memberFile.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid + "_" + oldFileName;

        String savePath = "c:/groupAir/" + newFileName;
        memberFile.transferTo(new File(savePath));

        memberDto.setMemberFileName(newFileName);

        MemberFileDto memberFileDto = MemberFileDto.builder()
            .memberOldFile(oldFileName)
            .memberNewFile(newFileName)
            .memberEntity(memberRepository.findById(memberDto.getId()).orElseThrow(() ->
                new RuntimeException("해당 아이디가 존재하지 않습니다.")
            ))
            .build();

        MemberFileEntity memberFileEntity = MemberFileEntity.builder()
            .memberEntity(memberFileDto.getMemberEntity())
            .memberOldFile(memberFileDto.getMemberOldFile())
            .memberNewFile(memberFileDto.getMemberNewFile())
            .build();

        memberFileRepository.save(memberFileEntity);
    }

    @Override
    public void memberDelete(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(()->{
            throw new IllegalArgumentException("해당 아이디가 없습니다.");
        });

        memberRepository.deleteById(memberEntity.getId());
    }

    public String findName(Long id) {

        String name = memberRepository.findById(id).get().getName();

        return name;
    }

}




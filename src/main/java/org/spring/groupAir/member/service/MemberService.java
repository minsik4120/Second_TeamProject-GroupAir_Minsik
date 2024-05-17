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
    public void memberJoin(MemberDto memberDto) throws IOException {
        memberRepository.findByUserEmail(memberDto.getUserEmail()).ifPresent(email -> {
            throw new RuntimeException(memberDto.getUserEmail() + " 이메일이 이미 존재합니다!");
        });
        if (memberDto.getMemberFile().isEmpty()) {
            MemberEntity memberEntity1 = MemberEntity.toMemberJoinEntity0(memberDto, passwordEncoder);
            memberRepository.save(memberEntity1);
        } else {
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
        MemberEntity memberEntity = memberRepository.findById(memberDto.getId()).orElseThrow(() -> {
            throw new RuntimeException("해당 아이디가 없습니다");
        });
        Optional<MemberFileEntity> optionalMemberFileEntity
            = memberFileRepository.findByMemberEntityId(memberDto.getId());

        if (optionalMemberFileEntity.isPresent()) {
            String newFileName = optionalMemberFileEntity.get().getMemberNewFile();
            String filePath = "c:/e1i4_file/" + newFileName;
            File deleteFile = new File(filePath);
            if (deleteFile.exists()) {
                deleteFile.delete();
            } else {
                System.out.println("파일이 존재하지 않습니다.");
            }
            memberFileRepository.delete(optionalMemberFileEntity.get());
        }
        String oldPw = memberEntity.getUserPw();

        if (memberDto.getMemberFile().isEmpty() && memberDto.getUserPw().equals(oldPw)) {
            memberEntity = MemberEntity.toMemberupdateEntity0(memberDto);
            memberRepository.save(memberEntity);
        } else if (!memberDto.getMemberFile().isEmpty() && memberDto.getUserPw().equals(oldPw)) {
            MultipartFile memberFile = memberDto.getMemberFile();
            String oldFileName = memberFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String newFileName = uuid + "_" + oldFileName;

            String savePath = "c:/e1i4_file/" + newFileName;
            memberFile.transferTo(new File(savePath));

            memberDto.setMemberFileName(newFileName);

            memberEntity = MemberEntity.toMemberupdateEntity1(memberDto);

            Long memberId = memberRepository.save(memberEntity).getId();
            MemberEntity memberEntity1 =
                memberRepository.findById(memberId).orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 아이디가 존재하지 않습니다.");
                });

            MemberFileDto memberFileDto
                = MemberFileDto.builder()
                .memberOldFile(oldFileName)
                .memberNewFile(newFileName)
                .memberEntity(memberEntity1)
                .build();

            MemberFileEntity memberFileEntity = MemberFileEntity
                .builder()
                .memberEntity(memberFileDto.getMemberEntity())
                .memberOldFile(memberFileDto.getMemberOldFile())
                .memberNewFile(memberFileDto.getMemberNewFile())
                .build();

            memberFileRepository.save(memberFileEntity);

        } else if (memberDto.getMemberFile().isEmpty() && !memberDto.getUserPw().equals(oldPw)) {

            String newPw = passwordEncoder.encode(memberDto.getUserPw());

            memberDto.setUserPw(newPw);
            memberEntity = MemberEntity.toMemberupdateEntity0(memberDto);
            memberRepository.save(memberEntity);
        } else if (!memberDto.getMemberFile().isEmpty() && !memberDto.getUserPw().equals(oldPw)) {

            MultipartFile memberFile = memberDto.getMemberFile();
            String oldFileName = memberFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String newFileName = uuid + "_" + oldFileName;

            String savePath = "c:/e1i4_file/" + newFileName;
            memberFile.transferTo(new File(savePath));

            memberDto.setMemberFileName(newFileName);

            String newPw = passwordEncoder.encode(memberDto.getUserPw());

            memberDto.setUserPw(newPw);

            memberEntity = MemberEntity.toMemberupdateEntity1(memberDto);

            Long memberId = memberRepository.save(memberEntity).getId();

            MemberEntity memberEntity1 = memberRepository.findById(memberId).orElseThrow(() -> {
                throw new RuntimeException("해당 아이디가 존재하지 않습니다.");
            });
            MemberFileDto memberFileDto
                = MemberFileDto.builder()
                .memberOldFile(oldFileName)
                .memberNewFile(newFileName)
                .memberEntity(memberEntity1)
                .build();

            MemberFileEntity memberFileEntity = MemberFileEntity
                .builder()
                .memberEntity(memberFileDto.getMemberEntity())
                .memberOldFile(memberFileDto.getMemberOldFile())
                .memberNewFile(memberFileDto.getMemberNewFile())
                .build();

            memberFileRepository.save(memberFileEntity);

        }
    }



}




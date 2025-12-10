package com.solo.practice.member.service;

import com.solo.practice.exception.BusinessLogicException;
import com.solo.practice.exception.ExceptionCode;
import com.solo.practice.member.entity.Member;
import com.solo.practice.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findVerifiedMember(long memberId){

        Optional<Member> optionalMember = memberRepository.findById(memberId);

        Member findMember = optionalMember.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    public void verifyExistsEmail(String email){

        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if(optionalMember.isPresent()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }

    public void verifyExistsNickname(String nickname){

        Optional<Member> optionalMember = memberRepository.findByNickname(nickname);

        if(optionalMember.isPresent()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_NICKNAME_EXISTS);
        }
    }

    public Member createMember(Member member){

        verifyExistsEmail(member.getEmail());
        verifyExistsNickname(member.getNickname());

        return memberRepository.save(member);
    }

    public Member updateMember(Member member){

        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getNickname())
                .ifPresent(nickname -> {
                    if(!nickname.equals(findMember.getNickname())){
                        verifyExistsNickname(nickname);
                    }
                    findMember.setNickname(nickname);
                });

        return memberRepository.save(findMember);
    }

    public Member findMember(long memberId){

        return findVerifiedMember(memberId);
    }

    public void deleteMember(long memberId){

        Member findMember = findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }
}

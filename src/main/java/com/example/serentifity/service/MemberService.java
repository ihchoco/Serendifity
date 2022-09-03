package com.example.serentifity.service;

import com.example.serentifity.Repository.MemberRepository;
import com.example.serentifity.Repository.MemoryMemberRepository;
import com.example.serentifity.domain.Member;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /**
     * 회원 가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 X
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { //Optional이기 때문에 사용 가능
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMEmbers(){
        return memberRepository.findAll();
    }

   public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
   }
}

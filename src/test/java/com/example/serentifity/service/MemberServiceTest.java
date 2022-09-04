package com.example.serentifity.service;

import com.example.serentifity.Repository.MemberRepository;
import com.example.serentifity.Repository.MemoryMemberRepository;
import com.example.serentifity.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository(); //이렇게 생성하면 전혀 다른 객체를 한번 더 생성하는거라서 어딘가 문제발생 가능
    //MemberService에서 사용하는 memberRepository와 MemberServiceTest에서 사용하는 memberRepository 두개 존재(서로 다른 객체)

    //해결방법 : 같은 인스턴스를 쓰게 하기 위해서
    //1. MemberService에 생성자를 만들때 Repository 객체를 넣어주는 방식으로 변경
    //2. @BeforeEach를 사용해서 Repository 객체를 넣어주기

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { //테스트는 한국말로 적어도 괜찮다
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //테스트는 정상적으로 동작하는것도 중요하지만 아닌 예외상황을 만드는것도 중요하다
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        /* try catch 대신에 다른 문법 사용
        try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
         */
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
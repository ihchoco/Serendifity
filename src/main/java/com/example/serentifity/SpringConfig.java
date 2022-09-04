package com.example.serentifity;

import com.example.serentifity.Repository.MemberRepository;
import com.example.serentifity.Repository.MemoryMemberRepository;
import com.example.serentifity.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}

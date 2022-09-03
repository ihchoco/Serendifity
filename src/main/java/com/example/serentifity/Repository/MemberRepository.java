package com.example.serentifity.Repository;

import com.example.serentifity.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Optional은 자바 8에 들어간 기능으로 없으면 Null이 나올수 있다는 것
    Optional<Member> findByName(String name);
    List<Member> findAll();
}



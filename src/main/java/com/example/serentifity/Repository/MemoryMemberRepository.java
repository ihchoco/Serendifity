package com.example.serentifity.Repository;

import com.example.serentifity.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //KEY = 회원의 ID를 할거라서 Long, 값은 Member
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //만약에 값이 없으면 Null로 보내준다
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();
        //loop를 돌면서 name이 일치하는 값들을 찾으면 return 해주는것
        //만약에 없으면 Optional에 null을 넣어서 반환하게 된다
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}

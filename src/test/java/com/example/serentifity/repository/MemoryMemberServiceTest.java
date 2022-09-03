package com.example.serentifity.repository;

import com.example.serentifity.Repository.MemberRepository;
import com.example.serentifity.Repository.MemoryMemberRepository;
import com.example.serentifity.domain.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberServiceTest {

        MemoryMemberRepository repository = new MemoryMemberRepository();

        @AfterEach
        public void afterEach(){
            repository.clearStore();
        }

        @Test
        public void save(){
            Member member = new Member();
            member.setName("spring");

            repository.save(member);
            Member result = repository.findById(member.getId()).get(); //Optional 반환이라서 .get()을 사용할 수 있음

            System.out.println("result = "+(result == member));

            //Assertions.assertEquals(member, result); //두개의 값이 같으면 그냥 지나가고 다를경우에는 빨간색으로 표시
            Assertions.assertThat(member).isEqualTo(result); //위 아래 결과는 동일하지만 요즘은 이 방식을 더 자주 사용한다.

        }

        @Test
        public void findByName(){
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);

            Member result = repository.findByName("spring1").get();

            Assertions.assertThat(result).isEqualTo(member1);
        }

        @Test
        public void findAll(){
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);

            List<Member> result = repository.findAll();

            Assertions.assertThat(result.size()).isEqualTo(2);


        }
}

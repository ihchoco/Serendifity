package com.example.serentifity.Repository;

import com.example.serentifity.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{ //첫번째 T : Entity, 두번째 Key : key 값의 타입(Long)

    //JPQL : select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);

}

//지금 Interface만 있고 구현체도 없고 아무것도 없는데 끝?! 이게 끝..

//이렇게 JpaRepository를 상속받는 Interface를 만들게 되면 자동으로 스프링에서 구현체를 자동으로 만들어서 등록을 해주기 때문에 우리는 그걸 가져다 사용하기만 하면 된다.

//사용법은 SpringConfig에 가서 MemberRepository 변수를 만들어서 사용해주면 끝


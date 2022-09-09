package com.example.serentifity.Repository;

import com.example.serentifity.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
public class JpaMemberRepository implements MemberRepository{

    //아까 build.gradle에 JPA를 추가해두었으면 스프링이 기동될 때 자동으로 EntityManager bean객체를 만들어서 Repository에 주입해준다
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //이렇게 하면 JPA가 Insert Query를 만들어서 자동으로 동작시켜준다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result =  em.createQuery("select m from member m", Member.class).getResultList();
        return result;
    }

    //저장, 조회, 수정, 삭제는 자동으로 JPA가 해주니까 따로 작성필요 없다

    //PK기반이 아닌 나머지것들을 이용할 때는 위와 같이 작성해주어야 한다

    //JPA는 모든 작업이 Transactional 안에서 수행되어야 한다. 특정 부분에만 @Transactional 어노테이션 추가가 가능하지만 클래스 위에 전체에 적용도 가능

}

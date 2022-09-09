package com.example.serentifity.domain;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에 Insert할 때 자동으로 key값이 +1 되면서 DB에서 자동으로 추가되는 전략을 IDENTITY 전략이라고 부른다
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

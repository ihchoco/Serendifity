drop table if exists member CASCADE;

create table member
(
    id bigint generated by default as identity, -- generated by default as identity = 여기에 값을 입력하지 않으면 DB가 알아서 값을 채워준다
    name varchar(255),
    primary key(id)
);

insert into member(name) values('spring');
insert into member(name) values('spring2');

select * from member;
# 일정 관리 앱

lv에 맞는 요구사항에 따라, lv0 ~ lv6 까지 진행가능한 과제

---
---

## api 명세

| 기능             | method | CRUD   | url            | request                                                                                | response | 상태 코드   |
|----------------|--------|--------|----------------|----------------------------------------------------------------------------------------|----------|---------|
| schedule 생성    | POST   | CREAT  | /schedule      | {"author":author,"contents":contents,"password":password}                              |ResponseEntity<ResponseDto>  | OK |
| schedule 전체 조회 | GET    | READ   | /schedule      | x                                                                                      | ResponseEntity<List<ResponseDto>> | OK|
| schedule 단건 조회 | GET    | READ   | /schedule/{id} | x                                                                                      | ResponseEntity<ResponseDto> | OK|
| schedule 단건 수정 | PATCH  | UPDATE | /schedule/{id}   | {"author":author,"contents":contents,"password":password}  ResponseEntity<ResponseDto> | OK|
| schedule 단건 삭제 | DELETE | DELETE | /schedule/{id} | x                                                                                      | ResponseEntity<ResponseDto> | OK|

---
---

## ERD 
![schedule](https://github.com/user-attachments/assets/6421fe3b-8481-40bf-9654-e9245ec753c3)

---
---

## SQL 

create table schedule
(
id       bigint auto_increment comment 'id pk'
primary key,
author   varchar(100) null comment '작성자 명',
comments varchar(100) null comment '할 일',
flexDate datetime     null comment '수정일',
fixDate  datetime     null comment '작성일',
password varchar(100) null comment '비밀번호'
);

---
---

## 기능 별 이슈 git에 정리

## 제출 상황

스프링 이해 부족 및 새로운 객체 메서드 사용에 어려움, 이슈 별 브랜치를 나누어, git 관리 등에서 생각보다 많은 문제에 부딪혀 lv2에서 멈추고,

남은 시간은 http 에 대한 공부와 스프링에 대한 공부를 진행하였습니다.

## 참고 사항 및 회고

- git 컨벤션을 이번엔 더 신경을 써보았는데, fix add update 를 주로 쓰는 것과 생각보다 코드를 작성하다보면 저장할 타이밍을 놓쳐서, 다른 부분까지 한번에 commit 하는 경우가 대다수였다.

- branch를 나누어 이슈 별 commit을 진행 하였는데, 처음부터 잘 못됬음을 느꼈다. 우선 main은 초기 상태에서 가지를 뻗고, 해당 가지들은 웬만해서 main에 안합치는 것이 좋은 거 같다고 느꼈다.

이미 merge를 했는데 '아차! ~~를 빼먹었네' 하는 경우가 대다수였던거 같다. 그래서 다음에는 초기의 main의 상태에서 비슷 혹은 같은 기능별로 가지를 나누어, 해당 기능 혹은 역할 혹은 이슈는 거기안에서 만 하게끔 해야겠다.

- 중간 중간에 til을 대신한다는 느낌으로 주석을 많이 달았습니다. 굳이 이상한 객체 메서드를 채택하여, 삽질하는 것도 있고 보기 불편한 요소가 많이 있지만,

다시 이 과제를 볼 때에, 무슨 생각으로 만들었는지 기록용으로 써보았으니 양해 부탁드립니다.


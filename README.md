# DoItCoding_Final

### :alarm_clock: 개발기간
- 2023.02.15 - 2023.03.06


### 👥 멤버구성
 - 김고운
 - 신윤경
 - 이명진
 - 유근형
 - 조영민
 - 황은선
<hr>

### ⚙️ 개발환경
 - java 17
 - DataBase : ORACLE
 - ORM : MyBatis, JPA
 - Framework : SpringBoot(3.0)
 - IDE : IntelliJ Ultimate 2022

<hr>

### 🌊 서비스 흐름도

<img width="441" alt="image" src="https://user-images.githubusercontent.com/49307938/223932828-eabd4587-05dc-44ad-9d97-2573c4c39215.png">

<hr>

### ㏈ ER 다이어 그램

<img width="425" alt="image" src="https://user-images.githubusercontent.com/49307938/223933230-ee8d8a15-c7ec-481f-99f0-2ce9decdf12b.png">

<hr>

### 프로젝트 담당기능 및 코드
<details>
<summary>메인 화면 구성 (시간, 카테고리, 랭킹별 상영작 출력)</summary>
<div markdown="1">



</div>
</details>

<details>
<summary>검색 기능 구현</summary>
<div markdown="1">



</div>
</details>

<details>
<summary>카테고리별 상영작 출력</summary>
<div markdown="1">



</div>
</details>

<details>
<summary>로그인</summary>
<div markdown="1">



</div>
</details>

<details>
<summary>회원가입 기능 일부 구현(정규화, 아이디 중복확인, 비밀번호 일치 불일치)</summary>
<div markdown="1">



</div>
</details>

<details>
<summary>관리자 페이지 전담</summary>
<div markdown="1">



</div>
</details>

<details>
<summary>mypage에서 qna 목록 </summary>
<div markdown="1">



</div>
</details>


<hr>

### 주요 문제 해결기록
<details>
<summary>페이징 처리를 위한 모듈화</summary>
<div markdown="1">



</div>
</details>

<details>
<summary>카카오 api 활용하여 주소 위도 경도값 로자동으로 가져오기</summary>
<div markdown="1">



</div>
</details>

<details>
<summary>JPA? Mybatis? 무엇을 선택할까</summary>
<div markdown="1">



</div>
</details>


<hr>

### ✏️ 프로젝트 기록 (유근형) 

#### main, search, category, admin, myPage 작업. 그 외 login, regist 기능 일부 구현.

<br>

* 23.02.17-20
  * main.html을 Spring으로 옮기기 (dao, entity, service, mapper 작성)
  * 카테고리, 시간 별로 Ticket을 출력하는 경우 쿼리문이 복잡하기 때문에 jpa가 아니라 mybatis로 작성.
  
- 23.02.21
  - Spring으로 main과 search를 구현하는 중. 2차 프로젝트 때 했던 페이지를 Spring으로 다시 구현하는 중.
    - Spring에서 Ajax를 하는 작업을 하고 있다. sql문이 복잡한 게 있어서 jpa보다 mybatis로 작업했다.
    - Controller에서 @Resposebody를 통해 통신을 하면 Json 형태로 자동 변환해주기 때문에 Ajax 통신이 손쉬워진다.
    - 오늘은 그 방법을 모색하느라 시간이 가서 main의 랭킹 출력밖에 못 했다. 더군다나 review가 없는데 출력하는 바람에 안 되는 걸 몰라서 1시간 가량 헤맸다. 뭘 테스트하든지 데이터 삽입을 먼저 하자.
    - IntelliJ에서 CSS와 이미지 파일을 불러오기 위해서는 static 폴더에 넣어야 하는 것, 또한 html에서 해당 파일들의 경로를 지정할 때는 static를 쓰지 않아도 된다는 걸 알았다.


+ 23.02.22
  + Spring으로 main, search, category 구현 완료. 모두 복잡한 sql문이 있어서 mybatis로 작업.
  + Spring에서 Ajax 하는 방법을 찾았다. 모든 페이지가 비슷해서 손쉽게 할 수 있었다.
  + mypage 작업만 하면 된다.

* 23.02.23-24 
  * 관리자 페이지를 작성 완료
    * 관리자 페이지에서는 작품과 고객 정보를 관리할 수 있다. 작품을 등록, 수정, 삭제하거나 고객 정보를 수정, 삭제, 내역 확인 등을 할 수 있다.

+ 23.02.24-25
  + 관리자 페이지의 insertTicket, updateTicket, listTicket 작업의 대부분을 완료
  + 남은 거 : 페이징 처리 + 주소값 검색하면 자동으로 위도, 경도 불러오기, 고객 정보 페이지
  + 고객 정보 페이지에서의 기능들
    + 회원 목록 출력
    + 회원 검색
    + 회원 정보 출력
    + 회원 검색
    + 회원이 작성한 qna 목록 출력
    + 회원이 예매한 내역 확인
    + 회원 정보 수정
    + 회원 삭제
    
- 23.02.26
  - 고객 정보 페이지 기능 구현
    - 페이징 처리 완료 (paging 파일을 만들어 다른 곳에서도 페이징할 때 쓸 수 있도록 모듈화)
    - 검색 기능 완료
    
   
* 23.02.27-28
  * 회원 목록, 정보, qna, 예매내역 출력 완료
  * 정보 수정,삭제 완료
  * ticket, customer에서 정보 입력, 수정할 때 카카오 지도 나오기 완료.
  * 지도에서 주소창 입력하면 자동으로 위도랑 경도 값 입력되도록 하기 왼료.
    * 대신 주소 입력창과 주소 검색창이 달라서 이름은 수동으로 설정해야한다.
    
+ 23.03.02
  + category 페이지에서 ticket 목록이 무한스크롤로 뜨게 하기 완료.
  * admin 페이지의 ticket, customer 목록 정렬하기 완료.
  
+ 23.03.03-04
  + myPage에서 qna 내역 출력 완료.
  + myPage에서 qna 목록 보기 완료.
  + myPage에서 qna 목록 페이징, 정렬하기 완료.

- 23.03.05-06
  - 남아있는 문제점
    - category
      - ticketid 0인것 출력하지 않기
      - 콘서트 인스턴스 제목 수정
      - 톡 상담 작동
    - admin/listTicket
      - 마지막에 내용 없는 페이지 나옴
    - admin/updateTicket
      - 주소 검색창이 없음
      - main 이미지 엑박
      - 원래 있던 이미지 다른 걸로 갈아끼우면 이미지 안 나옴  -> 경로 문제 (images/ticket으로 업로드 됨) 해결 O
    - admin/updateCustomer
      - 수정이 안됨. -> html form의 action 경로가 이상했음 O
    - admin/listCustomer
      - 자식레코드가 있으면 에러페이지 뜸. -> DB에서 custid를 참조하는 테이블들에 cascade 설정하면 된다.

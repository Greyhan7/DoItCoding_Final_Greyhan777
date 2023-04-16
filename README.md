# T-Catch 예매 사이트

### 📓 프로젝트 설명
인터파크 티켓, Yes24 티켓 등과 같이 영화 시사회, 뮤지컬, 연극, 콘서트를 예매할 수 있는 종합 예매 사이트입니다.

상영일 기준 2주 이내의 상영작을 현재 상영작으로 정의하여 해당 상영작을 예매할 수 있습니다. 

또한 매진이 된 상영작의 경우, 취소표에 한해 대기표를 신청할 수 있는 드로우(Draw) 기능이 구현되어 있습니다. 드로우는 상영 전날 신청한 인원에 한해 랜덤으로 추첨이 이루어집니다.

그 외 주요 구현 기능은 다음과 같습니다.

- 회원가입 및 로그인, 아이디 비밀번호 찾기(인증코드 이메일, 핸드폰으로 검증).
- 5점 만점으로 리뷰 남기기.
- 리뷰 점수를 바탕으로 메인 화면에서 카테고리 별로 랭킹 3위까지 출력.
- 카카오 챗봇을 통한 간단한 상담 (현재 불가능)
- 일반적인 사항 혹은 사용자가 예매한 상영작에 대한 1대1 문의 (비밀 글 처리 가능).
- 관리자 페이지에서 상영작, 회원 정보 관리 (입력, 수정, 삭제 등)
- 관리자가 공지사항 작성, 사용자가 작성한 1대1 문의 답변 (답변 시 사용자 메인 화면에 알람이 생성됨)

### :alarm_clock: 개발기간
- 2023.02.15 - 2023.03.06 (20일)


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
 - DataBase : ORACLE 21c
 - ORM : MyBatis, JPA
 - Framework : SpringBoot(3.0)
 - View : HTML5, CSS3, JavaScript, Thymeleaf
 - IDE : IntelliJ Ultimate 2022

<hr>

### 🌊 서비스 흐름도

<img width="841" alt="image" src="https://user-images.githubusercontent.com/97737386/229759345-d6083d7b-17ca-4515-bfa6-0e26533c5371.png">

<hr>

### ㏈ ER 다이어 그램

<img width="425" alt="image" src="https://user-images.githubusercontent.com/49307938/223933230-ee8d8a15-c7ec-481f-99f0-2ce9decdf12b.png">

<hr>

### 💻 프로젝트 담당기능 및 코드
<details>
<summary>1. 메인 화면 구성 (시간, 카테고리, 랭킹별 상영작 출력)</summary>
<div markdown="1">
<br>

#### 1-1.메인화면

<img width="725" alt="image" src="https://user-images.githubusercontent.com/99037697/230832571-dacda879-e773-4292-bb7f-a390de2894d8.png">

<br>

#### 1-2. 장르별 랭킹, 시간대별 상영작

<img width="725" height="500" alt="image" src="https://user-images.githubusercontent.com/99037697/230832844-1e3046ea-44eb-4f5c-b2d5-ad1b2d50a06c.png">

 :pushpin: [코드 확인](https://github.com/Greyhan7/DoItCoding_Final_Greyhan777/blob/0b4a78125f478064a2915a2f362bb277404a056c/src/main/resources/templates/main.html#L75)

- cateid에 따라 장르별로 다르게 출력되도록 정의.
- time 변수를 정의하여 각각 값이 0, 1, 2일떄 과거, 현재, 미래 상영작을 출력.
- 상영일이 현재 날짜보다 과거면 과거 상영작, 현재 날짜 ~ 현재 날짜+14일이면 현재 상영작, 현재 날짜+14일보다 크면 미래 상영작으로 mapper에서 sql문 정의.
- 만약 해당 조건의 상영작이 3개 이상일 때, '다음으로' 버튼을 누르면 그 다음에 해당하는 상영작들이 출력된다.
  - 끝까지 모두 출력되었을 때 해당 카테고리 버튼을 클릭하거나, '다음으로' 버튼을 다시 클릭하면 맨 처음에 나왔던 상영작들이 출력된다.

```html
<select id="findAllTicketByCategory" resultType="ticketVO">
    select * from ticket where cateid=#{cateid} and
    <if test="time==0">
      ticket_date &lt; to_char(sysdate, 'yyyy/mm/dd')
    </if>
    <if test="time==1">
      ticket_date &gt; to_char(sysdate, 'yyyy/mm/dd') and ticket_date &lt;= to_char(sysdate+14, 'yyyy/mm/dd')
    </if>
    <if test="time==2">
      ticket_date &gt; to_char(sysdate+14, 'yyyy/mm/dd')
    </if>
  </select>

```

- 작동화면
 <img width="725" height="500" alt="image" src="https://user-images.githubusercontent.com/99037697/230836317-409dde59-7832-4bc9-a9fa-049f08cd2dce.gif">

</div>
</details>

<details>
<summary>2. 검색 & 카테고리 페이지 (js파일로 모듈화)</summary>
<div markdown="1">
<br>

- 모든 페이지에 공통적으로 들어가는 기능이기 때문에 유지보수 용이를 위해 CategoryNavSearch.js 파일로 분리하여 모듈화하였음.
:pushpin: [코드확인](https://github.com/Greyhan7/DoItCoding_Final_Greyhan777/blob/a1d6a664e70aed9ddb54071ef82c40b54f53f8a0/src/main/resources/static/js/ticket/CategoryNavSearch.js#L1)


#### 2-1. 검색 페이지
 
 - 작품명과 출연자 이름을 토대로 검색 결과 출력.
 ```html
 <select id="findSearchTicket" resultType="ticketVO">
    select * from ticket where ticket_name like '%'||#{keyword}||'%' or cast like '%'||#{keyword}||'%'
  </select>
 ```
 
 <img width="725" height="500" alt="image" src="https://user-images.githubusercontent.com/99037697/231095912-8b345be8-95d3-4a21-97e3-38647922a1be.gif">

<br>

#### 2-2. 카테고리별 상영작 출력 (무한 스크롤)
 
 - 내비게이션 바에서 카테고리 클릭시 해당 작품 출력.
 - 예매 사이트 특성상 사용자가 편하게 아이쇼핑하는 경험을 할 수 있도록 상영작 목록을 볼 때 페이지를 일일히 누르기보다 무한 스크롤 방식을 채택하였음.
 - scroll이 하단에 위치하는 순간 page가 증가하도록 설정.

 <img width="725" height="500" alt="image" src="https://user-images.githubusercontent.com/99037697/231096263-e84b5f0e-772d-42d4-b90a-4cd45dcef4c6.gif">

</div>
</details>


<details>
<summary>3. 로그인, 회원가입 기능 일부 구현</summary>
<div markdown="1">
<br>

- 회원가입 시 아이디 중복체크, 비밀번호 일치-불일치, 비밀번호 조건 (정규화) 등 작업.

</div>
</details>


<details>
<summary>4. 관리자 페이지 전담</summary>
<div markdown="1">
<br>

<img width="725" height="500" alt="image" src="https://user-images.githubusercontent.com/99037697/231105156-2f77c494-cc4b-48fe-91d5-ebae62c5c76f.png">

- mainAdmin 페이지를 통해서 고객관리, 상영작 관리, 공지사항 관리 페이지로 접속 가능.
- 정석적인 CRUD, 페이징 처리, 정렬, 검색 기능을 가진 두 게시판.
- Thymeleaf를 통해 프론트 구현 및 페이징 처리, 정렬, 검색 기능 활용.

- :pushpin: [Controller 코드 확인](https://github.com/Greyhan7/DoItCoding_Final_Greyhan777/blob/a1d6a664e70aed9ddb54071ef82c40b54f53f8a0/src/main/java/com/example/finalpro/controller/AdminController.java)
- :pushpin: [View 코드 확인](https://github.com/Greyhan7/DoItCoding_Final_Greyhan777/blob/adcfbb19db9c44741fc87fc67c10323d4d1d1c95/src/main/resources/templates/admin/customer/list.html)


#### 4-1. 고객 관리 페이지

<img width="725" alt="image" src="https://user-images.githubusercontent.com/99037697/231105715-4a9e5200-d4ba-4fc3-8a9f-de9ed4654a25.png">

- id, 이름, 생년월일, 성별에 따라 정렬.
- id와 이름을 기준으로 검색 가능.
- 사용자가 예매한 상영작, 작성한 qna 내역 조회 가능.
- 사용자 아이디를 입력하면 사용자의 정보를 수정할 수 있고, 삭제를 누르면 사용자 정보가 삭제된다.

<br>
 
 - 고객 페이지에서 검색 기능 작동화면

 <img width="725" alt="image" src="https://user-images.githubusercontent.com/99037697/231107730-981fad77-0356-4fb0-b92d-a08bd8539ba5.gif">

<br>
 
 - 고객 페이지에서 qna 작성 내역 확인 작동화면

<img width="725" alt="image" src="https://user-images.githubusercontent.com/99037697/231107747-9cc5cd1d-47d9-42ca-9833-ecb137c80681.gif">


#### 4-2. 작품 관리 페이지

<img width="725" alt="image" src="https://user-images.githubusercontent.com/99037697/231107846-cf110d60-cb9d-4de4-a016-61d40e07a181.png">

- 상영작 정보를 조회, 검색, 추가 ,수정하거나 삭제할 수 있음.

<br>
 
 - 작품 추가 작동화면

<img width="725" alt="image" src="https://user-images.githubusercontent.com/99037697/231112469-8ddd1f69-a684-495f-8df9-bcc44557557c.gif">

<br>

#### 4-3. 공지사항 페이지
<br>

- 관리자 페이지에서 공지사항 목록 페이지로 이동 가능.

</div>
</details>

<hr>

### ✂️ 주요 문제 해결기록
<details>
<summary>중복 페이징 처리를 위한 모듈화</summary>
<div markdown="1">
<br>
 
다양한 페이지에서 페이징 처리를 구현하기 때문에 Paging.java 파일을 만들어 공통되게 설정할 수 있도록 모듈화하였습니다.

페이지마다 출력되는 레코드의 숫자가 동일하기 때문에 유지보수 측면에서 용이해졌습니다.

:pushpin:[코드확인](https://github.com/Greyhan7/DoItCoding_Final_Greyhan777/blob/a1d6a664e70aed9ddb54071ef82c40b54f53f8a0/src/main/java/com/example/finalpro/function/page/Paging.java)

</div>
</details>

<details>
<summary>카카오 api 활용하여 주소 위도 경도값 자동으로 가져오기</summary>
<div markdown="1">
<br>
 
상영작 상세 페이지에서 상영하는 장소의 지도를 표시해야해서 해당 장소에 대한 위도와 경도 정보를 필요했는데, 이를 일일히 입력하는 게 번거로웠습니다.

따라서 카카오 맵 api를 활용하여 input에 장소명을 입력하면 위도와 경도가 자동으로 기입되도록 설정하였습니다.

:pushpin:[코드확인](https://github.com/Greyhan7/DoItCoding_Final_Greyhan777/blob/a1d6a664e70aed9ddb54071ef82c40b54f53f8a0/src/main/resources/templates/admin/ticket/insertTicket.html#L55)

</div>
</details>

<details>
<summary>중복되는 코드 간략화 및 기능 추가</summary>
<div markdown="1">
<br>
 main 페이지에서 시간, 랭킹, 카테고리별 상영작을 출력하는 데에 지나치게 중복된 코드를 작성한 것을 간략화하였습니다.
 
 그 결과, 같은 기능을 하면서도 훨씬 가독성 좋은 코드를 완성할 수 있었습니다.
 
 또한 프로젝트 완성 시점에서는 현재, 미래 상영작이 3개 이상임에도 순서대로 출력되는 3개의 상영작만 확인 가능하였는데
 
 이를 보완하여 '다음으로' 버튼을 누르면 나머지 상영작들이 출력될 수 있도록 코드를 수정하였습니다 (현재 미래 상영작만 적용).
 
 :pushpin:[코드확인](https://github.com/Greyhan7/DoItCoding_Final_Greyhan777/blob/b2c4f4bb12c434ee1e4180593366df6e9161cbdc/src/main/resources/templates/main.html#L144)
 
  <details>
  <summary>이전 코드</summary>
  <div markdown="1">
     
      // 랭킹에서 카테고리 클릭
      $(document).on('click', '#rank_cate1', function(){
        cateid =1;
        selectRankingBycategory();
      });
      $(document).on('click', '#rank_cate2', function(){
        cateid =2;
        selectRankingBycategory();
      });
      $(document).on('click', '#rank_cate3', function(){
        cateid =3;
        selectRankingBycategory();
      });
      $(document).on('click', '#rank_cate4', function(){
        cateid =4;
        selectRankingBycategory();
      });


      // 현재 상영작 카테고리 클릭
      $(document).on('click', '#current_cate1', function(){
        cateid = 1;
        selectCurrentBycategory();
      })
      $(document).on('click', '#current_cate2', function(){
        cateid = 2;
        selectCurrentBycategory();
      })
      $(document).on('click', '#current_cate3', function(){
        cateid = 3;
        selectCurrentBycategory();
      })
      $(document).on('click', '#current_cate4', function(){
        cateid = 4;
        selectCurrentBycategory();
      })

      // 미래 상영작 카테고리 클릭
      $(document).on('click', '#future_cate1', function(){
        cateid = 1;
        selectFutureBycategory();
      })
      $(document).on('click', '#future_cate2', function(){
        cateid = 2;
        selectFutureBycategory();
      })
      $(document).on('click', '#future_cate3', function(){
        cateid = 3;
        selectFutureBycategory();
      })
      $(document).on('click', '#future_cate4', function(){
        cateid = 4;
        selectFutureBycategory();
      })
     

  </div>
  </details>
  
  <details>
  <summary>수정 코드</summary>
  <div markdown="1">
  
  
      // '다음으로' 버튼을 누르면 Ajax 호출할 때 다음 3개 더 출력하게 하기
      // slide_future : 페이지 번호 개념.
      // slide_future_index : slide_future, 즉 해당 페이지에서 출력하는 마지막 index번호
      // total_length : Ajax로 출력하는 레코드의 총 숫자 (만약 slide_future_index가 total_length보다 크면 다시 slide_future를 1로 초기화한다)
      let slide_future = 1;
      let slide_future_index = 3;
      let total_length;

      // '다음으로' 버튼을 클릭하면 다음 3개의 상영작을 출력하기
      $(document).on('click', '#btn_next', function(){
      slide_future++;
      slide_future_index = slide_future * 3;
      selectFutureBycategory(slide_future_index);
      });
  
       $(document).on('click', '.preview', function(){
          // match(/\d+/)[0] : 정규표현식을 사용하여 'id'에서 숫자인 것을 추출한 후 그 중 첫번째 숫자([0])를 가져온다
          cateid = $(this).attr("id").match(/\d+/)[0];
          let id = $(this).attr("id");
          slide_future = 1;
          slide_future_index = 3;
        
          switch (id){
            case "rank_cate"+cateid :
              selectRankingBycategory();
              break;
            case "current_cate"+cateid:
              selectCurrentBycategory();
              break;
            case "future_cate"+cateid:
              selectFutureBycategory(slide_future_index);
              break;
          }
        });
 
  </div>
  </details>
 
</div>
</details>

<hr>


### 프로젝트 회고 및 부족한 점

#### 1. CS이론 및 기반지식의 부족함
Springboot를 통해 CRUD, 페이징, 정렬 등 기초적인 실습은 완료했으나 어떤 원리로 기능하는지에 대해선 이해가 부족하다. 마치 세탁기를 표준세탁으로 돌릴 줄은 알지만 그 세탁기가 어떤 원리로 움직이는지에 대해선 잘 모르는 상태와 같다. 

따라서 앞으로의 학습을 통해 컴퓨터 프로그래밍이 어떤 원리로 돌아가는지, Java와 Spring이 어떤 구조로 동작하는지에 대한 이해 학습이 필요하다.

#### 2. 코드의 근거를 꼭 기록하자
프로젝트 도중 일자별 기록은 남겼으나 구체적인 과정에서의 어려움에 대해선 시간적인 이유라는 핑계로 자세히 남기질 못했다. 이에 프로젝트 종료 후 내가 작성한 코드의 이유, 어떤 어려움을 겪었고 이를 극복하기 위해 이런 식으로 코드를 작성하였는지에 대해 정확하게 기억하지 못하는 일이 벌어졌다. 

기능이 작동하면 그만이라는 마음가짐을 버리고 앞으로의 발전을 위해 스스로 작성한 코드의 근거를 기록할 필요가 있다.

#### 3. 개발자만의 소통 방식 - 클린코드와 각주의 필요성
개발자의 소통은 단순히 대화로 이루어지는 게 아니라 다른 사람이 보더라도 쉽게 이해할 수 있는 코드와 해당 코드에 대한 설명, 즉 각주로도 이루어진다는 걸 알았다. 내가 작성한 코드가 제대로 작동한다는 걸 다른 사람에게 증명하기 위해 노력해야 한다. 코드는 나만의 것이 아니라 다른 사람과의 협업 커뮤니케이션이기도 하다.

#### 4. 초기 설계의 중요성
프로젝트 도중에 몇 가지 변동사항이 생겨 db를 수정하는 일이 있었다. 이에 따라 레코드를 전부 다 삭제하고 처음부터 다시 만들면서 비효율적인 진행이 이뤄졌다. 

프로젝트를 기획할 때 가능한 변동사항이 없도록 철저하게 설계를 해야 나중에 고생하지 않는다는 교훈을 얻었다.


### ✏️ 프로젝트 기록 (유근형) 

#### main, search, category, admin, myPage 작업. 그 외 login, regist 기능 일부 구현.

<br>

<details>
<summary>일자별 기록</summary>
<div markdown="1">

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

</div>
</details>



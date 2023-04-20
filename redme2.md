# T-Catch 예매 사이트

### 📓 프로젝트 설명
인터파크 티켓, Yes24 티켓 등과 같이 영화 시사회, 뮤지컬, 연극, 콘서트를 예매할 수 있는 종합 예매 사이트입니다.

상영일 기준 2주 이내의 상영작을 현재 상영작으로 정의하여 해당 상영작을 예매할 수 있습니다. 

또한 매진이 된 상영작의 경우, 취소표에 한해 대기표를 신청할 수 있는 드로우(Draw) 기능이 구현되어 있습니다. 드로우는 상영 전날 신청한 인원에 한해 랜덤으로 추첨이 이루어집니다.

### :alarm_clock: 개발기간 및 인원
- 2023.02.15 - 2023.03.06 (20일)
- 6명

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

<img width="425" alt="image" src="https://user-images.githubusercontent.com/99037697/233271195-8436faf7-f043-4fb8-b45d-c3d978584680.png">


<hr>

### 💻 프로젝트 화면

### **1.메인화면**
- 메인화면에서는 상영작들의 정보를 보거나 로그인 페이지로 넘어갈 수 있다.

<img width="525" height="300" alt="image" src="https://user-images.githubusercontent.com/99037697/233266910-e61e497c-1cbf-4f6e-8165-dd4046d8e6ba.png">

<br><br>

### **1-2. 메인화면 (장르별 랭킹, 시간대별 상영작)**

- 사용자가 기록한 점수에 따라 장르 별로 순위가 매겨진 랭킹을 최대 3등까지 확인할 수 있다.
- 현재 상영하는 작품, 상영 예정작인 작품을 장르별로 확인할 수 있다.

<img width="625" height="400" alt="image" src="https://user-images.githubusercontent.com/99037697/230832844-1e3046ea-44eb-4f5c-b2d5-ad1b2d50a06c.png">

### **2. 검색 & 카테고리 페이지**

 - 작품명과 출연자 이름을 토대로 검색 결과 확인.

<img width="555" height="350" alt="image" src="https://user-images.githubusercontent.com/99037697/233265852-5a396637-76c9-4996-bb6e-151639289667.png">

<br>

### **2-2. 카테고리별 상영작 출력 (무한 스크롤)**

 - 내비게이션 바에서 카테고리 클릭시 해당 작품 출력.
 - 예매 사이트 특성상 사용자가 편하게 아이쇼핑하는 경험을 할 수 있도록 상영작 목록을 볼 때 페이지를 일일히 누르기보다 무한 스크롤 방식을 채택하였음.
 - scroll이 하단에 위치하는 순간 page가 증가하도록 설정.

<img width="625" height="400" alt="image" src="https://user-images.githubusercontent.com/99037697/233266013-1154d6ba-18de-4f0f-bcbb-c60e795e2767.png">
 
### **3. 로그인, 회원가입, 아이디 비밀번호 찾기**

- db에 존재하는 데이터와 일치할 경우 로그인 성공.
- 회원가입 시 아이디와 전화번호 중복확인, 해당 전화번호로 문자 인증 등의 기능 구현.

<img width="425" height="250" alt="image" src="https://user-images.githubusercontent.com/99037697/232431254-d2c900fa-dbfb-4f42-94a7-21d7d8190aa0.png">|<img width="325" height="300" alt="image" src="https://user-images.githubusercontent.com/99037697/232431433-b56d8b56-c41c-4928-b022-77bdfcb384a9.png">
--- | --- |

<br>

### **3-1. 아이디, 비밀번호 찾기**

<img width="525" height="400" alt="image" src="https://user-images.githubusercontent.com/99037697/233267241-78471d81-b98e-4260-9f1c-c29f5c0bde7a.png">|<img width="525" height="400" alt="image" src="https://user-images.githubusercontent.com/99037697/233267263-048a4fd0-93a4-48fb-a3eb-84beeda49521.png">
--- | --- |


### **4. 작품 상세 페이지 및 예매**

- 상세 페이지에서는 작품의 기본적인 정보를 확인하고, 서버 시간에 맞춰 예매를 할 수 있다.

<img width="725" height="400" alt="image" src="https://user-images.githubusercontent.com/99037697/233268499-545c85aa-2f82-48c9-aa68-5c3f07c06163.png">

- 예매 버튼을 누르면 좌석 선택 페이지로 넘어간다. 좌석을 선택하면 결제 모듈이 실행된다.

<img width="725" height="400" alt="image" src="https://user-images.githubusercontent.com/99037697/233268759-bf9b118e-0c14-44e8-bf0a-bd8db9feb182.png">


### **5. 마이페이지**

- 마이페이지에서는 자신의 회원정보를 수정하거나 예매내역, 드로우내역, QnA 문의내역, 내가 쓴 후기를 확인할 수 있다.
- 예매, 드로우, QnA, 후기는 마이페이지에서 취소, 삭제할 수 있다.

<img width="625" height="400" alt="image" src="https://user-images.githubusercontent.com/99037697/233269353-04ac4a66-e67e-4863-b304-dc327189b65d.png">

<br><br>

### **6. 관리자 페이지**

- mainAdmin 페이지를 통해서 고객관리, 상영작 관리, 공지사항 관리 페이지로 접속 가능.
- 정석적인 CRUD, 페이징 처리, 정렬, 검색 기능을 가진 두 게시판.
- Thymeleaf를 통해 프론트 구현 및 페이징 처리, 정렬, 검색 기능 활용.

<img width="525" height="250" alt="image" src="https://user-images.githubusercontent.com/99037697/231105156-2f77c494-cc4b-48fe-91d5-ebae62c5c76f.png">


### **6-1. 고객 관리 페이지**

- id, 이름, 생년월일, 성별에 따라 정렬.
- id와 이름을 기준으로 검색 가능.
- 사용자가 예매한 상영작, 작성한 qna 내역 조회 가능.
- 사용자 아이디를 입력하면 사용자의 정보를 수정할 수 있고, 삭제를 누르면 사용자 정보가 삭제된다.

<img width="625" height="350" alt="image" src="https://user-images.githubusercontent.com/99037697/231105715-4a9e5200-d4ba-4fc3-8a9f-de9ed4654a25.png">

### **6-2. 작품 관리 페이지**

- 상영작 정보를 조회, 검색, 추가 ,수정하거나 삭제할 수 있음.

<img width="625" height="350" alt="image" src="https://user-images.githubusercontent.com/99037697/231107846-cf110d60-cb9d-4de4-a016-61d40e07a181.png">


### **6-3. 공지사항 페이지**

- 관리자 페이지에서 공지사항 목록 페이지로 이동 가능.

<img width="625" height="300" alt="image" src="https://user-images.githubusercontent.com/99037697/233269916-605fc98d-f0cd-4277-ab28-23a3a394e84c.png">


### 🔍 그 외 자세한 코드, 문제 해결 기록, 일자별 기록은 깃허브 링크를 통해 확인할 수 있습니다.
### 제가 담당한 부분은 다음과 같습니다.
- 1번 메인화면 전체
- 2번 검색 & 카테고리 전체
- 3번 로그인 & 회원가입 기능 일부
- 5번 마이페이지 qna 내역 조회
- 6번 관리자 페이지 전체

[깃허브 링크](https://github.com/Greyhan7/DoItCoding_Final_Greyhan777.git)


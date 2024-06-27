# ✈️ 그룹웨어 기본 연동 기능 및 메시지 봇 구현 팀(GroupAir)

## 🔥 프로젝트 소개

![image](https://github.com/minsik4120/Second_TeamProject-GroupAir_Minsik/assets/154856679/9dd6cb04-4a67-4df5-b4cd-d5954dd27349)

⭐️ GroupAir가 항공사 테마를 주제로 선정한 이유

* 2차 프로젝트 컨셉 정하기 및 역할 분담에서 3차 프로젝트의 핵심 과제인 날씨, 버스 , 영화 API를 어느 분야에 많이 사용될까를 회의 진행중에 고민을 하고 항공 분야를 하면 되겠다라고 팀원들과 얘기를 나눠 주제를 선별해 보았습니다.


## 1. 개발 환경과 ERD 환경 (개발환경,DB)
![image](https://github.com/minsik4120/Second_TeamProject-GroupAir_Minsik/assets/154856679/62ad29ef-4c6b-4df7-a265-396957780b10)
![image](https://github.com/minsik4120/Second_TeamProject-GroupAir_Minsik/assets/154856679/5482a899-b79a-4c56-b482-6aee29dc2639)


## 2. 팀원 구성
역할 분담  <br/>

   * 🐬 서민식 - 회사 일정 , 게시판 관리   <br/>
   * 🎅 박** - 근태 관리 , 급여 관리, 항공편 관리(map) , 권한별 INDEX 페이지   <br/>
   * 🌝 정** - Q&A게시판 CRUD   <br/>
   * 👻 조** - chatbot, 부서관리, layout(index), OpenWeather APY   <br/>
   * ⭐ 손** - CICD, db 설계, 로그인, 사원관리 , oauth, Mail, 조직 연동 <br/>

 ## 3. 개발 기간 및 작업 관리
 ![image](https://github.com/minsik4120/Second_TeamProject-GroupAir_Minsik/assets/154856679/70780eb4-4907-423c-843d-ff1942a0d4df)


## 4. 게시판 관리 , 회사 일정(FullCalendar)  <br/>

  ### (1) 게시판 페이지   <br/>
  
   1️⃣ 게시판 페이지 : 회원 가입한 유저나 상품을 등록한 상품등이 조회가 가능하고 검색 기능과 페이징 , 상세 페이지에 들어가 수정 삭제 등을 가능하게 처리하였
습니다 . 그리고 관리자 페이지에 들어가 네이버 API 워크스페이스로 연동하여 사용할 수 있도록 하였습니다.






  ### (2) 회사 일정(FullCalendar)  
  2️⃣ 일정 관리  : 회원으로 등록된 사람은 이용이 가능하고 일정 관리 탭에서 사용이 가능합니다.
  * 전체 일정 관리 - ADMIN 권한이 일정을 등록하면 전체 일정으로 등록이 되고 전체 일정 탭에서 확인이 가능합니다 .
  * 개인 일정 관리 - 자신의 ID로 일정 등록한 것만 캘린더에서 확인이 가능하고 그외 관리자가 등록한 일정도 확인이 가능합니다.                                                                                                                            

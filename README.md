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
  
   1️⃣ 게시판(CRUD),(덧글) 관리 : 글 작성시 해당 카테고리 별 페이지로 등록이 되고 카테고리는 공지 사항 , 자유 게시판, 수다방, Q&A 등이 있습니다. 
   * 글작성시 공지사항은 ADMIN 권한인 사람만 볼 수 있게 처리 하였고 그로 인해 공지사항은 ADMIN 만 등록이 가능합니다.
   * 등록된 게시글은 각각의 ID 값을 가져 등록이 됩니다

<details>
  <summary>예시 이미지</summary>


![image](https://github.com/minsik4120/Second_TeamProject-GroupAir_Minsik/assets/154856679/1dc3c1ec-3a2c-40ef-bbe6-47e725b17aca)
     
![image](https://github.com/minsik4120/Second_TeamProject-GroupAir_Minsik/assets/154856679/4c11e026-acac-41e0-8faf-f852f6c5c3cb)
    

 ![image](https://github.com/minsik4120/Second_TeamProject-GroupAir_Minsik/assets/154856679/0b5ee723-783e-45bc-bc6e-b4b577050ff9)


</details>
 


   * 또한 글 등록 닉네임은 회원 가입때 사용한 유저 닉네임으로 등록이 되게 처리하였고 덧글 작성시에도 회원 상세 정보를 가져와 처리 했습니다.
   * 공지 사항으로 등록된 게시물은 특별하게 보이게 처리하였습니다.
        
     
      





  ### (2) 회사 일정(FullCalendar)  
  2️⃣ 일정 관리  : 회원으로 등록된 사람은 이용이 가능하고 일정 관리 탭에서 사용이 가능합니다.
  * 전체 일정 관리 - ADMIN 권한이 일정을 등록하면 전체 일정으로 등록이 되고 전체 일정 탭에서 확인이 가능합니다 .
  * 개인 일정 관리 - 자신의 ID로 일정 등록한 것만 캘린더에서 확인이 가능하고 그외 관리자가 등록한 일정도 확인이 가능합니다.                                                                                                                            

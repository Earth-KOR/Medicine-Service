# Medicine-Service - 약 복용 알리미
<p align="center"> Main Page Preview </p>
<p align="center"> <img src="C:/Users/김동민/Desktop/사진/main.png" width="600px"> </p>
<br>

## 주제
* **프리미엄 신발 관련 커뮤니티 웹 서비스**

## 개발환경

* 개발도구
  * Eclipse Neon 3 `v4.6.3`
  * MySQL WorkBench `v8.0.17`
  <br>
* 언어
  * JAVA SE1.8 `JDK 8`
  * JSP `model 1`
  * HTML5/CSS3
  * JavaScript/Jquery
  <br>
* 서버(WAS)
  * Apache Tomcat `v8.5`
  <br>
* 커뮤니티
  * Google Document / Github

## 개요

### SneakerHouse 소개
프리미엄 신발 관련 발매 정보, 착샷 게시판 등 커뮤니케이션을 하기 위한 웹 서비스

### 선정이유
현재 운영중인 신발 관련 커뮤니티에서 밴치마킹을 통해 고쳐야할 부분은 개선하고 더 깔끔한 디자인의 커뮤니티를 만들기 위함

### 주요기능
* 발매 정보 게시판
* 착샷/신발장/셀럽들 이미지 게시판
* 나의 신발장 관리 게시판
* 관리자/일반 사용자 글쓰기/수정 기능

## DB 구성 - ER Diagram
<p align="center"> <img src="./Project/Screenshots/ERdiagram.png" width="700px"> </p>

## 기능구현 
* [메인 페이지](#메인-페이지-배너)
* [회원가입 form](#회원가입-form)
* [게시판](#게시판)
  * [전체 게시판](#전체-게시판)
  * [일반 게시판](#일반-게시판)
  * [이미지 게시판](#이미지-게시판)
* [글쓰기 form](#글쓰기-form)
* [나의 신발장](#나의-신발장)

<br>

## 메인 페이지 배너
* `JavaScript`을 활용하여, `Slide` 함수를 만들어 직접 제작한 3가지 종류의 이미지를 넣어서 만듬.
* 이미지 클릭시, 해당 페이지로 갈수 있도록 링크 걸어둠.
<img src="./Project/Screenshots/banner.gif" width="700px">

<br>

## 회원가입 form
* `jQuery`를 활용하여 아이디, 비밀번호, 비밀번호 확인, 이메일란 유효성 검사하기.
* 유효성 검사를 하는 동시에 `Ajax`를 활용한 아이디 및 이메일 중복 확인 검사하기.
* `DAUM 우편번호 API`를 활용하여 주소 찾기 구현.
<img src="./Project/Screenshots/joinForm.gif" width="800px">

<br>

## 게시판

### 전체 게시판
* 전체 num 컬럼으로 전체 게시판 리스트 뿌려주기. (특정 게시판은 board_type, re_ref 컬럼으로 리스트 뿌려주기)
<p> <img src="./Project/Screenshots/all_board.png" width="700px"> <img src="./Project/Screenshots/all_board2.png" width="600px"> </p>

<br>

### 일반 게시판
* 글 상단부에 최근 공지사항 글 5개를 DB에서 가지고 와서 뿌려줌.
<img src="./Project/Screenshots/board1.png" width="700px">

<br>

### 이미지 게시판
<img src="./Project/Screenshots/image_board.png" width="700px">

<br>

## 글쓰기 form
* `네이버 스마트 에디터 api`를 이용하여 이미지 파일 업로드가 가능한 글쓰기 구현.
<p> <img src="./Project/Screenshots/write1.png" width="500px"> <img src="./Project/Screenshots/write2.png" width="200px"> </p>

<br>

## 나의 신발장
* 소개: 본인이 가지고 있는 신발들을 추가하여 도표를 통해 정리할수 있는 페이지.
* 추가하기 버튼(검색기능): `Ajax`를 활용하여 검색한 글자를 토대로 DB에서 리스트 가져오기.
<img src="./Project/Screenshots/my_locker.gif" width="800px">

<br>

## 라이센스
Copyright © 2020 Jeong Hun Park. <br>
This project is ITWILL busan licensed.
<hr>

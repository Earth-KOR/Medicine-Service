# Medicine-Service - 약 복용 알리미
<br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95682260-4bca4a00-0c1f-11eb-800a-84f91a25a2f6.png" width="600px"> </p>
<br><br><br>

## 프로젝트 주제
<br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95682342-a4014c00-0c1f-11eb-91d7-18b6c80a4f13.png" width="600px"> </p>
<br><br><hr>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95682353-b54a5880-0c1f-11eb-8e22-72d23b91ccbe.png" width="600px"> </p>
<br><br>

* **아날로그 방식 개선 목적 : 식사시간을 기준으로 정하는 아날로그 방식에서 `복용알림 서비스로의 개선 목적.`** <br><br>
* **복용효과 100% 받기 : 약 동력의 기반하여 `최적의 재 복용 시간`을 알려 줌.** <br><br>
* **체내 약 효능 시기 인지하기 : 체내에 약의 효과가 작용하고 있는 것을 `시각적 그래프`로 알려 줌.** 

<hr><br>

<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95682371-d448ea80-0c1f-11eb-9302-b83d753c297a.png" width="600px"> </p>
<br><br><hr><br>

## 개발환경
<br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95682372-d57a1780-0c1f-11eb-9523-b3c31a51424a.png" width="600px"> </p>
<br><br>

## Database
<br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95739084-39a2e700-0cc5-11eb-9577-6fb907f23a17.png" width="600px"> </p>
<br><br>


## 기능구현 
* [검색 기능](#검색-기능)
* [복용 시작](#복용-시작)
* [복용 기록](#복용-기록)
* [복용 그래프](#복용-그래프)
* [약국 찾기 지도](#약국-찾기-지도)
* [실시간 채팅 상담](#실시간-채팅-상담)
<br><br>
### 검색 기능
<br>

* **공공데이터 에서 약 `53000개`의 약 정보를 파싱하여 DB에 저장함.** <br><br>
* **공공데이터 에서 약 `100개`의 약 부작용 정보를 파싱하여 DB에 저장함.** <br><br>
* **Jquery의 AutoComplete 기능을 이용하여 검색 시 `자동완성` 기능을 추가함.**

<br><br><br>
<p align="center">< 검색 시 ></p><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734699-c72f0880-0cbe-11eb-9620-288b8ee81c3f.png" width="600px"> </p>
<br>
<p align="center">< 검색 완료 시 ></p>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734692-c4ccae80-0cbe-11eb-8866-29d24a3352ad.png" width="600px"> </p>
<br><br>

<hr><br>

### 복용 시작
<br>

* **원하는 약을 선택한 후, 처방받은 `복용주기를 입력함.`** <br><br>
* **입력한 약의 이름을 `정보 테이블 , 부작용 테이블에 접근`하여 부합하는 정보를 가져와서 리스트로 나타냄.**

<br><br><br><br>
<p align="center">< 주기 입력 창 ></p><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734698-c6967200-0cbe-11eb-8860-1502c735bc64.png" width="600px"> </p>
<br><br>
<p align="center">< 복용 리스트 출력 ></p><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734722-cbf3bc80-0cbe-11eb-9557-fc9114361a22.png" width="600px"> </p>
<br><br>
<hr><br>

### 복용 기록
<br>

* **복용이 완료된 약들은 복용 리스트에서 지워지고, `History 페이지에 자동으로 이동하여 저장`됨.**

<br><br>
<p align="center">< History ></p>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734717-ca29f900-0cbe-11eb-81a3-e14d9739f9bb.png" width="600px"> </p>
<br><br>
<hr><br>

### 복용 그래프
<br>

* **임의로 만든 약 동력 그래프를 시간에 따라 그리게 되고, 복용주기가 끝나면 그래프는 멈추도록 설계함.** <br><br>
* **약의 효과가 발현하는 55% 지점부터 (임의로 지정) 약 효과가 발현하고 있다는 신호를 주기위해 `gif 이미지를 띄워 표시함.`** <br><br>
* **입력한 재복용 주기만큼 시간이 지나면 사용자의 휴대전화로 `재복용 알람 문자 메세지를 전송함.`**

<br><br><br><br>

<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734719-cb5b2600-0cbe-11eb-9382-84f5afa0208d.png" width="600px"> </p>

<br><br>

<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95845701-7c2bf880-0d85-11eb-8e5b-875c68f72fca.png" width="600px"> </p>

<br><br><br><br>

<p align="center">< 그래프 구현 ></p>
  
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95844973-8a2d4980-0d84-11eb-9401-8014d9958a8e.gif" width="600px"> </p>

<br><br><br><br> 

<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734742-d2823400-0cbe-11eb-9437-dc2633fc547e.png" width="600px"> </p>

<br><br>

<hr>


<br>

### 약국 찾기 지도
<br>

* **공공데이터에서 약 `22000개`의 전국 약국정보를 파싱하여 DB에 저장함.** <br><br>
* **해당 약국의 위치를 표시하기 위해서` Marker` 기능을 사용하였고, 해당 마커의 정보를 표기하기 위해 `InfoWindow` 기능을 사용함.** <br><br>
* **Marker가 한 곳에 밀집되어 있을 때, 밀집된 Marker의 수를 표시하기 위해서 `Clusterer` 기능을 구현함.** <br><br>
* **광역시 시청을 기준으로 버튼 클릭 시 , `해당 좌표로 바로 이동`할 수 있도록 구현함.**

<br><br><br><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95738785-c00af900-0cc4-11eb-9c86-5a2b74e67851.png" width="600px"> </p>
<br><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95738771-ba151800-0cc4-11eb-8096-8fca86454a9c.png" width="600px"> </p>
<br><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734753-d57d2480-0cbe-11eb-8e79-d81428cd699b.png" width="600px"> </p>
<br><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95738780-be413580-0cc4-11eb-9d40-2c987a2aa8cd.png" width="600px"> </p>
<br><br>
<hr><br>

### 실시간 채팅
<br>

* **Admin으로 접속 했을 때에는 관리자 채팅 표시가 나타나게 하고, 일반 유저일 경우 실시간 상담 로고만 나타나도록 설정함.** <br><br>
* **관리자 채팅으로 접속 시, 일반 유저들이 작성 한 채팅창이 뜨게 작성하였고 `실시간 채팅`이 가능하도록 구현함.** <br><br>
* **일반 유저가 실시간 상담을 종료하면 관리자 채팅에서의 `채팅방도 사라지게 구현함.`**

<br><br><br><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734757-d615bb00-0cbe-11eb-85e5-083338798c82.png" width="600px"> </p>
<br><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734763-d7df7e80-0cbe-11eb-858b-cd1bae9a5081.png" width="600px"> </p>
<br><br>


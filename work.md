작업내용
============
## 3/30 화요일 

- Gateway 프로젝트 생성 (포트 7000)
- Eureka 서버 프로젝트 생성 (포트 8761)
- Application 이름 셋팅
    - GATEWAY
    - MONOLITHIC

- GATEWAY 라우팅 설정 : /** 에 대해서 MONOLITHIC 서비스로 라우팅 되도록 설정
- 서비스 등록 상태 확인 Eureka Dashboard : http://localhost:8761/


## 3/31 수요일

- Front-End 에서 gateway 7000 번으로 호출하도록 변경 
- Cors 문제가 발생해서, 기존 Back-end 프로젝트에서 설정 제거하고, GateWay에서 Cors 셋팅수행 


## 4/1 목요일
- Docker Compose AD Mounted Volume 수정 
- AD SErvie Eureka Dependency 추가 
- Ad Appliacation에 @EnableEurekaClient 추가 
- GateWay 설정 변경  
- AD Service /api/ads => /ads 로 변경 
- 구동 확인
- 타 서비스 호출을 위한 Feign Client 추가 <- Backend-Service (AdsServiceClient 클래스 참고)

## 4/3 토요일
- Currency 서비스 새로만들기
- 기존 Currency 서비스 가져오기 
- Currency 에서 Redis를 사용하도록 수정
- Redis에 환율 초기값 설정하는 방법을 몰라서 fetchCurrency 에서 강제 할당함 *****
- api-gateway에 CURRENCY 활성화 (8094 Port 사용)
- eureka client 등록
- //TO-DO
- Redis 에 초기값 넣고 fetchCurrency 정상화
- currency 서비스에서 payment 의 Money Class 를 참조하는데 참조가 안되서 Money 클래스를 복사하여 가져옴
  --> 어떻게 처리해야 하는지???
- Monorithic 에서 currency 제거



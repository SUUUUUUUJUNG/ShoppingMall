# 오늘의 쇼핑

이 저장소는 "오늘의 쇼핑" 온라인 쇼핑몰 프로젝트의 백엔드 및 프론트엔드 코드를 포함하고 있습니다. Java, Spring Boot, Oracle DB를 사용하여 구축한 이 프로젝트는 사용자가 효율적으로 상품을 검색하고 구매할 수 있는 웹 애플리케이션입니다.

## 프로젝트 개요

"오늘의 쇼핑"은 다양한 기능을 제공하는 온라인 쇼핑몰로서, 사용자에게 편리한 쇼핑 경험을 제공합니다. 회원가입부터 상품 검색, 결제까지 전 과정을 지원하며, 사용자 피드백에 기반한 지속적인 업데이트를 제공합니다.

## 주요 기능

- **회원가입 및 로그인:**
  - 사용자 계정 생성 및 관리
  - Daum 주소 API를 활용한 주소 입력 지원
  - Spring Security 및 OAuth2를 통한 로그인 인증

- **상품 검색 및 관리:**
  - 다양한 카테고리의 상품 검색 기능
  - 상품 상세보기 페이지에서 리뷰, 문의 등 사용자 상호작용 지원

- **장바구니 및 결제 시스템:**
  - 상품 장바구니 추가 및 관리
  - PayPal API를 통한 안전한 결제 처리

- **주문 관리:**
  - 주문 내역 확인 및 관리
  - 주문 상태 업데이트 및 추적

- **사용자 리뷰 및 피드백:**
  - 제품 리뷰 작성 및 조회 기능
  - 사용자 문의 사항 관리 및 응답

- **관리자 기능:**
  - 상품 등록, 수정, 삭제 관리
  - 사용자 관리 및 통계 보기

## 기술 스택

- **프론트엔드:** JSP, JavaScript, Bootstrap, CKEditor5
- **백엔드:** Java, Spring Boot, Maven, MyBatis
- **데이터베이스:** Oracle DB 11g
- **보안:** Spring Security, OAuth2, JWT
- **APIs:** Daum 주소 검색 API, Kakao 및 Naver 로그인 API, PayPal API

## 시작하기

### 사전 요구사항

- JDK 17
- Maven
- Oracle DB 설치 및 설정

### 설치 방법

1. 프로젝트 클론하기:
   ```bash
   git clone https://github.com/SUUUUUUUJUNG/ShoppingMall.git
   ```
2. Maven을 이용하여 의존성 해결 및 빌드:
   ```bash
   cd ShoppingMall
   mvn install
   ```
3. 애플리케이션 실행:
   ```bash
   mvn spring-boot:run
   ```
## 설정 파일 구성

이 프로젝트에서 사용하는 설정 파일은 `application.properties`와 `application-dev.properties`로 구분되어 있습니다. 각 파일은 Spring Boot 애플리케이션의 다양한 환경 설정을 관리하며, 개발 환경에 특화된 설정들은 `application-dev.properties`에 명시되어 있습니다.

### application.properties

기본 설정 파일로, 애플리케이션의 공통적인 환경 설정을 포함합니다.

```properties
# BasicDataSource
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=데이터베이스 유저이름
spring.datasource.password=비밀번호

# Server
server.port=8092

# Spring MVC
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
spring.mvc.static-path-pattern=/resources/**
server.error.path=/error

# Active Profiles
spring.profiles.active=dev
```

### application-dev.properties

개발 환경에 특화된 설정으로, 보안, OAuth, 메일 서버 설정 등을 포함합니다.

```properties
# JWT 설정
jwt.expiredMs=만료시간
spring.jwt.secret=jwt 시크릿키

# Kakao OAuth 설정
spring.security.oauth2.client.registration.kakao.client-id=아이디
spring.security.oauth2.client.registration.kakao.client-secret=시크릿키
spring.security.oauth2.client.registration.kakao.redirect-uri=콜백 url
spring.security.oauth2.client.registration.kakao.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.kakao.client-authentication-method=client_secret_post
spring.security.oauth2.client.registration.kakao.scope=profile_nickname
spring.security.oauth2.client.provider.kakao.authorization-uri=https://kauth.kakao.com/oauth/authorize
spring.security.oauth2.client.provider.kakao.token-uri=https://kauth.kakao.com/oauth/token
spring.security.oauth2.client.provider.kakao.user-info-uri=https://kapi.kakao.com/v2/user/me
spring.security.oauth2.client.provider.kakao.user-name-attribute=id

# Naver OAuth 설정
spring.security.oauth2.client.registration.naver.client-id=아이디
spring.security.oauth2.client.registration.naver.client-secret=시크릿키
spring.security.oauth2.client.registration.naver.redirect-uri=콜백 url
spring.security.oauth2.client.registration.naver.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.naver.scope=email
spring.security.oauth2.client.provider.naver.authorization-uri=https://nid.naver.com/oauth2.0/authorize
spring.security.oauth2.client.provider.naver.token-uri=https://nid.naver.com/oauth2.0/token
spring.security.oauth2.client.provider.naver.user-info-uri=https://openapi.naver.com/v1/nid/me
spring.security.oauth2.client.provider.naver.user-name-attribute=response

# 이메일 서비스 설정
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=이메일
spring.mail.password=앱 비밀번호
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

이 설정 파일들은 애플리케이션의 데이터베이스 연결, 보안 인증, 메일 서비스와 같은 핵심적인 기능들을 구성합니다. 각 설정은 환경에 따라 적절히 조정되어야 하며, 특히 보안 관련 정보(예: 비밀번호, API 키)는 안전하게 관리되어야 합니다.
## 사용 예제

프로젝트 실행 후 웹 브라우저를 통해 `http://localhost:8080`으로 접속하면 "오늘의 쇼핑" 웹사이트를 사용할 수 있습니다. 회원가입, 로그인, 상품 검색, 장바구니 기능을 테스트해 보세요.

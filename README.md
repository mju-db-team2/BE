# SI Manager Backend API

이 프로젝트는 SI 회사의 프로젝트 및 인력 관리를 위한 웹 애플리케이션의 **백엔드 API 서버**입니다.  
Spring Boot(Gradle), JPA, MySQL을 기반으로 하며, 프론트엔드(Vite + React)에서 호출하는 REST API를 제공합니다.

---

## 시작하기

### 필수 조건
- **Java 21 이상**
- **Gradle** (wrapper 포함)
- **MySQL 8.x**
- (선택) IntelliJ IDEA

---

## 설치

레포지토리를 클론한 후 프로젝트 폴더로 이동합니다.

```bash
git clone https://github.com/<your-org-or-id>/BE.git
cd BE
```

---

## 환경 설정 (.env 방식)

민감한 정보(DB 비밀번호 등)는 GitHub에 업로드되지 않으며,  
`.env` 파일에 저장하여 환경변수로 주입합니다.

### 1) `.env` 파일 작성 (루트 경로에 생성)

```bash
DB_HOST=localhost
DB_PORT=3306
DB_NAME=dbteam2
DB_USER=root
DB_PASSWORD=your_password
```

### 2) `src/main/resources/application.yml` 생성

```yaml
spring:
  datasource:
    url: jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: ${DB_USER}
    password: ${DB_PASSWORD}

  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true

logging:
  level:
    org.hibernate.SQL: debug
```

👉 이 파일(application.yml)은 `.gitignore`에 포함되어 GitHub에 올라가지 않습니다.  
👉 팀원들은 제공된 `application.yml.template` 을 참고해 로컬 설정을 작성합니다.

---

## 서버 실행

### macOS / Linux

```bash
export $(grep -v '^#' .env | xargs)
./gradlew bootRun
```

### Windows PowerShell

```powershell
Get-Content .env | ForEach-Object {
  if ($_ -match "^(?<name>[^=]+)=(?<value>.*)$") {
    [System.Environment]::SetEnvironmentVariable($matches['name'], $matches['value'], "Process")
  }
}
.\gradlew.bat bootRun
```

브라우저에서 API를 테스트하려면 `http://localhost:8080` 으로 접속합니다.

---

## 빌드

```bash
./gradlew build
```

빌드 결과는 `build/libs/*.jar` 로 생성되며 단독 실행이 가능합니다.

```bash
java -jar build/libs/si-manager-backend-0.0.1-SNAPSHOT.jar
```

---

## 프로젝트 구조

```
src
└─ main
├─ java
│   └─ com.example.dbteam2backend
│       ├─ global
│       │   ├─ config          # CORS, JPA 설정 등 공통 설정
│       │   ├─ exception       # 글로벌 예외 처리 (CustomException / Handler)
│       │   └─ api             # 공통 API 응답 (ApiResponse)
│       │
│       ├─ project             # ⭐ 1번 담당자 (프로젝트 도메인)
│       │   ├─ controller
│       │   ├─ service
│       │   ├─ repository
│       │   └─ entity
│       │
│       ├─ employee            # ⭐ 2번 담당자 (인력/스킬 도메인)
│       │   ├─ controller
│       │   ├─ service
│       │   ├─ repository
│       │   └─ entity
│       │
│       ├─ club                # ⭐ 3번 담당자 (동아리 도메인)
│       │   ├─ controller
│       │   ├─ service
│       │   ├─ repository
│       │   └─ entity
│       │
│       └─ Dbteam2BackendApplication
│
└─ resources
├─ application.yml.template   # 팀원용 템플릿
├─ application.yml            # (gitignore)
├─ static
└─ templates

```
---

## 주요 기능


본 백엔드 서버는 다음 3가지 핵심 기능을 중심으로 개발됩니다.

1. **프로젝트 투입 상태 조회**
    - 여러 프로젝트에 투입된 개발자들의 현황을 한 화면에서 확인
    - 프로젝트별 인력 구성 비율, 역할별 투입 인원 등 집계 데이터 제공
    - 프론트엔드 대시보드에서 시각화될 데이터 API 제공

2. **특정 기술(Skill Set)을 보유한 직원 필터링**
    - 직원이 보유한 기술 기반으로 검색/필터링
    - 예: Java + Spring Boot 가능한 직원만 조회
    - 숙련도(prof_level), 경험연차(exp_years), 최근 사용일자 기반 조건 조회 지원

3. **동아리(Club) 기능 + 성능 향상 실험**
    - 동아리 목록, 멤버, 역할(동아리장/부원) 조회 API
    - 동아리 활동(Activity) 및 참여자 조회 기능
    - ⚡ **성능 향상 기법(캐싱/쿼리 최적화 등)을 적용하여 전·후 성능 비교 실험**
        - 예: 동아리 멤버 & 활동 조회 API에 캐싱 적용
        - 조회 속도 및 DB 부하 비교

---

## Git 브랜치 전략

- main : 최종 배포 브랜치
- dev : 통합 개발 브랜치
- feature/* : 기능 단위 개발 브랜치


---


## Commit Message Convention

| 타입 | 설명 |
|------|------|
| feat | 새로운 기능 추가 |
| fix | 버그 수정 |
| refactor | 코드 리팩토링 |
| docs | 문서 변경 |
| chore | 빌드/설정 관련 작업 |


---

> 📌 이 문서는 프론트엔드 README 형식에 맞추어 제작된 **백엔드 README**입니다.
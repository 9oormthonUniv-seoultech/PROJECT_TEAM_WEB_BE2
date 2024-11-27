### 📂 Pocket-4-Cut

---

## 🛠️ 프로젝트 소개

Pocket-4-Cut은 네컷 필름을 쉽게 휴대폰에 저장하고 관리할 수 있는 서비스이고, 이 Repository는 Pocket-4-Cut의 백엔드 레포입니다.  
유지보수성과 확장성을 위해 헥사고날 아키텍처(Hexagonal Architecture)를 기반으로 설계했습니다.

---

## 🏗️ 주요 기술 스택

- **언어**: Java 21  
- **프레임워크**: Spring Boot 3.3.3  
- **빌드 도구**: Gradle  
- **데이터베이스**: MySQL  
- **API 문서화**: Swagger  
- **배포 환경**: AWS EC2  
- **CI/CD**: GitHub Actions, Docker

---

## 🏛️ 아키텍처

본 프로젝트는 **헥사고날 아키텍처(Hexagonal Architecture)**를 활용하여 설계되었습니다.  
- **Core**: 도메인 로직 및 비즈니스 규칙을 정의하는 핵심 영역.
- **Application**: 유스케이스를 구현하고 외부 API 호출 및 데이터 접근을 오케스트레이션.
- **Ports & Adapters**: 외부 시스템(MySQL, Redis, AWS S3 등)과의 상호작용을 담당.

---

## 📋 주요 기능

1. **네컷 앨범 관리**
   - 앨범 정보 저장, 조회, 삭제
   - 사용자별 필름 즐겨찾기 기능 지원

2. **이미지 저장 및 다운로드**
   - AWS S3와의 통합을 통한 이미지 저장 및 다운로드
   - Presigned URL을 사용한 안전한 이미지 다운로드

3. **사용자 관리**
   - OAuth2(Kakao) 로그인 연동
   - JWT 기반 인증 및 인가
  
---


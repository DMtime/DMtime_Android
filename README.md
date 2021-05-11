# DMtime
[![Build Status](https://app.bitrise.io/app/2c694dc9c1a6641f/status.svg?token=PqgUitZ9yQE2quZ1vwDSCQ&branch=develop)](https://app.bitrise.io/app/2c694dc9c1a6641f)
대덕소프트웨어마이스터고등학교 학생들을 위한 익명 커뮤니티 앱입니다.  
대덕소프트웨어마이스터고등학교 학교이메일로만 가입할 수 있으며, 서로의 소식을 나누거나 
좋거나 안좋은 글들에 대해 댓글과 추천 기능으로 의견을 표현할 수 있습니다  

<br/>

![logo](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F728fbfc5-2c6e-43a7-b664-d7a1e5fa3aec%2FUntitled.png?table=block&id=adbb1ded-3a43-41a7-a22d-991aacda8e9e&width=5120&userId=&cache=v2)  

## 기술스택
* 언어 : Kotlin
* 아키텍쳐 : Clean Architecture, MVP
* 라이브러리 : RxJava, Koin, Retrofit2


<br/>

## 테스트 작성 규칙
테스트 코드는 [Given ~ When ~ Then 규칙](https://martinfowler.com/bliki/GivenWhenThen.html)으로 작성되었습니다.
![image](https://user-images.githubusercontent.com/48317457/116565423-d2871000-a940-11eb-9057-40208708dbd9.png)

## 참고 자료
### 아키텍쳐
[Advanced MVP](https://speakerdeck.com/gorita/advanced-mvp-refactoring-mvp)
[Adapter, 누구냐 넌? — Data? View?](https://medium.com/@jsuch2362/adapter-%EB%88%84%EA%B5%AC%EB%83%90-%EB%84%8C-data-view-2db7eff11c20)
[Android Architecture Blueprints [beta] - MVP + Clean Architecture](https://github.com/android/architecture-samples/tree/todo-mvp-clean)
[Android-CleanArchitecture](https://github.com/android10/Android-CleanArchitecture)

### 테스트
[안드로이드의 테스트~빌드~배포 사이클](https://medium.com/wantedjobs/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%EC%9D%98-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EB%B9%8C%EB%93%9C-%EB%B0%B0%ED%8F%AC-%EC%82%AC%EC%9D%B4%ED%81%B4-9d542eda0ef8)
[테스트 코드, 안드로이드에서는 어떻게 작성해야 할까?](https://blog.banksalad.com/tech/test-in-banksalad-android/)
[Given ~ When ~ Then 규칙](https://martinfowler.com/bliki/GivenWhenThen.html)
[Android TDD 적용기](https://speakerdeck.com/sungil/android-tdd-jeogyonggi)
### 안드로이드
[[번역] RecyclerView의 안티 패턴](https://medium.com/hongbeomi-dev/%EB%B2%88%EC%97%AD-recyclerview-%EC%95%88%ED%8B%B0-%ED%8C%A8%ED%84%B4-ce0fcd8ea232)
### CI/CD
[CI/CD(지속적 통합/지속적 제공): 개념, 방법, 장점, 구현 과정](https://www.redhat.com/ko/topics/devops/what-is-ci-cd)
[안드로이드 Bitrise 도입기](https://brunch.co.kr/@kmongdev/19)
[Bitrise 자동 빌드 후 Slack메시지 연동하기](https://velog.io/@ironelder/Bitrise-%EC%9E%90%EB%8F%99-%EB%B9%8C%EB%93%9C-%ED%9B%84-Slack%EB%A9%94%EC%8B%9C%EC%A7%80-%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0-nmk6ag26mp)


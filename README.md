목차
1. 프로젝트 개요
2. 프로젝트 설명
  - 프로젝트 설명
  - 사용 기술
  - 프로젝트 구조
  - ERD
  - 구현 기능

프로젝트 개요
  프로젝트 소개 : 떡볶이 추천 서비스
  팀원 : 총 4인 (기획 1, 디자인 1, 프론트 1, 백엔드1)
  담당 역할 : API 명세서 작성, DB 설계 및 구축, 백엔드 API 개발, TDD, 서버 구축, Jenkins 연동
  개발 기간 : 2024.01.26 ~ 2024.02.31
  프로젝트 URL : www.utteok.com 프로젝트 깃헙 : https://github.com/KindOfTteokbokki API 명세서 : https://documenter.getpostman.com/view/26372368/2s9YyqihiZ
  와이어프레임: https://www.figma.com/file/Pnl9LrfEoxrxX1KWfxnuD9/%EC%96%B4%EB%96%A4-%EB%96%A1%EB%B3%B6%EC%9D%B4-%EC%A2%85%ED%95%A9?type=design&node-id=68-350&mode=design&t=ifOAW6qaUEKTi2kX-0

프로젝트 설명
1) 프로젝트 설명
  떡볶이를 좋아하는 사람들에게, 어떤 떡볶이를 먹을지 고민하는 이들에게,
  가게와 메뉴를 추천하는 서비스입니다.
  유저는 떡볶이 재료, 맛 등을 선택하고
  수집되있는 정보를 통해 매칭되는 가게의 메뉴를 찾아줍니다.
  재미 요소로 재료 맛 등 선택에 따른 취향의 칭호를 획득하고 컬렉션을 할 수 있습니다.
  또한 실 사용자들의 후기를 남김으로 다른 사용자들에게 추천을 할 수 있습니다.
2) 사용기술
  Frontend : TypeScript, React, Redux, styled-components
  Backend : Spring boot, Gradle, JAVA, Mybatis, Junit
  Database : MariaDB
  Deploy : NCloud, Linux, Jenkins
  API : Postman
  Cooperation tool : Slack, Figma, Discord, GitHub
3) 프로젝트 구조
4) ERD
5) 구현 기능
  1. 소셜 로그인(카카오, 네이버)
    - 회원가입
      카카오 또는 네이버로 회원가입
      이메일과 닉네임을 필수 값으로 저장
      소셜 종류와 이메일, 닉네임 모두 확인하여 중복 확인
    - 로그인
      Oauth2.0을 통해 로그인
  2. 떡볶이 종류 선택
    - 재료 선택 값 서버에서 클라이언트에 전달
      값을 서버에서 관리함으로써 유지보수성을 높임
  3. 떡볶이 종류에 맞는 가게 메뉴 랜덤 노출
    - 선택된 값에 따른 메뉴 정보 노출
      선택 값에 매칭되는 메뉴 가져오기
      중복 값이 있을 경우, 랜덤으로 한 개 노출
    - 해당 유저에 노출된 메뉴, 횟수 저장(로그인 필요)
      카운트 테이블에 유저별 메뉴 노출 횟수 저장
  4. 떡볶이 종류에 맞는 칭호 랜덤 획득
    - 선택된 값에 따른 칭호 노출
      선택 값에 매칭되는 칭호 가져오기
      중복 값이 있을 경우, 랜덤으로 한 개 노출
    - 해당 유저에 획득한 칭호 저장(로그인 필요)
      칭호 테이블에 유저별 노출된 칭호 저장
  5. 칭호 페이지(로그인 필요)
    - 획득한 칭호 노출
      해당 유저의 칭호 테이블에 저장된 칭호 노출
    - 획득하지 못한 칭호 노출
      해당 유저가 획득하지 못한 칭호 이름과 받은 다른 유저들의 수 노출
  6. 내가 많이 선택한 메뉴 Top3 노출
    - 비회원은 최근 조회된 메뉴 노출
    - 해당 유저에 노출된 메뉴, 많은 순으로 노출(로그인 필요)
  7. 리뷰 리스트 및 등록
    - 비회원은 메인페이지에서 최근 4 건만 노출
    - 유저들이 등록한 리뷰 노출(로그인 필요)
    - 유저 리뷰 등록(로그인 필요)
  8. 관리자 추천 조합 노출
    - 관리자가 등록한 메뉴 노출
  9. 내정보(로그인 필요)
    1) 닉네임 변경
      - 변경이 없을 경우 소셜을 통해 가져온 닉네임 사용
      - 변경 시도 시, 웹소켓으로 닉네임 입력 실시간으로 중복 검사
    2) 획득한 칭호 개수 노출
      - 유저가 획득한 칭호, 총 개수 노출
    3) 리뷰 관리(수정 삭제)
      - 유저가 등록한 리뷰만 노출하여 수정 삭제 기능
    4) 닉네임 변경
      - 글자 입력 시, 실시간으로 닉네임 사용가능 여부 체크

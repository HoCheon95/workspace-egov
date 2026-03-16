## 실험 1: 서블릿 이름 불일치
- 변경 내용: web.xml의 servlet-mapping에 servlet-name만 다른 이름으로 바꾼다.
- 예상 결과: 서버가 안열릴 것 이다.
- 관찰 결과: Tomcat 서버가 실행이 안됨.
		  java.lang.IllegalArgumentException: 서블릿 매핑이 알 수 없는 서블릿 이름 [asdflkj]을(를) 지정하고 있습니다. 
- 원인 분석: wev.xml에서 <servlet>과 <servlet-mapping>에서 servlet-name을 기준으로 연결이 되는 것을 확인 할 수 있다.

1. 연결 고리(ID) 역할의 상실
 - <servlet>: com.homework.web.WroldTimeServlet 이라는 클래스가 있고, 그 이름을 WroldTimeServlet 이라고 부르겠다 선언
 - <servlet-mapping>: 어떤 이름의 서블릿을 어떤 URL에 연결할까? 결정
 이 때 두 태그 사이를 잇는 유일한 기준이 바로 <servlet-name>이다. 하지만 서로 이름이 다르면 Tomcat은 /worldtime 이라는 주소로 들어온 요청을 보낼 목적지(asdflkj)라는 이름의 서블릿을 찾지 못하게 된다.
 
 
## 실험 2: Content-Type 제거
- 변경 내용: WorldTimeServlet에 response.setContentType("text/html;charset=UTF-8"); 코드 주석처리
- 예상 결과: 한글이 깨질 것이다.
- 관찰 결과: 한글이 다 깨진다.
- 원인 분석: Content-Type 헤더가 없으면 서블릿 컨테이너(Tomcat)와 HTTP 표준은 명시적인 설정이 없을 경우 기본 인코딩 ISO-8859-1로 간주한다. ISO-8859-1은 서구권 언어 전용 인코딩이라서 한글을 표현할 수 없다.


## 실험 3: GET을 POST로 변경
- 변경 내용: index.html의 <form method="GET">을 <form method="POST">로 바꾼다.
- 예상 결과: 조회하기 버튼을 클릭해도 정보가 전달이 안될 것 같다.
- 관찰 결과: 405 에러 코드 발생
- 원인 분석: 브라우저의 폼은 method 값을 확인하고, 그에 맞는 HTTP 메서드로 요청을 보낸다. 서블릿 컨테이너(Tomcat)는 이 요청을 받아 적절한 서블릿 메서드를 호출한다.


## 실험 4: 존재하지 않는 타임존
- 변경 내용: index.html의 select option 중 하나의 value를 존재하지 않는 타임존으로 바꾼다.
- 예상 결과: 한국 시간이 나올 것 같다.
- 관찰 결과: ZoneRulesException 에러발생
- 원인 분석: ZoneId.of() 메서드는 미리 정의된 타임존 ID 리스트에 없는 값을 넣으면 ZoneRulesException 이라는 예외를 던지며 프로그램이 멈추게 된다.


## 실험 5: 서블릿 인스턴스 관찰
- 변경 내용: WorldTimeServlet 클래스에 private int requestCount = 0; 필드를 추가한다.
doGet() 메서드 시작 부분에서 requestCount++ 를 실행하고 응답 HTML에 현재 requestCount 값을 함께 출력한다.
- 예상 결과: 매번 1로 초기화 될 것같다.
- 관찰 결과: 서로 다른 브라우저에서도 같은 카운터를 공유한다.
- 원인 분석: 
1) 공유 메모리: 모든 사용자가 하나의 서블릿 객체를 공유하므로, 클래스의 필드 변수 역시 모든 사용자가 공유하게 된다. 그래서 서로 다른 브라우저에서 접속해도 requestCount가 이어져서 증가하는 것이다.
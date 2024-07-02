# React Wtih SpringBoot

## 개발 환경
- Front-End : React(VsCode)
- Back-End : Spring-Boot(Intelli J)
- DB : MariaDB

## 커리큘럼
- 리액트(React) 개발 프로세스
- API 서버 개발
- JWT 인증 처리
- 리덕스 툴킷(Redux Toolkit)
- 리코일(Recoil)
- 리액트 쿼리(React Query)

### @RequiredArgsConstructor
- Lombok의 의존성 주입(DI) 방법 중 생성자 주입을 코드 없이 자동 설정해주는 어노테이션

### implements Formatter<LocalDate>
```
@Override
public LocalDate parse(String text, Locale locale) throws ParseException {
    return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
}

@Override
public String print(LocalDate object, Locale locale) {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
}
```
- 클라이언트가 보내는 쿼리 파라미터는 문자열이기 때문에 서블릿을 통해 받아오면 직접 원하는 타입으로 변환해주어야 한다.
- implements Forammtter<LocalDate> 를 통해 변환해주는 메소드 override

### implements WebMvcConfigurer
- 생성한 클래스를 config에 등록할 수 있다.
- 날짜변환이나 컨버터 등등

### @Builder(builderMethodName = "withAll")
- 기본 메서드 명인 Builder() 대신 다른 이름으로 네이밍

### @Data
- Lombok 라이브러리에서 지원하는 어노테이션으로 @Getter/@Setter/@Tostring/@EqualsAndHashCode/@RequiredArgsContructor 를 합쳐놓은 어노테이션

### @PathVariable
- 경로 변수를 표시하기 위해 메서드에 매개변수를 사용
- 경로 변수는 중괄호 {id}로 둘러싸인 값을 나타낸다.
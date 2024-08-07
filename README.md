# React Wtih SpringBoot Todo Project

## 개발 환경
- Front-End : React(VsCode)
- Back-End : Spring-Boot(Intelli J)
- DB : MariaDB

## Library
### Front-End
- react-router-dom
- axios
- @reduxjs/toolkit react-redux
- react-cookie

### Back-End
- Lombok
- queryDSL
- thumbnailator:0.4.19
- Spring Security
- JWT API, Impl, Jackson

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

### @Builder.Default
- 객체의 초기화

### @Data
- Lombok 라이브러리에서 지원하는 어노테이션으로 @Getter/@Setter/@Tostring/@EqualsAndHashCode/@RequiredArgsContructor 를 합쳐놓은 어노테이션

### @PathVariable
- 경로 변수를 표시하기 위해 메서드에 매개변수를 사용
- 경로 변수는 중괄호 {id}로 둘러싸인 값을 나타낸다.

### @PostConstruct
- 의존성 주입이 이루어진 후 초기화를 수행하는 어노테이션
- 생성자보다 늦게 호출 된다.
1. 생성자 호출
2. 의존성 주입 완료
3. @PostConstruct


### Axios Library
- JSON.stringify(obj) => json 타입으로 변환이 필요 없다.

### 파일 업로드
- 실제 저장할 경로 + 파일명 만들기 `Path savePath = Paths.get(uploadPath, saveName)`
- 실제 파일을 읽어서 만든 경로 + 파일명으로 저장 `Files.copy(file.getInputStream(), savePath)`

### File.separator
- 운영체제에 맞는 디렉토리 구분자를 자동으로 삽입 `window : \\`, `Unix : /`

### @Embeddable
- 한 엔티티가 가지는 컬럼들 중 다른 하나의 객체로 묶을 수 있는 컬럼들을 묶어 객체지향적으로 설계할 수 있다.

### @ElementCollection
- JPA가 컬랙션 객체임을 알 수 있게 한다.
- 컬렉션 객체는 관계형 데이터베이스에 저장할 수 없기 때문에 컬렉션을 저장하기 위해 별도의 테이블을 만들어 컬렉션을 저장해야 한다.
- int, String, Integer 등과 같은 엔티티가 아닌 값 타입, 임베디드 타입에 대한 테이블을 생성하고 1대 다 관계로 다룬다.
- @ElementCollection(fetch = FetchType.LAZY) 컬렉션의 데이터는 실제로 접글할 때 까지 데이터베이스에서 로드되지 않는다.

### @ElementCollection과 @Embeddable의 동작
- @ElementCollection은 @Embeddable 타입의 객체 목록을 엔티티의 필드로 정의할 때 사용된다.
- 이 어노테이션은 JPA에게 List, Set, Map과 같은 컬렉션을 매핑하기 위한 방법을 지시한다.

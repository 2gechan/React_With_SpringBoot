# Spring Security

## Spring Security를 사용하기 위한 구현
```
-- CustomSecurityConfig 클래스

@Bean으로 등록하게 되면 인증 요청이 들어올 경우 Spring Security에서 Bean으로 등록된 인증 필터가 요청을 가로챈다.

@Configuration
@Log4j2
@RequiredArgsConstructor
public class CustomSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.debug("-------------------- security config --------------------");

        // spring security cors 설정
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });

        // 세션 만들지 않는다는 설정
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.NEVER);
        });

        // csrf disabled
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        http.formLogin(config -> {
            config.loginPage("/api/member/login");
        });

        return http.build();
    }

    // 패스워드 인코더
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cors 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        // cors 설정 객체
        CorsConfiguration configuration = new CorsConfiguration();

        // 모든 출처 허용
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));

        // 허용 메서드
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));

        // 허용된 요청 헤더 목록
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        // 자격 증명(쿠키, 인증 헤더 등)을 포함한 요청 허용할 지 설정
        configuration.setAllowCredentials(true);

        // URL 패턴 별로 cors 설정 매핑 객체
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // 모든 경로에 앞서 정의한 cors 설정 등록
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
} 

-- Member 클래스
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "memberRoleList") // 지연 로딩 하기 때문에 ToString을 사용하게 되면 문제가 발생할 수 있어서 예외
public class Member {

    @Id
    private String email;

    private String pw;

    private String nickname;

    private boolean social;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberRole> memberRoleList = new ArrayList<>();

    public void addRole(MemberRole memberRole) {
        memberRoleList.add(memberRole);
    }

    public void clearRole() {
        memberRoleList.clear();
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changePw(String pw) {
        this.pw = pw;
    }

    public void changeSocial(boolean social) {
        this.social = social;
    }
}

-- MemberDTO 클래스

Spring Security의 User 클래스를 상속받아 인증 처리할 때 사용, 상속받은 User 클래스 생성자 호출 필수 super()

public class MemberDTO extends User {

    private String email, pw, nickname;
    private boolean social;

    private List<String> roleNames = new ArrayList<>();

    public MemberDTO(String email, String pw, String nickname, boolean social, List<String> roleNames ) {
        super(email, pw, roleNames.stream().
                map(str -> new SimpleGrantedAuthority("ROLE_"+str)).collect(Collectors.toList()));

        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
        this.social = social;
        this.roleNames = roleNames;
    }

    public Map<String, Object> getClaims() {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("email", email);
        dataMap.put("pw", pw);
        dataMap.put("nickname", nickname);
        dataMap.put("social", social);
        dataMap.put("roleNames", roleNames);

        return dataMap;
    }
}


-- CustomUserDetailsService 클래스

UserDetailsService는 사용자 정보를 로드하기 위한 인터페이스
이를 구현하는 클래스는 loadUserByUsername 메서드를 통해 사용자 이름으로 UserDetails 객체를 반환

@RequiredArgsConstructor
@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("----------------loadUserByUsername-----------------" + username);

        Member member = memberRepository.getWithRoles(username);

        if (member == null) {
            throw new UsernameNotFoundException("Not Found");
        }

        MemberDTO memberDTO = new MemberDTO(
                member.getEmail(),
                member.getPw(),
                member.getNickname(),
                member.isSocial(),
                member.getMemberRoleList().stream()
                        .map(role -> role.name()).collect(Collectors.toList())
        );

        return memberDTO;
    }
}
```

package com.gechan.mallapi.config;

import com.gechan.mallapi.controller.formatter.LocalDateFormatter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 웹 설정과 관련된 것들을 명시하는 곳 (formatter, cors 등)
@Configuration
@Log4j2
public class CustomServletConfig implements WebMvcConfigurer {

    // 날짜 변환 클래스를 config에 등록
    @Override
    public void addFormatters(FormatterRegistry registry) {

        log.debug("------------------------");
        log.debug("addFormatters");
        registry.addFormatter(new LocalDateFormatter());
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // 모든 경로에 cors 매핑
//        registry.addMapping("/**")
//                .maxAge(500) // preflight 요청 캐싱하는 시간
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
//                .allowedOrigins("*") // 모든 경로 허가
//                .allowedHeaders("Authorization", "Cache-Control", "Content-Type");
//    }
}

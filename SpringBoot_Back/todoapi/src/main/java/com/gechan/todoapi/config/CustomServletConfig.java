package com.gechan.todoapi.config;

import com.gechan.todoapi.controller.formatter.LocalDateFormatter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
}

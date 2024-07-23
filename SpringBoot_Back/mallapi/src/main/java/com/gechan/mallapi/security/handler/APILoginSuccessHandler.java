package com.gechan.mallapi.security.handler;

import com.gechan.mallapi.dto.MemberDTO;
import com.gechan.mallapi.util.JWTUtil;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

// 인증에 성공했을 때 사용할 핸들러
@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("-----------인증 성공 핸들러------------");
        log.debug(authentication);
        log.debug("-----------------------------------");

        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();

        // 회원 정보 가져오기
        Map<String, Object> claims = memberDTO.getClaims();

        String accessToken = JWTUtil.generateToken(claims, 10);
        String refreshToken = JWTUtil.generateToken(claims, 60 * 24);

        // 인증 토큰 삽입
        claims.put("accessToken", accessToken);
        claims.put("refreshToken", refreshToken);

        // json 타입으로 변환
        Gson gson = new Gson();
        String jsonStr = gson.toJson(claims);

        // json 타입 설정
        response.setContentType("application/json; charset=UTF-8");

        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();
    }
}

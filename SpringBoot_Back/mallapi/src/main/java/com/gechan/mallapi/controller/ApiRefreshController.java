package com.gechan.mallapi.controller;

import com.gechan.mallapi.util.CustomJWTException;
import com.gechan.mallapi.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ApiRefreshController {

    @RequestMapping("/api/member/refresh")
    public Map<String, Object> refresh(
            @RequestHeader("Authorization") String authHeader,
            String refreshToken) {

        if (refreshToken == null) throw new CustomJWTException("NULL_REFRESH");

        if (authHeader == null || authHeader.length() < 7) {
            throw new CustomJWTException("INVALID STRING");
        }

        // Bearer xxxx...
        String accessToken = authHeader.substring(7);

        // AccessToken 만료 여부 확인
        if (!checkExpiredToken(accessToken)) {
            return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
        }

        Map<String, Object> claims = JWTUtil.validateToken(refreshToken);

        // 위 로직 조건문을 통과하지 못한 경우는 accessToken이 만료 된 것 이므로 새롱룬 accessToken 발급
        String newAccessToken = JWTUtil.generateToken(claims, 10);

        // 기존 refreshToken의 만료 시간이 60분 미만이면 새로운 refreshToken 발급
        String newRefreshToken = checkTime((Integer) claims.get("exp")) ? JWTUtil.generateToken(claims, 60 * 24) : refreshToken;

        return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);
    }

    private boolean checkTime(Integer exp) {

        // jwt exp를 날짜로 변환
        Date exDate = new Date((long) exp * 1000);

        // 현재 시간과의 차이 계산 - 밀리세컨즈
        long gap = exDate.getTime() - System.currentTimeMillis();

        // 분단위 계산
        long leftMin = gap / (1000 * 60);

        // 1시간도 안남았는지 ..
        return leftMin < 60;
    }

    private boolean checkExpiredToken(String token) {

        try {
            JWTUtil.validateToken(token);
        } catch (CustomJWTException exception) {
            if (exception.getMessage().equals("Expired")) {
                return true;
            }
        }
        return false;
    }

}

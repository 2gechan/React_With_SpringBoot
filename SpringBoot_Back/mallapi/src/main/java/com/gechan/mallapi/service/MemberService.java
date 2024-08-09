package com.gechan.mallapi.service;

import com.gechan.mallapi.dto.MemberDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    MemberDTO getKakaoMember(String accessToken);
}

package com.gechan.mallapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long pno;

    private String pname;

    private int price;

    private String pdesc;

    private boolean delFlag;

    // 브라우저에서 등록하거나 수정하는 파일 목록
    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

    // DB에 들어 있는 파일명 목록
    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();
}

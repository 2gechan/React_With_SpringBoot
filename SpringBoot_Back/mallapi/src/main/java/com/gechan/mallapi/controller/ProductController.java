package com.gechan.mallapi.controller;

import com.gechan.mallapi.dto.PageRequestDTO;
import com.gechan.mallapi.dto.PageResponseDTO;
import com.gechan.mallapi.dto.ProductDTO;
import com.gechan.mallapi.service.ProductService;
import com.gechan.mallapi.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final CustomFileUtil fileUtil;
    private final ProductService productService;

//    @PostMapping("/")
//    public Map<String, String> register(ProductDTO productDTO) {
//
//        log.debug("register : {}", productDTO);
//
//        List<MultipartFile> files = productDTO.getFiles();
//
//        List<String> uploadFileNames = fileUtil.saveFiles(files);
//
//        productDTO.setUploadFileNames(uploadFileNames);
//
//        log.debug(uploadFileNames);
//
//        return Map.of("RESULT", "SUCCESS");
//    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable("fileName") String fileName) {

        return fileUtil.getFile(fileName);
    }

    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {

        return productService.getList(pageRequestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register(ProductDTO productDTO) {

        // 실제 파일
        List<MultipartFile> files = productDTO.getFiles();

        // 파일 저장 후 파일 명 리스트
        List<String> uploadFileNames = fileUtil.saveFiles(files);

        // DB에 파일 명 저장하기 위해 SET
        productDTO.setUploadFileNames(uploadFileNames);

        log.debug(uploadFileNames);

        Long pno = productService.register(productDTO);

        return Map.of("RESULT", pno);
    }

    @GetMapping("/{pno}")
    public ProductDTO read(@PathVariable("pno") Long pno) {

        return productService.get(pno);
    }
}

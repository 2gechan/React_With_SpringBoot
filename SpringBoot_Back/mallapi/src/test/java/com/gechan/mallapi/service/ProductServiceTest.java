package com.gechan.mallapi.service;

import com.gechan.mallapi.dto.PageRequestDTO;
import com.gechan.mallapi.dto.PageResponseDTO;
import com.gechan.mallapi.dto.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<ProductDTO> responseDTO = productService.getList(pageRequestDTO);

        log.debug(responseDTO.getDtoList());
    }

    @Test
    public void testRegister() {

        ProductDTO productDTO = ProductDTO.builder()
                .pname("새로운 상품")
                .pdesc("신규 추가 상품입니다.")
                .price(1000)
                .build();

        productDTO.setUploadFileNames(
                java.util.List.of(
                        UUID.randomUUID() + "_" + "TEST1.jpg",
                        UUID.randomUUID() + "_" + "TEST2.jpg"
                )
        );

        Long pno = productService.register(productDTO);
    }
}

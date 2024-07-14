package com.gechan.mallapi.service;

import com.gechan.mallapi.dto.PageRequestDTO;
import com.gechan.mallapi.dto.PageResponseDTO;
import com.gechan.mallapi.dto.ProductDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductService {

    PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);

    Long register(ProductDTO productDTO);

    ProductDTO get(Long pno);
}

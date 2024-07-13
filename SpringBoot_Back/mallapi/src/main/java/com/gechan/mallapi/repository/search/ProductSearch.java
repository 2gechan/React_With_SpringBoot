package com.gechan.mallapi.repository.search;

import com.gechan.mallapi.dto.PageRequestDTO;
import com.gechan.mallapi.dto.PageResponseDTO;
import com.gechan.mallapi.dto.ProductDTO;

public interface ProductSearch {

    PageResponseDTO<ProductDTO> searchList(PageRequestDTO pageRequestDTO);
}

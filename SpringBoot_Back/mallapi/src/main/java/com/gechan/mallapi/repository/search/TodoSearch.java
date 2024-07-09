package com.gechan.mallapi.repository.search;

import com.gechan.mallapi.domain.Todo;
import com.gechan.mallapi.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface TodoSearch {

    Page<Todo> search1(PageRequestDTO pageRequestDTO);
}

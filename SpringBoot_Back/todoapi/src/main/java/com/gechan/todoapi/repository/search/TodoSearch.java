package com.gechan.todoapi.repository.search;

import com.gechan.todoapi.domain.Todo;
import com.gechan.todoapi.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface TodoSearch {

    Page<Todo> search1(PageRequestDTO pageRequestDTO);
}

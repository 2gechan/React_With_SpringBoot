package com.gechan.todoapi.repository.search;

import com.gechan.todoapi.domain.Todo;
import org.springframework.data.domain.Page;

public interface TodoSearch {

    Page<Todo> search1();
}

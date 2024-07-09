package com.gechan.mallapi.repository;

import com.gechan.mallapi.domain.Todo;
import com.gechan.mallapi.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {

}

package com.gechan.todoapi.repository;

import com.gechan.todoapi.domain.Todo;
import com.gechan.todoapi.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {

}

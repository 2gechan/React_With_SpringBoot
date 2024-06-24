package com.gechan.todoapi.repository.search;

import com.gechan.todoapi.domain.QTodo;
import com.gechan.todoapi.domain.Todo;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

// queryDSL을 사용할 때 이름이 정확히 맞아야한다 Interface 이름 뒤에 Impl 필수
// QuerydslRepositorySupport 상속 필요
@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    // 생성자에 도메인 클래스 지정
    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> search1() {
        log.debug("search1....................");

        QTodo todo = QTodo.todo; // 쿼리를 날리기 위한 객체

        JPQLQuery<Todo> query = from(todo); // from
        query.where(todo.title.contains("1")); // where

        // 페이징 처리할 조건
        Pageable pageable = PageRequest.of(1, 10, Sort.by("tno").descending());

        // 페이징 조건, 쿼
        this.getQuerydsl().applyPagination(pageable, query);

        query.fetch(); // 목록 데이터

        query.fetchCount(); // 카운트(Long)

        return null;
    }
}

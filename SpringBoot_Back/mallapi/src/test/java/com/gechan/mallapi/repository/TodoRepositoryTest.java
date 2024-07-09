package com.gechan.mallapi.repository;

import com.gechan.mallapi.domain.Todo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test1() {
        Assertions.assertNotNull(todoRepository);

        log.debug(todoRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        for (int i = 0; i < 100; i++) {
            Todo todo = Todo.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .dueDate(LocalDate.of(2024, 12, 31))
                    .build();

            Todo result = todoRepository.save(todo);
            Assertions.assertNotNull(result);
            log.debug(result.toString());
        }
    }

    @Test
    public void testRead() {
        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        log.debug(todo);
    }

    @Test
    public void testUpdate() {
        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        todo.setTitle("Update Title");
        todo.setContent("Update Content");
        todo.setComplete(true);
        todo.setDueDate(LocalDate.of(2024, 6, 24));

        Todo updateTodo = todoRepository.save(todo);
        Assertions.assertNotNull(updateTodo);
        log.debug(updateTodo);
    }

    @Test
    public void testPaging() {

        // jpa의 페이징 기법
        // 페이지 번호는 0부터
        // 페이지 번호 0, 페이지사이즈 10, tno번호로 내림차순 정렬
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        // 개수
        log.debug(result.getTotalElements());

        // 실제 내용
        log.debug(result.getContent());

    }

//    @Test
//    public void testSearch1() {
//        todoRepository.search1();
//    }
}

package com.gechan.todoapi.repository;

import com.gechan.todoapi.domain.Todo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Todo todo = Todo.builder()
                .title("Title")
                .content("Content...")
                .dueDate(LocalDate.of(2024, 12, 31))
                .build();

        Todo result = todoRepository.save(todo);

        Assertions.assertNotNull(result);
        log.debug(result.toString());
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
}

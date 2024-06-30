package com.gechan.todoapi.service;

import com.gechan.todoapi.dto.PageRequestDTO;
import com.gechan.todoapi.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTest {

    @Autowired
    TodoService todoService;

    @Test
    public void testGet() {

        Long tno = 50L;

        log.debug(todoService.get(tno));
    }

    @Test
    public void testRegister() {

        TodoDTO todoDTO = TodoDTO.builder()
                .title("Title...")
                .content("Content...")
                .dueDate(LocalDate.of(2024, 06, 30))
                .build();

        log.debug(todoService.register(todoDTO));
    }

    @Test
    public void testGetList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(21).build();

        log.debug(todoService.getList(pageRequestDTO));
    }

}

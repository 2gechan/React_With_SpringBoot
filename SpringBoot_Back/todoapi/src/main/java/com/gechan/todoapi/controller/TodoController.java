package com.gechan.todoapi.controller;

import com.gechan.todoapi.dto.PageRequestDTO;
import com.gechan.todoapi.dto.PageResponseDTO;
import com.gechan.todoapi.dto.TodoDTO;
import com.gechan.todoapi.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable("tno") Long tno) {

        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {

        log.debug("list......." + pageRequestDTO);

        return todoService.getList(pageRequestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDTO todoDTO) {

        log.debug("todoDTO: " + todoDTO);

        Long tno = todoService.register(todoDTO);

        return Map.of("TNO", tno);
    }
}

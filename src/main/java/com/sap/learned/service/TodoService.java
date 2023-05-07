package com.sap.learned.service;

import com.sap.learned.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    List<TodoDTO> findAll();
    TodoDTO findById(Long id);
    TodoDTO save(TodoDTO todoDTO);
    void delete(Long id);
}

package com.sap.learned.service.impl;

import com.sap.learned.dto.TodoDTO;
import com.sap.learned.entity.Todo;
import com.sap.learned.exception.EntityNotFoundException;
import com.sap.learned.exception.ErrorCodes;
import com.sap.learned.exception.InvalidEntityException;
import com.sap.learned.repository.TodoRepository;
import com.sap.learned.service.TodoService;
import com.sap.learned.validator.TodoValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Override
    public List<TodoDTO> findAll() {
       return todoRepository.findAll().stream()
               .map(TodoDTO::fromEntity)
               .collect(Collectors.toList());
    }

    @Override
    public TodoDTO findById(Long id) {
        if (id == null) {
            log.error("ID is null");
            return null;
        }

        Optional<Todo> todo = todoRepository.findById(id);
        TodoDTO todoDTO = TodoDTO.fromEntity(todo.get());
        return Optional.of(todoDTO).orElseThrow(() -> new EntityNotFoundException("No todo was found with the id " + id, ErrorCodes.TODO_NOT_FOUND));
    }

    @Override
    public TodoDTO save(TodoDTO todoDTO) {
        List<String> errors = TodoValidator.validate(todoDTO);
        if (!errors.isEmpty()) {
            log.error("Todo is not valid {}", todoDTO);
            throw new InvalidEntityException("Todo is not valid", ErrorCodes.TODO_NOT_VALID, errors);
        }

        return TodoDTO.fromEntity(todoRepository.save(TodoDTO.toEntity(todoDTO)));
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("ID is null");
            return;
        }

        todoRepository.deleteById(id);
    }
}

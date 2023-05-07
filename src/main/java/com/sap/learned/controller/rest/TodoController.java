package com.sap.learned.controller.rest;

import com.sap.learned.dto.TodoDTO;
import com.sap.learned.service.TodoService;
import com.sap.learned.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.APP_ROOT + "/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TodoDTO>> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.findById(id));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDTO> add(@RequestBody TodoDTO todoDTO) {
        todoDTO.setId(0L);
        return ResponseEntity.ok(todoService.save(todoDTO));
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDTO> update(@RequestBody TodoDTO todoDTO) {
        return ResponseEntity.ok(todoService.save(todoDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.ok().build();
    }
}

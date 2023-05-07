package com.sap.learned.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.learned.entity.Todo;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class TodoDTO {

    private Long id;
    private String title;
    private String description;
    @JsonIgnore
    private Set<UserDTO> users;

    public static TodoDTO fromEntity(Todo todo) {
        if (todo == null)
            return null;

        return TodoDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .build();
    }

    public static Todo toEntity(TodoDTO todoDTO) {
        if (todoDTO == null)
            return null;

        Todo todo = new Todo();
        if (todoDTO.getId() > 0) todo.setId(todoDTO.getId());
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());

        return todo;
    }
}

package com.sap.learned.validator;

import com.sap.learned.dto.TodoDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TodoValidator {

    public static List<String> validate(TodoDTO todoDTO) {
        List<String> errors = new ArrayList<>();

        if (todoDTO == null) {
            errors.add("Title is mandatory !");
            errors.add("Description is mandatory !");
            return errors;
        }
        if (!StringUtils.hasLength(todoDTO.getTitle())) {
            errors.add("Title is mandatory !");
        }
        if (!StringUtils.hasLength(todoDTO.getDescription())) {
            errors.add("Description is mandatory !");
        }
        return errors;
    }
}

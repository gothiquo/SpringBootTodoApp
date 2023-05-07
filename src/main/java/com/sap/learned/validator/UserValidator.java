package com.sap.learned.validator;

import com.sap.learned.dto.UserDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validate(UserDTO userDTO) {
        List<String> errors = new ArrayList<>();

        if (userDTO == null) {
            errors.add("First Name is mandatory !");
            errors.add("Last Name is mandatory !");
            errors.add("Email is mandatory !");
            errors.add("Username is mandatory !");
            errors.add("Password is mandatory !");
            return errors;
        }
        if (!StringUtils.hasLength(userDTO.getFirstName())) {
            errors.add("First Name is mandatory !");
        }
        if (!StringUtils.hasLength(userDTO.getLastName())) {
            errors.add("Last Name is mandatory !");
        }
        if (!StringUtils.hasLength(userDTO.getEmail())) {
            errors.add("Email is mandatory !");
        }
        if (!StringUtils.hasLength(userDTO.getUsername())) {
            errors.add("Username is mandatory !");
        }
        if (!StringUtils.hasLength(userDTO.getPassword())) {
            errors.add("Password is mandatory !");
        }
        return errors;
    }
}

package com.sap.learned.service;

import com.sap.learned.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO findByEmail(String email);
    UserDTO findByUsername(String username);
    UserDTO save(UserDTO userDTO);
    void delete(Long id);
}

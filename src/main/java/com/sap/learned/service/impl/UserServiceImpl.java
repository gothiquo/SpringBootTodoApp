package com.sap.learned.service.impl;

import com.sap.learned.dto.UserDTO;
import com.sap.learned.exception.EntityNotFoundException;
import com.sap.learned.exception.ErrorCodes;
import com.sap.learned.exception.InvalidEntityException;
import com.sap.learned.repository.UserRepository;
import com.sap.learned.service.UserService;
import com.sap.learned.validator.UserValidator;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().
                map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        if (id == null) {
            log.error("ID is null");
            return null;
        }

        var user = userRepository.findById(id);
        var userDTO = UserDTO.fromEntity(user.get());
        return Optional.of(userDTO).orElseThrow(() -> new EntityNotFoundException("No user was found with the ID " + id, ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserDTO findByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            log.error("Email is null");
            return null;
        }

        var user = userRepository.findUserByEmail(email);
        var userDTO = UserDTO.fromEntity(user.get());
        return Optional.of(userDTO).orElseThrow(() -> new EntityNotFoundException("No user was found with the email " + email, ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserDTO findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            log.error("Username is null");
            return null;
        }

        var user = userRepository.findUserByUsername(username);
        var userDTO = UserDTO.fromEntity(user.get());
        return Optional.of(userDTO).orElseThrow(() -> new EntityNotFoundException("No user was found with the username " + username, ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        List<String> errors = UserValidator.validate(userDTO);

        if (!errors.isEmpty()) {
            log.error("User is not valid {}", userDTO);
            throw new InvalidEntityException("User is not valid", ErrorCodes.USER_NOT_VALID, errors);
        }

        return UserDTO.fromEntity(userRepository.save(UserDTO.toEntity(userDTO)));
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("ID is null");
            return;
        }

        userRepository.deleteById(id);
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            log.error("Username is null");
            return null;
        }

        var user = userRepository.findUserByUsername(username).get();
        user = Optional.of(user).orElseThrow(() -> new EntityNotFoundException("No user was found with the username " + username, ErrorCodes.USER_NOT_FOUND));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(List.of(user.getRole())));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }*/
}

package com.monocart.service;

import com.monocart.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO dto);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO dto);
    void deleteUser(Long id);
}
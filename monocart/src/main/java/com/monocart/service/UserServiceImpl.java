package com.monocart.service;


import com.monocart.dto.UserDTO;
import com.monocart.entity.User;
import com.monocart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = new User(null, dto.getName(), dto.getEmail(), dto.getAddress());
        User saved = userRepository.save(user);
        return new UserDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getAddress());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getAddress()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAddress(dto.getAddress());
        User updated = userRepository.save(user);
        return new UserDTO(updated.getId(), updated.getName(), updated.getEmail(), updated.getAddress());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
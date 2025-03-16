package org.heymouad.blog.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.entities.User;
import org.heymouad.blog.repositories.UserRepository;
import org.heymouad.blog.services.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;

    @Override
    public User loadUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found!!"));
    }
}

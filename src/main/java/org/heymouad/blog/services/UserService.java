package org.heymouad.blog.services;

import org.heymouad.blog.domain.entities.User;

import java.util.UUID;

public interface UserService {
    User loadUserById(UUID id);
}

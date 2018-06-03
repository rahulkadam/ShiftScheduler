package com.astro.shiftscheduler.service;

import com.astro.shiftscheduler.domain.auth.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUsername(String username);
    void save(User user);
}

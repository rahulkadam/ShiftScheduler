package com.astro.shiftscheduler.service;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    String findLoggedInUserName();
    void autoLogin(String username , String password);
}

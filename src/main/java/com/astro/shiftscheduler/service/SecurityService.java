package com.astro.shiftscheduler.service;


public interface SecurityService {
    String findLoggedInUserName();
    void autoLogin(String username , String password);
}

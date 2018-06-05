package com.astro.shiftscheduler.dao.repository;

import com.astro.shiftscheduler.domain.auth.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

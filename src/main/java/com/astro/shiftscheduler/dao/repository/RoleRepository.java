package com.astro.shiftscheduler.dao.repository;

import com.astro.shiftscheduler.domain.auth.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role , Long> {

}

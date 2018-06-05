package com.astro.shiftscheduler.dao.repository;

import com.astro.shiftscheduler.domain.auth.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("rolerepository")
public interface RoleRepository extends CrudRepository<Role , Long> {

}

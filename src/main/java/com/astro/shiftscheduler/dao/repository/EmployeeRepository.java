package com.astro.shiftscheduler.dao.repository;

import com.astro.shiftscheduler.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("employeeRepository")
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}

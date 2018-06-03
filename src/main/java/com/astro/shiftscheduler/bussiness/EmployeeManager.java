package com.astro.shiftscheduler.bussiness;

import com.astro.shiftscheduler.dao.repository.EmployeeRepository;
import com.astro.shiftscheduler.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeManager {

    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * Generating dummy employee list based on no of employee
     * @param n
     * @return
     */
    @Transactional
    public List<Employee> generateEmployeeListForNEmployee(int n) {
        List<Employee> employeeList = new ArrayList<>();
        for(int i = 0; i< n ; i++) {
            Employee employee = new Employee();
            employee.setName("Employee-" + employee.getId());
            employeeList.add(employee);
        }
        employeeRepository.saveAll(employeeList);
        return employeeList;
    }
}

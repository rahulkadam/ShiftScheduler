package com.astro.shiftscheduler.dao.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Employee {
    private long id;
    private String name;

    public Employee(Employee employee) {
        this.id = employee.id;
        this.name = employee.name;
    }
}

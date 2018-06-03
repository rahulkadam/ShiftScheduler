package com.astro.shiftscheduler.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Employee {
    @Id @GeneratedValue
    private Long id;
    private String name;

    public Employee(Employee employee) {
        this.id = employee.id;
        this.name = employee.name;
    }
}

package com.astro.shiftscheduler.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;

@NoArgsConstructor
@Setter @Getter
@Entity
public class Shift {
    @Id @GeneratedValue
    private Long id;
    private String type;
    @Transient
    private DateTime time;
    private int shiftType;
    private int dayNumber;
    @ManyToOne
    @JoinColumn(name="EMPLOYEE_ID")
    private Employee employee;
    @Transient
    private DateTime createdAt;
}

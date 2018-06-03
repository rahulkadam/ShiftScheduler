package com.astro.shiftscheduler.domain;

import com.astro.shiftscheduler.bussiness.validator.rule.ShiftRule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Setter @Getter
@NoArgsConstructor
@Entity
public class ShiftConfiguration {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @Transient
    private DateTime startingDate;
    private int scheduleSpanDays;
    private int noOfShiftsPerDay;
    private int perWeek;
    private int startingDayOFWeek;
    private boolean active;
    @Transient
    private List<ShiftRule> rules;


    // Default configuration with minimum values
    public ShiftConfiguration(String name ,int noOfShiftsPerDay , int scheduleSpanDays , List<ShiftRule> ruleList) {
        this.noOfShiftsPerDay = noOfShiftsPerDay;
        this.startingDate = new DateTime();
        this.scheduleSpanDays = scheduleSpanDays;
        this.active = true;
        this.rules = ruleList;
        this.perWeek = 7;
        this.startingDayOFWeek = 1;
        this.name = name;
        this.id = (long)(Math.random());
    }
}

package com.astro.shiftscheduler.dao.dto;

import com.astro.shiftscheduler.validator.rule.ShiftRule;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;


import java.util.List;

@Setter @Getter
public class ShiftConfiguration {
    private long id;
    private String name;
    private DateTime startingDate;
    private int scheduleSpanDays;
    private int noOfShiftsPerDay;
    private int perWeek;
    private int startingDayOFWeek;
    private Company company;
    private boolean active;
    private List<ShiftRule> rules;

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

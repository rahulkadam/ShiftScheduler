package com.astro.shiftscheduler.dao.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.Date;

@NoArgsConstructor
@Setter @Getter
public class Shift {
    private Long id;
    private String type;
    private DateTime time;
    private int shiftType;
    private int dayNumber;
    private Employee employee;
    private Date createdAt;
}

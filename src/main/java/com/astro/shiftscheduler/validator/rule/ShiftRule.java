package com.astro.shiftscheduler.validator.rule;

import com.astro.shiftscheduler.dao.dto.Employee;
import com.astro.shiftscheduler.dao.dto.Shift;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public abstract class ShiftRule {
    protected Long id;
    protected String name;

    public boolean validate(List<Shift> shifts , Employee employee) {
        return true;
    }
}

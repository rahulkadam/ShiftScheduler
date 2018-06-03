package com.astro.shiftscheduler.bussiness.validator.rule;

import com.astro.shiftscheduler.domain.Employee;
import com.astro.shiftscheduler.domain.Shift;
import com.astro.shiftscheduler.domain.ShiftConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public abstract class ShiftRule {
    protected Long id;
    protected String name;

    public boolean validate(List<Shift> shifts , Employee employee , ShiftConfiguration config) {
        return true;
    }
}

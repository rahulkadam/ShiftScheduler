package com.astro.shiftscheduler.validator.rule;

import com.astro.shiftscheduler.dao.dto.Employee;
import com.astro.shiftscheduler.dao.dto.Shift;

import java.util.List;

public class MinimumWorkInCertainTimeRule extends ShiftRule {
    private int targetDays = 1;
    private int durationDays = 14;

    @Override
    public boolean validate(List<Shift> shifts, Employee employee) {
        return super.validate(shifts, employee);
    }
}

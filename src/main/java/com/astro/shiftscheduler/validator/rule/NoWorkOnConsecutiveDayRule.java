package com.astro.shiftscheduler.validator.rule;

import com.astro.shiftscheduler.dao.dto.Employee;
import com.astro.shiftscheduler.dao.dto.Shift;

import java.util.List;

public class NoWorkOnConsecutiveDayRule extends ShiftRule {
    public int noOfDay = 2;

    @Override
    public boolean validate(List<Shift> shifts, Employee employee) {
        return super.validate(shifts, employee);
    }
}

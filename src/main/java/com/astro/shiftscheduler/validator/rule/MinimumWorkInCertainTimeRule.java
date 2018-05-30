package com.astro.shiftscheduler.validator.rule;

import com.astro.shiftscheduler.dao.dto.Employee;
import com.astro.shiftscheduler.dao.dto.Shift;
import com.astro.shiftscheduler.dao.dto.ShiftConfiguration;

import java.util.List;

public class MinimumWorkInCertainTimeRule extends ShiftRule {

    @Override
    public boolean validate(List<Shift> shifts, Employee employee , ShiftConfiguration config) {
        return super.validate(shifts, employee , config);
    }
}

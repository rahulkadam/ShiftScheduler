package com.astro.shiftscheduler.validator;

import com.astro.shiftscheduler.dao.dto.Employee;
import com.astro.shiftscheduler.dao.dto.Shift;
import com.astro.shiftscheduler.dao.dto.ShiftConfiguration;
import com.astro.shiftscheduler.validator.rule.ShiftRule;

import java.util.List;

public interface Validator {
    boolean validateRule(List<Shift> shifts , Employee employee , ShiftConfiguration config);
}

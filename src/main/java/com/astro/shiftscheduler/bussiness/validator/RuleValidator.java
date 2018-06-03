package com.astro.shiftscheduler.bussiness.validator;

import com.astro.shiftscheduler.domain.Employee;
import com.astro.shiftscheduler.domain.Shift;
import com.astro.shiftscheduler.domain.ShiftConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;

public interface RuleValidator {
    boolean validateRule(List<Shift> shifts , Employee employee , ShiftConfiguration config);
}

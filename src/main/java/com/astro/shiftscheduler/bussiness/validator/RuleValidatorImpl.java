package com.astro.shiftscheduler.bussiness.validator;

import com.astro.shiftscheduler.domain.Employee;
import com.astro.shiftscheduler.domain.Shift;
import com.astro.shiftscheduler.domain.ShiftConfiguration;
import com.astro.shiftscheduler.bussiness.validator.rule.ShiftRule;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Function to validate rules against shift/schedule and employee
 */
@Component
public class RuleValidatorImpl implements RuleValidator {

    /**
     * Functio should get List of rules in configuration.
     * @param shifts
     * @param employee
     * @param config
     * @return
     */
    @Override
    public boolean validateRule(List<Shift> shifts, Employee employee, ShiftConfiguration config) {
        List<ShiftRule> ruleList = config.getRules();

        for (ShiftRule rule : ruleList) {
            if (!rule.validate(shifts, employee, config)) {
                return false;
            }
        }
        return true;
    }
}
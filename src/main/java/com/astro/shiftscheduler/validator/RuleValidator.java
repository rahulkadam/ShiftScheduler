package com.astro.shiftscheduler.validator;

import com.astro.shiftscheduler.dao.dto.Employee;
import com.astro.shiftscheduler.dao.dto.Shift;
import com.astro.shiftscheduler.validator.rule.ShiftRule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleValidator implements Validator {

    @Override
    public boolean validateRule(List<Shift> shifts, Employee employee, ShiftRule rule) {
        return rule.validate(shifts , employee);
    }

    public boolean validateRule(List<Shift> shifts, Employee employee, List<ShiftRule> ruleList) {
        Boolean validated = true;
        for(ShiftRule rule :ruleList) {
            if(!rule.validate(shifts , employee)){
                return false;
            }
        }
        return true;
    }
}

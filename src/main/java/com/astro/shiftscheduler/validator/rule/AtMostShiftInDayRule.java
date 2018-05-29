package com.astro.shiftscheduler.validator.rule;

import com.astro.shiftscheduler.dao.dto.Employee;
import com.astro.shiftscheduler.dao.dto.Shift;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AtMostShiftInDayRule extends ShiftRule {

    private int mostinDay = 2;

    @Override
    public boolean validate(List<Shift> shifts, Employee employee) {
        return super.validate(shifts, employee);
    }
}

package com.astro.shiftscheduler.bussiness.validator.rule;

import com.astro.shiftscheduler.domain.Employee;
import com.astro.shiftscheduler.domain.Shift;
import com.astro.shiftscheduler.domain.ShiftConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AtMostShiftInDayRule extends ShiftRule {

    private int atMostShiftinDay = 1;

    @Override
    public boolean validate(List<Shift> shifts, Employee employee , ShiftConfiguration config) {
        if(shifts.isEmpty()) {
            return true;
        }
        int totalShiftPerDay = config.getNoOfShiftsPerDay();
        int lastIndex = shifts.size() - 1;
        Shift shift = shifts.get(lastIndex);
        while (shift.getShiftType() < totalShiftPerDay && lastIndex > totalShiftPerDay) {
            if(shift.getEmployee().getId() == employee.getId()) {
                return false;
            }
            lastIndex--;
            shift = shifts.get(lastIndex);
        }
        return true;
    }
}

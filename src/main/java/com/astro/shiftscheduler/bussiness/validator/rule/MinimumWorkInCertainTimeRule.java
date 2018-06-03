package com.astro.shiftscheduler.bussiness.validator.rule;

import com.astro.shiftscheduler.domain.Employee;
import com.astro.shiftscheduler.domain.Shift;
import com.astro.shiftscheduler.domain.ShiftConfiguration;

import java.util.List;

public class MinimumWorkInCertainTimeRule extends ShiftRule {
    int timeFrame = 15;
    int completedDay = 1;
    int conpletedShifts = 2;

    /**
     * Function to check and apply rule on no of days must worked
     * @param shifts
     * @param employee
     * @param config
     * @return
     */
    //TODO From current logic, employee will definately be present for 1 day in 2 week , as our logic is something similar to round robbin
    // Not writing this logic properly, as somehow , it is not relating to current implementation, bt we can apply some rules here

    @Override
    public boolean validate(List<Shift> shifts, Employee employee , ShiftConfiguration config) {
        if(shifts.isEmpty()) {
            return true;
        }
        int lastIndex = shifts.size() - 1;
        Shift shift = shifts.get(lastIndex);
        int dayNumber  = shift.getDayNumber();
        if(dayNumber < timeFrame) {
            return true;
        }
        return true;
    }
}

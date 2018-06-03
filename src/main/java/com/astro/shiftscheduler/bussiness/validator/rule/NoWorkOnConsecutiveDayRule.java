package com.astro.shiftscheduler.bussiness.validator.rule;

import com.astro.shiftscheduler.domain.Employee;
import com.astro.shiftscheduler.domain.Shift;
import com.astro.shiftscheduler.domain.ShiftConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Rule to check , no one should work on 2 consecutive day
 */
public class NoWorkOnConsecutiveDayRule extends ShiftRule {
    // we should make this configurable , as no of consecatinve days , we can use for any days
    int consecutiveDays = 2;

    @Override
    public boolean validate(List<Shift> shifts, Employee employee , ShiftConfiguration config) {
        // if shift is empty then re it's valid
        if(shifts.isEmpty()) {
            return true;
        }

        int totalShiftPerDay = config.getNoOfShiftsPerDay();
        int lastIndex = shifts.size() - 1;
        Shift shift = shifts.get(lastIndex);
        List<Shift> lastNDaysShift = new ArrayList<>();
        if(lastIndex <= totalShiftPerDay-1) {
            lastNDaysShift = shifts;
        } else {
            // retriving 1 days shift, as we need to check last day's
            int fromIndex = lastIndex - (consecutiveDays-1)*config.getNoOfShiftsPerDay();
            if (shift.getShiftType() != totalShiftPerDay) {
                // cutting todays shift also. we will need to check that
                fromIndex = fromIndex - shift.getShiftType();
            }
            if (lastIndex > fromIndex) {
                fromIndex = fromIndex > 0 ? fromIndex : 0;
                lastNDaysShift = shifts.subList(fromIndex, lastIndex);
            }
        }

        for(Shift shiftObj : lastNDaysShift) {
            if (shiftObj.getEmployee().getId() == employee.getId()) {
                return false;
            }
        }
        return true;
    }
}

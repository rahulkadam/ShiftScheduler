package com.astro.shiftscheduler.validator.rule;

import com.astro.shiftscheduler.dao.dto.Employee;
import com.astro.shiftscheduler.dao.dto.Shift;
import com.astro.shiftscheduler.dao.dto.ShiftConfiguration;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class NoWorkOnConsecutiveDayRule extends ShiftRule {

    @Override
    public boolean validate(List<Shift> shifts, Employee employee , ShiftConfiguration config) {
        if(shifts.isEmpty()) {
            return true;
        }
        int totalShiftPerDay = config.getNoOfShiftsPerDay();
        int lastIndex = shifts.size() - 1;
        Shift shift = shifts.get(lastIndex);
        List<Shift> lastdaysShift = new ArrayList<>();
        if(lastIndex <= totalShiftPerDay-1) {
            lastdaysShift = shifts;
        } else {
            if (shift.getShiftType() == totalShiftPerDay) {
                int fromIndex = lastIndex - config.getNoOfShiftsPerDay();
                if (lastIndex > fromIndex) {
                    fromIndex = fromIndex > 0 ? fromIndex : 0;
                    lastdaysShift = shifts.subList(fromIndex, lastIndex);
                }
            } else {
                int fromIndex = lastIndex - config.getNoOfShiftsPerDay() - shift.getShiftType();
                if (lastIndex > fromIndex) {
                    fromIndex = fromIndex > 0 ? fromIndex : 0;
                    lastdaysShift = shifts.subList(fromIndex, lastIndex);
                }
            }
        }

        for(Shift shiftObj : lastdaysShift) {
            if (shiftObj.getEmployee().getId() == employee.getId()) {
                return false;
            }
        }
        return true;
    }
}

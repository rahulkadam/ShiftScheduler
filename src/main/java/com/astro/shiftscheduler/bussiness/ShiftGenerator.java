package com.astro.shiftscheduler.bussiness;

import com.astro.shiftscheduler.dao.dto.Employee;
import com.astro.shiftscheduler.dao.dto.Shift;
import com.astro.shiftscheduler.dao.dto.ShiftConfiguration;
import com.astro.shiftscheduler.validator.RuleValidator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ShiftGenerator {

    @Autowired
    private RuleValidator ruleValidator;

    public List<Shift> generate(ShiftConfiguration config, List<Employee> employees) {

        List<Employee> empList = copyEmployeeList(employees);
        List<Shift> shifts = new ArrayList<>();
        DateTime date = config.getStartingDate();

        for (int i = 0; i < config.getScheduleSpanDays(); i++) {
            //Reloading Employee Bucket for shift allocation
            if (empList.size() < config.getNoOfShiftsPerDay()) {
                empList = copyEmployeeList(employees);
            }

            //
            for (int j = 0; j < config.getNoOfShiftsPerDay(); j++) {
                int getRandomEmployeeIndex = getRandomEmployeeIndex(empList.size());
                Employee employee = empList.get(getRandomEmployeeIndex);
                boolean isValid = ruleValidator.validateRule(shifts, employee, config.getRules());
                if (isValid) {
                    Shift shift = new Shift();
                    shift.setType("Type : " + j);
                    shift.setId((long) (Math.random()*10000));
                    shift.setTime(date);
                    shift.setEmployee(employee);
                    shift.setCreatedAt(new Date());
                    shift.setShiftType(j+1);
                    shifts.add(shift);
                    empList.remove(getRandomEmployeeIndex);
                } else {
                    j--;
                }
            }
            date = date.plusDays(1);
        }
        return shifts;
    }


    public List<Employee> copyEmployeeList(List<Employee> employees) {
        List<Employee> copyEmployeeList = new ArrayList<>();
        employees.stream().forEach(employee -> {
            copyEmployeeList.add(new Employee(employee));
        });
        return copyEmployeeList;
    }

    private int getRandomEmployeeIndex(int n) {
        if(n==1) {
            return 0;
        }
        Random random = new Random();
        int index = 0 + random.nextInt(n - 1);
        return index;
    }

}

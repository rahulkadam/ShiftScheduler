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

    public List<Shift> generate(ShiftConfiguration config, List<Employee> employees) throws Exception {

        List<Employee> empList = copyEmployeeList(employees);
        List<Shift> shifts = new ArrayList<>();
        DateTime date = config.getStartingDate();

        int continueFailed = 0;
        boolean isContinueFailedBefore = false;
        for (int i = 0; i < config.getScheduleSpanDays(); i++) {
            //Reloading Employee Bucket for shift allocation
            if (empList.size() < config.getNoOfShiftsPerDay()) {
                empList = copyEmployeeList(employees);
            }

            //
            for (int j = 0; j < config.getNoOfShiftsPerDay(); j++) {
                int getRandomEmployeeIndex = getRandomEmployeeIndex(empList.size());
                if (continueFailed == empList.size()) {
                    if (!isContinueFailedBefore) {
                        continueFailed = 0;
                        empList = copyEmployeeList(employees);
                        isContinueFailedBefore = true;
                    } else {
                        throw new Exception("Not possible to create schedule with Rules and employee and other details. please check again");
                    }
                }
                Employee employee = empList.get(getRandomEmployeeIndex);
                boolean isValid = ruleValidator.validateRule(shifts, employee, config);
                if (isValid) {
                    Shift shift = new Shift();
                    shift.setType("Type : " + j);
                    shift.setId((long) (Math.random() * 10000));
                    shift.setTime(date);
                    shift.setEmployee(employee);
                    shift.setCreatedAt(new Date());
                    shift.setShiftType(j + 1);
                    shifts.add(shift);
                    empList.remove(getRandomEmployeeIndex);
                    continueFailed = 0;
                } else {
                    j--;
                    continueFailed++;
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

    private int getRandomEmployeeIndex(int n) throws Exception {
        if(n < 1) {
            throw new Exception("Error Occured due to wrong configuration of data, Schedule shifts is not possible");
        }
        if (n == 1) {
            return 0;
        }
        Random random = new Random();
        int index = 0 + random.nextInt(n - 1);
        return index;
    }
}

package com.astro.shiftscheduler.helper;

import com.astro.shiftscheduler.bussiness.ShiftGenerator;
import com.astro.shiftscheduler.dao.dto.Employee;
import com.astro.shiftscheduler.dao.dto.Shift;
import com.astro.shiftscheduler.dao.dto.ShiftConfiguration;
import com.astro.shiftscheduler.validator.rule.AtMostShiftInDayRule;
import com.astro.shiftscheduler.validator.rule.MinimumWorkInCertainTimeRule;
import com.astro.shiftscheduler.validator.rule.NoWorkOnConsecutiveDayRule;
import com.astro.shiftscheduler.validator.rule.ShiftRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShiftServiceAdapter {


    @Autowired
    private ShiftGenerator shiftGenerator;

    public List<Shift> generateSchedule(int noOfEmployee , int noOfDays , int noOfShiftPerDay) throws Exception {
        List<Employee> employeeList = generateDefaultEmployeeList(noOfEmployee);
        ShiftConfiguration config = generateDefaultShiftConfiguration(noOfDays, noOfShiftPerDay);
        List<Shift> shifts = shiftGenerator.generate(config,employeeList);
        return shifts;
    }

    private List<Employee> generateDefaultEmployeeList(int n) {
        List<Employee> employeeList = new ArrayList<>();
        for(int i = 0; i< n ; i++) {
            Employee employee = new Employee();
            employee.setId(i+1);
            employee.setName("Employee-" + employee.getId());
            employeeList.add(employee);
        }
        return employeeList;
    }

    private ShiftConfiguration generateDefaultShiftConfiguration(int noOfDays , int noOfShiftPerDays) {
        List<ShiftRule> ruleList = getDefaultRuleList();
        ShiftConfiguration config = new ShiftConfiguration("Default generator" , noOfShiftPerDays , noOfDays , ruleList);
        return config;
    }

    private List<ShiftRule> getDefaultRuleList() {
        List<ShiftRule> ruleList = new ArrayList<>();
        ruleList.add(new AtMostShiftInDayRule());
        ruleList.add(new MinimumWorkInCertainTimeRule());
        ruleList.add(new NoWorkOnConsecutiveDayRule());
        return ruleList;
    }
}

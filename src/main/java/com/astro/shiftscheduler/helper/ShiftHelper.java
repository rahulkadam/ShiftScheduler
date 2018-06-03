package com.astro.shiftscheduler.helper;

import com.astro.shiftscheduler.bussiness.ConfigurationManager;
import com.astro.shiftscheduler.bussiness.EmployeeManager;
import com.astro.shiftscheduler.bussiness.ShiftManager;
import com.astro.shiftscheduler.domain.Employee;
import com.astro.shiftscheduler.domain.Shift;
import com.astro.shiftscheduler.domain.ShiftConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ShiftHelper {

    @Autowired
    private ShiftManager shiftManager;

    @Autowired
    private ConfigurationManager configurationManager;

    @Autowired
    private EmployeeManager employeeManager;

    /**
     * Function to generateSchedule schedule based on provided information
     * @param noOfEmployee
     * @param noOfDays
     * @param noOfShiftPerDay
     * @return
     * @throws Exception
     */
    @Transactional
    public List<Shift> generateSchedule(int noOfEmployee , int noOfDays , int noOfShiftPerDay) throws Exception {
        List<Employee> employeeList = employeeManager.generateEmployeeListForNEmployee(noOfEmployee);
        ShiftConfiguration config = configurationManager.createConfiguration(noOfDays, noOfShiftPerDay);
        List<Shift> shifts = shiftManager.generateSchedule(config,employeeList);
        return shifts;
    }

}

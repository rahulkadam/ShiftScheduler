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

    /**
     * Function to generate schedule and applya rules also
     *
     * @param config
     * @param employees
     * @return
     * @throws Exception
     */
    public List<Shift> generateSchedule(ShiftConfiguration config, List<Employee> employees) throws Exception {

        // Loading list to pool
        List<Employee> employeePool = reloadEmployeePool(employees);
        List<Shift> scheduledShiftList = new ArrayList<>();
        DateTime date = config.getStartingDate();

        int poolFailedForAllCount = 0;
        boolean isPoolRefilledOnce = false;

        for (int i = 0; i < config.getScheduleSpanDays(); i++) {
            //Reloading Employee Pool for shift allocation
            if (employeePool.size() < config.getNoOfShiftsPerDay()) {
                employeePool = reloadEmployeePool(employees);
            }

            // Loop will keep running for no of  shifts days schedule generation
            for (int j = 0; j < config.getNoOfShiftsPerDay(); j++) {
                int getRandomEmployeeIndex = getRandomEmployeeIndex(employeePool.size());

                // Check
                if (poolFailedForAllCount == employeePool.size()) {
                    if (!isPoolRefilledOnce) {
                        poolFailedForAllCount = 0;
                        employeePool = reloadEmployeePool(employees);
                        isPoolRefilledOnce = true;
                    } else {
                        throw new Exception("Not possible to create schedule with Rules and employee and other details. please check again");
                    }
                }
                Employee employee = employeePool.get(getRandomEmployeeIndex);

                // Applying Rule set on shift and employee to add in that shift.
                // We can add any no of rules to configuration. those will get execute here.
                boolean isValid = ruleValidator.validateRule(scheduledShiftList, employee, config);

                if (isValid) {
                    Shift shift = new Shift();
                    shift.setType("Type : " + j);
                    shift.setId((long) (Math.random() * 10000));
                    shift.setTime(date);
                    shift.setEmployee(employee);
                    shift.setCreatedAt(new Date());
                    shift.setShiftType(j + 1);
                    shift.setDayNumber(i+1);
                    scheduledShiftList.add(shift);
                    employeePool.remove(getRandomEmployeeIndex);
                    poolFailedForAllCount = 0;
                } else {
                    j--;
                    poolFailedForAllCount++;
                }
            }
            date = date.plusDays(1);
        }
        return scheduledShiftList;
    }


    /**
     * Function to fill queue again once we schedule employee.
     *
     * @param employees
     * @return
     */
    public List<Employee> reloadEmployeePool(List<Employee> employees) {
        List<Employee> copyEmployeeList = new ArrayList<>();
        employees.stream().forEach(employee -> {
            copyEmployeeList.add(new Employee(employee));
        });
        return copyEmployeeList;
    }

    /**
     * Function to accept no of employee available in queue and selecting random from them
     *
     * @param n
     * @return
     * @throws Exception
     */
    private int getRandomEmployeeIndex(int n) throws Exception {
        if (n < 1) {
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

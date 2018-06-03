package com.astro.shiftscheduler.bussiness;

import com.astro.shiftscheduler.bussiness.validator.RuleValidator;
import com.astro.shiftscheduler.domain.Employee;
import com.astro.shiftscheduler.domain.Shift;
import com.astro.shiftscheduler.domain.ShiftConfiguration;
import com.astro.shiftscheduler.helper.ShiftHelper;
import com.astro.shiftscheduler.bussiness.validator.RuleValidatorImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiftManagerTest {

    @InjectMocks private ShiftManager shiftManager;
    @Mock private RuleValidator ruleValidator;
    @Mock private EmployeeManager employeeManager;
    @Mock private ConfigurationManager configurationManager;

    @Test
    public void shouldGenerateShifts() throws Exception {
        Mockito.when(ruleValidator.validateRule(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(true);
        List<Shift> list = shiftManager.generateSchedule(getConfig(3,3) ,getEmployee(9));
        Assert.assertTrue(list.size() == 9);
    }

    @Test(expected = Exception.class)
    public void shouldFailedgenerateShifts() throws Exception {
        Mockito.when(ruleValidator.validateRule(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(true);
        List<Shift> list = shiftManager.generateSchedule(getConfig(3,12) ,getEmployee(7));
        Assert.assertTrue(list.size() == 9);
    }

    public ShiftConfiguration getConfig(int noOfDays , int shiftPerDay) {
        Mockito.when(configurationManager.createConfiguration(noOfDays,shiftPerDay)).thenReturn(null);
        return null;
    }

    public List<Employee> getEmployee(int n) {
        Mockito.when(employeeManager.generateEmployeeListForNEmployee(n)).thenReturn(null);
        return null;
    }

    private List<Shift> getShiftList(int n) {
        List<Shift> list = new ArrayList<>();
        for(int i=0; i< n ; i++) {
            list.add(getShift(i));
        }
        return list;
    }

    private Shift getShift(int n) {
        Shift shift = new Shift();
        shift.setId((long)n);
        return shift;
    }
}

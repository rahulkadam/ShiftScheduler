package com.astro.shiftscheduler.bussiness;

import com.astro.shiftscheduler.bussiness.validator.RuleValidator;
import com.astro.shiftscheduler.bussiness.validator.rule.ShiftRule;
import com.astro.shiftscheduler.dao.repository.ShiftRepository;
import com.astro.shiftscheduler.domain.Employee;
import com.astro.shiftscheduler.domain.Shift;
import com.astro.shiftscheduler.domain.ShiftConfiguration;
import com.astro.shiftscheduler.helper.ShiftHelper;
import com.astro.shiftscheduler.bussiness.validator.RuleValidatorImpl;
import org.junit.Assert;
import org.junit.Ignore;
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
    @Mock private
    ShiftRepository shiftRepository;

    @Test
    @Ignore
    public void shouldGenerateShifts() throws Exception {
        Mockito.doNothing().when(shiftRepository.saveAll(Mockito.any()));
        Mockito.when(ruleValidator.validateRule(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(true);
        List<Shift> list = shiftManager.generateSchedule(getConfig(3,3) ,getEmployeeList(9));
        Assert.assertTrue(list.size() == 9);
    }

    @Test(expected = Exception.class)
    @Ignore
    public void shouldFailedgenerateShifts() throws Exception {
        Mockito.doNothing().when(shiftRepository.saveAll(Mockito.any()));
        Mockito.when(ruleValidator.validateRule(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(true);
        List<Shift> list = shiftManager.generateSchedule(getConfig(3,12) ,getEmployeeList(7));
        Assert.assertTrue(list.size() == 9);
    }

    public ShiftConfiguration getConfig(int noOfDays , int shiftPerDay) {
        ShiftConfiguration config = new ShiftConfiguration("Default generator" , shiftPerDay , noOfDays ,null);
        return config;
    }

    public List<Employee> getEmployeeList(int n) {
        List<Employee> list = new ArrayList<>();
        for(int i=0;i<n ; i++) {
            Employee employee = new Employee();
            employee.setId((long)i);
            employee.setName("Employee-" + i);
            list.add(employee);
        }
        return list;
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

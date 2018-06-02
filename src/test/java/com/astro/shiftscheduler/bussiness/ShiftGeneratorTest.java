package com.astro.shiftscheduler.bussiness;

import com.astro.shiftscheduler.dao.dto.Employee;
import com.astro.shiftscheduler.dao.dto.Shift;
import com.astro.shiftscheduler.dao.dto.ShiftConfiguration;
import com.astro.shiftscheduler.helper.ShiftServiceAdapter;
import com.astro.shiftscheduler.validator.RuleValidator;
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
public class ShiftGeneratorTest {

    @Spy  private ShiftServiceAdapter shiftServiceAdapter;
    @InjectMocks private ShiftGenerator shiftGenerator;
    @Mock private RuleValidator ruleValidator;

    @Test
    public void shouldGenerateShifts() throws Exception {
        Mockito.when(ruleValidator.validateRule(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(true);
        List<Shift> list = shiftGenerator.generateSchedule(getConfig(3,3) ,getEmployee(9));
        Assert.assertTrue(list.size() == 9);
    }

    @Test(expected = Exception.class)
    public void shouldFailedgenerateShifts() throws Exception {
        Mockito.when(ruleValidator.validateRule(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(true);
        List<Shift> list = shiftGenerator.generateSchedule(getConfig(3,12) ,getEmployee(7));
        Assert.assertTrue(list.size() == 9);
    }

    public ShiftConfiguration getConfig(int noOfDays , int shiftPerDay) {
        return shiftServiceAdapter.generateDefaultShiftConfiguration(noOfDays, shiftPerDay);
    }

    public List<Employee> getEmployee(int n) {
        return shiftServiceAdapter.generateDefaultEmployeeList(n);
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

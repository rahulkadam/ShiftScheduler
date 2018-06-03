package com.astro.shiftscheduler.helper;

import com.astro.shiftscheduler.bussiness.ShiftManager;
import com.astro.shiftscheduler.domain.Shift;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiftHelperTest {

    @Mock public
    ShiftManager shiftManager;
    @InjectMocks public ShiftHelper shiftHelper;

    @Test
    @Ignore
    public void shouldGenerateSchedule() throws Exception {
        List<Shift> li = getShiftList(8);
        Mockito.when(shiftManager.generateSchedule(Mockito.any(), Mockito.any())).thenReturn(li);
        List<Shift> list = shiftHelper.generateSchedule(10 , 2,4);
        Assert.assertTrue(list.size() == 8);
    }

    @Test(expected = Exception.class)
    @Ignore
    public void shouldFailedGenerateSchedule() throws Exception {
        List<Shift> li = getShiftList(8);
        Mockito.doThrow(new Exception("")).when(shiftManager.generateSchedule(Mockito.any(), Mockito.any()));
        List<Shift> list = shiftHelper.generateSchedule(10 , 12,4);
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

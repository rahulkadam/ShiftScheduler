package com.astro.shiftscheduler.helper;

import com.astro.shiftscheduler.bussiness.ShiftGenerator;
import com.astro.shiftscheduler.dao.dto.Shift;
import org.junit.Assert;
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
public class ShiftServiceAdapterTest {

    @Mock public
    ShiftGenerator shiftGenerator;
    @InjectMocks public ShiftServiceAdapter shiftServiceAdapter;

    @Test
    public void shouldGenerateSchedule() throws Exception {
        List<Shift> li = getShiftList(8);
        Mockito.when(shiftGenerator.generateSchedule(Mockito.any(), Mockito.any())).thenReturn(li);
        List<Shift> list = shiftServiceAdapter.generateSchedule(10 , 2,4);
        Assert.assertTrue(list.size() == 8);
    }

    @Test(expected = Exception.class)
    public void shouldFailedGenerateSchedule() throws Exception {
        List<Shift> li = getShiftList(8);
        Mockito.doThrow(new Exception("")).when(shiftGenerator.generateSchedule(Mockito.any(), Mockito.any()));
        List<Shift> list = shiftServiceAdapter.generateSchedule(10 , 12,4);
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

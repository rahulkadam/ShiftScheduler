package com.astro.shiftscheduler.helper;

import com.astro.shiftscheduler.dao.dto.Shift;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShiftServiceAdapter {

    public List<Shift> getShifts() {
        List<Shift> shifts = new ArrayList<>();
        String shift = "shifts-Employee";
        Long id = 1000L;
        for(int i=0; i < 10 ; i++) {
            Shift s = new Shift();
            s.setId(id+i);
            s.setType(shift + i);
            if(i % 2 == 0) {
                s.setTime(8);
            } else {
                s.setTime(12);
            }
            shifts.add(s);
        }
        return shifts;
    }
}

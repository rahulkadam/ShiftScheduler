package com.astro.shiftscheduler.rest;

import com.astro.shiftscheduler.dao.dto.Shift;
import com.astro.shiftscheduler.helper.ShiftServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/shift")
public class ShiftController {

    @Autowired
    private ShiftServiceAdapter shiftServiceAdapter;

    @GetMapping(path = "/schedule")
    public List<Shift> scheduleShifts(@RequestParam("employee") int employeeCOunt , @RequestParam("dayspan") int daysSpan ,  @RequestParam("shift") int shiftCount) throws Exception {
        return shiftServiceAdapter.generateSchedule(employeeCOunt , daysSpan , shiftCount);
    }

}

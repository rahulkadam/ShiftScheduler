package com.astro.shiftscheduler.bussiness;

import com.astro.shiftscheduler.bussiness.validator.rule.AtMostShiftInDayRule;
import com.astro.shiftscheduler.bussiness.validator.rule.MinimumWorkInCertainTimeRule;
import com.astro.shiftscheduler.bussiness.validator.rule.NoWorkOnConsecutiveDayRule;
import com.astro.shiftscheduler.bussiness.validator.rule.ShiftRule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RuleManager {

    /**
     * Getting default Rule List which we should apply in first hand only
     * @return
     */
    public List<ShiftRule> getDefaultRuleList() {
        List<ShiftRule> ruleList = new ArrayList<>();
        ruleList.add(new AtMostShiftInDayRule());
        ruleList.add(new MinimumWorkInCertainTimeRule());
        ruleList.add(new NoWorkOnConsecutiveDayRule());
        return ruleList;
    }
}

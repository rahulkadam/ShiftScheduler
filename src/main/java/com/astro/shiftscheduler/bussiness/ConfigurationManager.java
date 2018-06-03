package com.astro.shiftscheduler.bussiness;

import com.astro.shiftscheduler.dao.repository.ShiftConfigurationRepository;
import com.astro.shiftscheduler.domain.ShiftConfiguration;
import com.astro.shiftscheduler.bussiness.validator.rule.ShiftRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigurationManager {

    @Autowired
    public RuleManager ruleManager;
    @Autowired
    private ShiftConfigurationRepository repository;

    public ShiftConfiguration createConfiguration(int noOfDays , int noOfShiftPerDays) {
        List<ShiftRule> ruleList = ruleManager.getDefaultRuleList();
        ShiftConfiguration config = new ShiftConfiguration("Default generator" , noOfShiftPerDays , noOfDays , ruleList);
        repository.save(config);
        return config;
    }
}

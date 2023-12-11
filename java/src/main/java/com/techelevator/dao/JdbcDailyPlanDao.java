package com.techelevator.dao;

import com.techelevator.model.DailyPlan;
import com.techelevator.model.DailyPlanDto;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcDailyPlanDao implements DailyPlanDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcDailyPlanDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public DailyPlan createDailyPlan(DailyPlanDto dailyPlanDto){
//        DailyPlan dailyPlan = new DailyPlan();
//
//    }
//
//    public DailyPlan dailyPlanDToDailyPlan(DailyPlanDto dailyPlanDto){
//        DailyPlan dailyPlan = new DailyPlan();
//        dailyPlan.setPlanName(dailyPlanDto.getPlanName());
//
//    }
}

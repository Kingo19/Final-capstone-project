package com.techelevator.dao;

import com.techelevator.model.DailyPlan;
import com.techelevator.model.DailyPlanDto;

import java.util.List;

public interface DailyPlanDao {
    public DailyPlan createDailyPlan(DailyPlanDto dailyPlanDto, int userId);
    public DailyPlan getPlanByDate(String date, int userId);
    public List<DailyPlan> getAllUserPlans(int userId);
}

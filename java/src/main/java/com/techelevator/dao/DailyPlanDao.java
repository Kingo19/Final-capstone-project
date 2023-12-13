package com.techelevator.dao;

import com.techelevator.model.DailyPlan;
import com.techelevator.model.DailyPlanDto;

import java.util.List;

public interface DailyPlanDao {
    public DailyPlan createDailyPlan(DailyPlanDto dailyPlanDto, int userId);
    public DailyPlan getPlanById(int planId, int userId);
    public DailyPlan getPlanByDate(String date, int userId);
//    public List<DailyPlan> getAllUserPlans(int userId);
//    public DailyPlan updateDailyPlan(int planId, int userId, DailyPlanDto dailyPlanDto);
    public List<DailyPlan> getNextWeekPlans(int userId);

}

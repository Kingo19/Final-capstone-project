package com.techelevator.dao;

import com.techelevator.model.DailyPlan;
import com.techelevator.model.DailyPlanDto;
import com.techelevator.model.Meal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDailyPlanDao implements DailyPlanDao{
    private final JdbcTemplate jdbcTemplate;
    private final JdbcMealDao jdbcMealDao;

    public JdbcDailyPlanDao(JdbcTemplate jdbcTemplate, JdbcMealDao jdbcMealDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcMealDao = jdbcMealDao;
    }

    public DailyPlan createDailyPlan(DailyPlanDto dailyPlanDto, int userId){
        DailyPlan dailyPlan = new DailyPlan();
        String sql = "INSERT INTO daily_plan(daily_plan_name,dayofplan,user_id) VALUES (?,?,?) RETURNING daily_plan_id;";
        try{
            dailyPlan = dailyPlanDToDailyPlan(dailyPlanDto, userId);

            dailyPlan.setPlanId(jdbcTemplate.queryForObject(sql, int.class, dailyPlan.getPlanName(), Date.valueOf(dailyPlan.getDateOfPlan()), userId));
            insertIntoDailyPlanMeals(dailyPlan);
        } catch (Exception e){
            System.out.println("Error creating plan, " + e);
        }
        return dailyPlan;
    }

    public DailyPlan dailyPlanDToDailyPlan(DailyPlanDto dailyPlanDto, int userId){
        DailyPlan dailyPlan = new DailyPlan();
        List<Meal> mealList = new ArrayList<>();
        dailyPlan.setPlanName(dailyPlanDto.getPlanName());
        dailyPlan.setDateOfPlan(LocalDate.parse(dailyPlanDto.getDate()));
        dailyPlan.setUserId(userId);
        for(String currentId : dailyPlanDto.getMealIds()){
            int currentIdCasted = Integer.parseInt(currentId);
            Meal currentMeal = jdbcMealDao.getMealByMealID(currentIdCasted, userId);
            mealList.add(currentMeal);
        }
        dailyPlan.setPlanMeals(mealList);
        return dailyPlan;
    }

//    public int getWeekdayId(String weekdayName){
//        String sql = "SELECT weekday_id FROM weekday WHERE weekday = ?;";
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, weekdayName);
//        int weekdayId = 0;
//        while(result.next()){
//            weekdayId = result.getInt("weekday_id");
//        }
//        return weekdayId;
//    }

    public void insertIntoDailyPlanMeals(DailyPlan dailyPlan){
        String sql = "INSERT INTO daily_plan_meals(daily_plan_id,meal_id) VALUES (?,?);";
        for(Meal currentMeal : dailyPlan.getPlanMeals()){
            jdbcTemplate.update(sql, dailyPlan.getPlanId(), currentMeal.getMealId());
        }
    }

//    public DailyPlan getPlanByDate(String date, int userId){
//        DailyPlan plan = new DailyPlan();
//        List<Meal> mealList = new ArrayList<>();
//        String sql = "SELECT * FROM daily_plan\n" +
//                "JOIN daily_plan_meals ON daily_plan_meals.daily_plan_id = daily_plan.daily_plan_id\n" +
//                "WHERE daily_plan.dayofplan = '?' AND daily_plan.user_id = ?;";
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, date, userId);
//        while(result.next()){
//            plan.setPlanName(result.getString("daily_plan_name"));
//            plan.setPlanId(result.getInt("daily_plan_id"));
//            mealList.add(jdbcMealDao.getMealByMealID(result.getInt("meal_id"), userId));
//        }
//        plan.setDateOfPlan(LocalDate.parse(date));
//        plan.setUserId(userId);
//        plan.setPlanMeals(mealList);
//        return plan;
//    }
}

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

    public void insertIntoDailyPlanMeals(DailyPlan dailyPlan){
        String sql = "INSERT INTO daily_plan_meals(daily_plan_id,meal_id) VALUES (?,?);";
        for(Meal currentMeal : dailyPlan.getPlanMeals()){
            jdbcTemplate.update(sql, dailyPlan.getPlanId(), currentMeal.getMealId());
        }
    }

    public DailyPlan getPlanByDate(String date, int userId){
        DailyPlan plan = new DailyPlan();
        List<Meal> mealList = new ArrayList<>();
        String sql = "SELECT daily_plan_name, daily_plan.daily_plan_id, meal_id FROM daily_plan\n" +
                "JOIN daily_plan_meals ON daily_plan_meals.daily_plan_id = daily_plan.daily_plan_id\n" +
                "WHERE daily_plan.dayofplan = ? AND daily_plan.user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, Date.valueOf(date), userId);
        while(result.next()){
            plan.setPlanName(result.getString("daily_plan_name"));
            plan.setPlanId(result.getInt("daily_plan_id"));
            mealList.add(jdbcMealDao.getMealByMealID(result.getInt("meal_id"), userId));
        }
        plan.setDateOfPlan(LocalDate.parse(date));
        plan.setUserId(userId);
        plan.setPlanMeals(mealList);
        return plan;
    }

    public DailyPlan getPlanById(int planId, int userId){
        DailyPlan plan = new DailyPlan();
        List<Meal> mealList = new ArrayList<>();
        String sql = "SELECT daily_plan_name, daily_plan.dayofplan, meal_id FROM daily_plan\n" +
                "JOIN daily_plan_meals ON daily_plan_meals.daily_plan_id = daily_plan.daily_plan_id\n" +
                "WHERE daily_plan.daily_plan_id = ? AND daily_plan.user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, planId, userId);
        while(result.next()){
            plan.setPlanName(result.getString("daily_plan_name"));
            plan.setDateOfPlan(result.getDate("dayofplan").toLocalDate());
            mealList.add(jdbcMealDao.getMealByMealID(result.getInt("meal_id"), userId));
        }
        plan.setPlanId(planId);
        plan.setUserId(userId);
        plan.setPlanMeals(mealList);
        return plan;
    }

    public List<DailyPlan> getAllUserPlans(int userId){
        List<DailyPlan> planList = new ArrayList<>();
        List<Integer> idsList = new ArrayList<>();
        String sql = "SELECT daily_plan_id FROM daily_plan WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while(result.next()){
            idsList.add(result.getInt("daily_plan_id"));
        }
        for(Integer currentId : idsList){
            planList.add(getPlanById(currentId, userId));
        }
        return planList;
    }

    public DailyPlan updateDailyPlan(int planId, int userId, DailyPlanDto dailyPlanDto){
        String sql = "UPDATE daily_plan SET daily_plan_name, dayofplan WHERE daily_plan_id = ?;";
        DailyPlan plan = dailyPlanDToDailyPlan(dailyPlanDto, userId);
        try{
            plan.setPlanId(planId);
            if (plan.getUserId() == userId){
                deleteFromPlanMeals(plan);
                jdbcTemplate.update(sql, plan.getPlanName(), Date.valueOf(plan.getDateOfPlan()), planId);
                insertIntoDailyPlanMeals(plan);
            }
        }catch (Exception e){
            System.out.println("Error updating plan, " + e);
        }
        return plan;
    }

    public void deleteFromPlanMeals(DailyPlan plan){
        String sql = "DELETE FROM daily_plan_meals WHERE daily_plan_id = ?;";
        jdbcTemplate.update(sql, plan.getPlanId());
    }
}

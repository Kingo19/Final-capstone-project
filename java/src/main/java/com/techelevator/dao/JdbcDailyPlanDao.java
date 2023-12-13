package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class JdbcDailyPlanDao implements DailyPlanDao{
    private final JdbcTemplate jdbcTemplate;
    private final JdbcMealDao jdbcMealDao;
    private final String SQL_BEGIN = "BEGIN;";
    private final String SQL_ROLLBACK = "ROLLBACK;";
    private final String SQL_COMMIT = "COMMIT;";


    public JdbcDailyPlanDao(DataSource dataSource, JdbcMealDao jdbcMealDao) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcMealDao = jdbcMealDao;
    }

    public DailyPlan createDailyPlan(DailyPlanDto dailyPlanDto, int userId){
        DailyPlan dailyPlan = new DailyPlan();
        String sql = "INSERT INTO daily_plan(daily_plan_name,dayofplan,user_id) VALUES (?,?,?) RETURNING daily_plan_id;";
        try{
            jdbcTemplate.update(SQL_BEGIN);
            dailyPlan = dailyPlanDToDailyPlan(dailyPlanDto, userId);

            dailyPlan.setPlanId(jdbcTemplate.queryForObject(sql, int.class, dailyPlan.getPlanName(), Date.valueOf(dailyPlan.getDateOfPlan()), userId));
            insertIntoDailyPlanMeals(dailyPlan);
            jdbcTemplate.update(SQL_COMMIT);
        } catch (Exception e){
            jdbcTemplate.update(SQL_ROLLBACK);
            throw new DaoException("Error creating plan.");
        }
        return dailyPlan;

    }

    public DailyPlan dailyPlanDToDailyPlan(DailyPlanDto dailyPlanDto, int userId){
        DailyPlan dailyPlan = new DailyPlan();
        List<MealTime> mealList = new ArrayList<>();
        dailyPlan.setPlanName(dailyPlanDto.getPlanName());
        dailyPlan.setDateOfPlan(LocalDate.parse(dailyPlanDto.getDate()));
        dailyPlan.setUserId(userId);
        for(MealTimeDto currentMealTime : dailyPlanDto.getMealsWithTime()){
            mealList.add(mealTimeDtoToMeal(currentMealTime, userId));
        }
        dailyPlan.setMealTimes(mealList);
        return dailyPlan;
    }

    public void insertIntoDailyPlanMeals(DailyPlan dailyPlan){
        String sql = "INSERT INTO daily_plan_meals(daily_plan_id,meal_id,meal_time) VALUES (?,?,?);";
        try{
            for(MealTime currentMeal : dailyPlan.getMealTimes()){
                jdbcTemplate.update(sql, dailyPlan.getPlanId(), currentMeal.getMealId(), currentMeal.getMealTime());
            }
        } catch (Exception e){
            throw new DaoException("Error. " + e);
        }
    }

    public DailyPlan getPlanByDate(String date, int userId){
        DailyPlan plan = new DailyPlan();
        List<MealTime> mealList = new ArrayList<>();
        String sql = "SELECT daily_plan_name, daily_plan.daily_plan_id, meal_id, meal_time FROM daily_plan\n" +
                "JOIN daily_plan_meals ON daily_plan_meals.daily_plan_id = daily_plan.daily_plan_id\n" +
                "WHERE daily_plan.dayofplan = ? AND daily_plan.user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, Date.valueOf(date), userId);
        while(result.next()){
            Meal meal = (jdbcMealDao.getMealByMealID(result.getInt("meal_id"), userId));
            MealTime mealTime = setMealTimeInfo(meal);
            mealTime.setMealTime(result.getTime("meal_time"));
            plan.setPlanName(result.getString("daily_plan_name"));
            plan.setPlanId(result.getInt("daily_plan_id"));

            mealList.add(mealTime);
        }
        plan.setDateOfPlan(LocalDate.parse(date));
        plan.setUserId(userId);
        plan.setMealTimes(mealList);
        return plan;
    }

    public DailyPlan getPlanById(int planId, int userId){
        DailyPlan plan = new DailyPlan();
        List<MealTime> mealList = new ArrayList<>();
        String sql = "SELECT daily_plan_name, daily_plan.dayofplan, meal_id, meal_time FROM daily_plan\n" +
                "JOIN daily_plan_meals ON daily_plan_meals.daily_plan_id = daily_plan.daily_plan_id\n" +
                "WHERE daily_plan.daily_plan_id = ? AND daily_plan.user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, planId, userId);
        while(result.next()){
            Meal meal = (jdbcMealDao.getMealByMealID(result.getInt("meal_id"), userId));
            MealTime mealTime = setMealTimeInfo(meal);
            mealTime.setMealTime(result.getTime("meal_time"));
            plan.setPlanName(result.getString("daily_plan_name"));
            plan.setDateOfPlan(result.getDate("dayofplan").toLocalDate());

            mealList.add(mealTime);
        }
        plan.setPlanId(planId);
        plan.setUserId(userId);
        plan.setMealTimes(mealList);
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

    public List<DailyPlan> getNextWeekPlans(int userId){
        List<Integer> idsList = new ArrayList<>();
        List<DailyPlan> planList = new ArrayList<>();
        String sql = "SELECT daily_plan_id FROM daily_plan \n" +
                "WHERE dayofplan BETWEEN current_date AND (current_date + 7) \n" +
                "AND user_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while(result.next()){
            idsList.add(result.getInt("daily_plan_id"));
        }
        for(Integer currentId : idsList){
            planList.add(getPlanById(currentId, userId));
        }
        return planList;
    }

    private MealTime mealTimeDtoToMeal(MealTimeDto mealTimeDto, int userId){
        MealTime mealTime = new MealTime();
        Meal meal = jdbcMealDao.getMealByMealID(mealTimeDto.getMealId(), userId);
        mealTime.setMealId(mealTimeDto.getMealId());
        mealTime.setMealTime(Time.valueOf(mealTimeDto.getTimeInString()));
        mealTime.setMealName(meal.getMealName());
        mealTime.setRecipeInfo(meal.getRecipeInfo());
        mealTime.setType(meal.getType());
        return mealTime;
    }

    private MealTime setMealTimeInfo(Meal meal){
        MealTime mealTime = new MealTime();
        mealTime.setType(meal.getType());
        mealTime.setRecipeInfo(meal.getRecipeInfo());
        mealTime.setMealName(meal.getMealName());
        mealTime.setMealId(meal.getMealId());
        return mealTime;
    }
}

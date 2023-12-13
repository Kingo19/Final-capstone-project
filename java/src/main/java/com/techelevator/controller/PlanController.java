package com.techelevator.controller;

import com.techelevator.dao.DailyPlanDao;
import com.techelevator.dao.JdbcDailyPlanDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.DailyPlan;
import com.techelevator.model.DailyPlanDto;
import com.techelevator.model.Meal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PlanController {
    private DailyPlanDao dailyPlanDao;
    private UserDao userDao;

    public PlanController(DailyPlanDao dailyPlanDao, UserDao userDao) {
        this.dailyPlanDao = dailyPlanDao;
        this.userDao  = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "user/plans/add", method = RequestMethod.POST)
    public DailyPlan addDailyPlan(Principal principal, @Valid @RequestBody DailyPlanDto dailyPlanDto){
        try{
            int userId = userDao.getUserByUsername(principal.getName()).getId();
            return dailyPlanDao.createDailyPlan(dailyPlanDto, userId);
        } catch (Exception e){
            throw e;
        }
    }

//    @RequestMapping(path = "user/plans/{id}", method = RequestMethod.GET)
//    public DailyPlan getPlanById(Principal principal, @PathVariable int id){
//        DailyPlan plan = new DailyPlan();
//        try{
//            int userId = userDao.getUserByUsername(principal.getName()).getId();
//            plan = dailyPlanDao.getPlanById(id, userId);
//        } catch (NullPointerException e) {
//            throw new DaoException("Unauthorized. Please log in: " + e);
//        }
//        if(plan.getPlanId()==0){
//            throw new DaoException("Plan not Found");
//        }
//        return plan;
//    }
    @RequestMapping(path = "user/plans/{date}", method = RequestMethod.GET)
    public DailyPlan getPlanByDate(Principal principal, @PathVariable String date){
        DailyPlan plan = new DailyPlan();
        try{
            int userId = userDao.getUserByUsername(principal.getName()).getId();
            plan = dailyPlanDao.getPlanByDate(date, userId);
        } catch (NullPointerException e) {
            throw new DaoException("Unauthorized. Please log in: " + e);
        }
        if(plan.getPlanId()==0){
            throw new DaoException("Plan not Found");
        }
        return plan;
    }
//    @RequestMapping(path = "user/plans", method = RequestMethod.GET)
//    public List<DailyPlan> getUserPlans(Principal principal){
//        List<DailyPlan> planList = new ArrayList<>();
//        try{
//            int userId = userDao.getUserByUsername(principal.getName()).getId();
//            planList = dailyPlanDao.getAllUserPlans(userId);
//        } catch (NullPointerException e){
//            throw new DaoException("Unauthorized. Please log in.");
//        }
//        if(planList.isEmpty()){
//            throw new DaoException("No plans found.");
//        }
//        return planList;
//    }

    @RequestMapping(path = "user/next-week", method = RequestMethod.GET)
    public List<DailyPlan> getNextWeeksPlans(Principal principal){
        List<DailyPlan> planList = new ArrayList<>();
        try{
            int userId = userDao.getUserByUsername(principal.getName()).getId();
            planList = dailyPlanDao.getNextWeekPlans(userId);
        } catch (NullPointerException e){
            throw new DaoException("Unauthorized. Please log in.");
        }
        if(planList.isEmpty()){
            throw new DaoException("No plans found.");
        }
        return planList;
    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "user/plans/edit/{id}", method = RequestMethod.GET)
//    public DailyPlan editPlan(Principal principal, @Valid @RequestBody DailyPlanDto dailyPlanDto, @PathVariable int id){
//        try{
//            int userId = userDao.getUserByUsername(principal.getName()).getId();
//            return dailyPlanDao.updateDailyPlan(id,userId,dailyPlanDto);
//        } catch (NullPointerException e){
//            throw new DaoException("Unauthorized. Please log in.");
//        }
//    }
}

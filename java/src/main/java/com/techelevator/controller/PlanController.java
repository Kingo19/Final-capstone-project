package com.techelevator.controller;

import com.techelevator.dao.DailyPlanDao;
import com.techelevator.dao.JdbcDailyPlanDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.DailyPlan;
import com.techelevator.model.DailyPlanDto;
import com.techelevator.model.Meal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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
    @RequestMapping(path = "user/plans/add", method = RequestMethod.GET)
    public DailyPlan addDailyPlan(Principal principal, @Valid @RequestBody DailyPlanDto dailyPlanDto){
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        return dailyPlanDao.createDailyPlan(dailyPlanDto, userId);
    }
    @RequestMapping(path = "user/plans/{date}", method = RequestMethod.GET)
    public DailyPlan getPlanById(Principal principal, @PathVariable String date){
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        return dailyPlanDao.getPlanByDate(date, userId);
    }
}
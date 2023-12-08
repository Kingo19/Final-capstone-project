package com.techelevator.model;

import java.time.LocalDate;

public class PlanDto {
    private int userId;
    private LocalDate planDate;
    public PlanDto(int userId, LocalDate planDate) {
        this.userId = userId;
        this.planDate = planDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDate planDate) {
        this.planDate = planDate;
    }
}

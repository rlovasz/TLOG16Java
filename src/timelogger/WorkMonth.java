package timelogger;


import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author precognox
 */
public class WorkMonth {
    private List<WorkDay> days = new ArrayList<>();
    private long sumPerMonth;
    private long extraMinPerMonth;

    public void setDays(List<WorkDay> days) {
        this.days = days;
    }
    
    public List<WorkDay> getDays() {
        return days;
    }

    public long getSumPerMonth() {
        sumPerMonth=0;
        for(WorkDay wd: days)
        {
        sumPerMonth += wd.getSumPerDay();
        }
        return sumPerMonth;
    }

    public long getExtraMinPerMonth() {
        long requiredMinPerMonth=0;
        for(WorkDay wd: days)
        {
        requiredMinPerMonth += wd.getRequiredMinPerDay();
        }
        return getSumPerMonth()-requiredMinPerMonth;
    }
    
    public void addWorkDay(WorkDay wd) {
        addWorkDay(wd, false);
    }
    
    public void addWorkDay(WorkDay wd, boolean isWeekendEnabled)
    {
        if(wd.isWeekday() || isWeekendEnabled){
            days.add(wd);
        }
    }
    
    
}

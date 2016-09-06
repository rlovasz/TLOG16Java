package timelogger;

import java.util.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * With the instantiation of this class we can create work months.
 * We can ask for the days in this month and we can change it.
 * We can ask for the sum of the working minutes in this work month, and the extra minutes.
 * @author precognox
 */ 
@NoArgsConstructor
public class WorkMonth {

    @Setter
    @Getter
    private List<WorkDay> days = new ArrayList<>();
    private long sumPerMonth;
    private long extraMinPerMonth;
    
    /**
     * This method calculates all the minutes 
     * in this month while the employee worked
     * @return with a positive value of worked minutes
     */
    public long getSumPerMonth() {
        sumPerMonth = 0;
        for (WorkDay wd : days) {
            sumPerMonth += wd.getSumPerDay();
        }
        return sumPerMonth;
    }

    /**
     * This method calculates all the extra worked 
     * minutes in this month
     * @return with the signed value of extra minutes.
     * If it is positive the employee worked more,
     * if it is negative the employee worked less, then the required.
     */
    public long getExtraMinPerMonth() {
        long requiredMinPerMonth = 0;
        for (WorkDay wd : days) {
            requiredMinPerMonth += wd.getRequiredMinPerDay();
        }
        return getSumPerMonth() - requiredMinPerMonth;
    }

    /**
     * This method is an overloaded method of addWorkDay(WorkDay,boolean)
     * with the default false value: addWorkDay(WorkDay,false)
     * @param workDay This is a WorkDay parameter, which will be added.
     */
    public void addWorkDay(WorkDay workDay) {
        addWorkDay(workDay, false);
    }

    /**
     * This method adds a work day to this month,
     * if the work day is a weekday. But if it is
     * on weekend we have to enable to work on weekend.
     * @param workDay This is a WorkDay parameter, which will be added.
     * @param isWeekendEnabled This is a boolean parameter, 
     * if it is false, we cannot work on weekend, but if it is true,
     * we can add a day of weekend to this month.
     */
    public void addWorkDay(WorkDay workDay, boolean isWeekendEnabled) {
        if (workDay.isWeekday() || isWeekendEnabled) {
            days.add(workDay);
        }
    }

}

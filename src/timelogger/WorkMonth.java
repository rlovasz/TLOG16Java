package timelogger;

import java.util.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * With the instantiation of this class we can create work months. We can ask
 * for the days in this month and we can change it. We can ask for the sum of
 * the working minutes in this work month, and the extra minutes.
 *
 * @author precognox
 */
@NoArgsConstructor
public class WorkMonth {

    @Setter
    @Getter
    private List<WorkDay> days = new ArrayList<>();
    private long sumPerMonth = 0;
    private long requiredMinPerMonth = 0;

    /**
     * This method calculates all the minutes in this month while the employee
     * worked
     *
     * @return with a positive value of worked minutes
     * @throws timelogger.NotExpectedTimeOrderException
     * @throws timelogger.EmptyTimeFieldException
     */
    public long getSumPerMonth() throws NotExpectedTimeOrderException, EmptyTimeFieldException {
        if (sumPerMonth == 0) {
            for (WorkDay workDay : days) {
                sumPerMonth += workDay.getSumPerDay();
            }
        }
        return sumPerMonth;
    }

    /**
     * This method calculates all the extra worked minutes in this month
     *
     * @return with the signed value of extra minutes. If it is positive the
     * employee worked more, if it is negative the employee worked less, then
     * the required.
     * @throws timelogger.NotExpectedTimeOrderException
     * @throws timelogger.EmptyTimeFieldException
     */
    public long getExtraMinPerMonth() throws NotExpectedTimeOrderException, EmptyTimeFieldException {
        if (requiredMinPerMonth == 0) {
            requiredMinPerMonth = getRequiredMinPerMonth();
        }
        return getSumPerMonth() - requiredMinPerMonth;
    }

    /**
     * This method calculates how many minutes should the employee work this
     * month.
     *
     * @return with the integer value of minutes.
     */
    public long getRequiredMinPerMonth() {
        days.stream().forEach((wd) -> {
            requiredMinPerMonth += wd.getRequiredMinPerDay();
        });
        return requiredMinPerMonth;
    }

    /**
     * This method is an overloaded method of addWorkDay(WorkDay,boolean) with
     * the default false value: addWorkDay(WorkDay,false)
     *
     * @param workDay This is a WorkDay parameter, which will be added.
     * @throws timelogger.WeekendIsNotEnabledException
     * @throws timelogger.NotNewDateException
     */
    public void addWorkDay(WorkDay workDay) throws WeekendIsNotEnabledException, NotNewDateException {
        addWorkDay(workDay, false);
    }

    /**
     * This method adds a work day to this month, if the work day is a weekday.
     * But if it is on weekend we have to enable to work on weekend.
     *
     * @param workDay This is a WorkDay parameter, which will be added.
     * @param isWeekendEnabled This is a boolean parameter, if it is false, we
     * cannot work on weekend, but if it is true, we can add a day of weekend to
     * this month.
     * @throws timelogger.WeekendIsNotEnabledException, if we try to add a
     * weekend and it is enabled
     * @throws timelogger.NotNewDateException, the day is already exists, what
     * we are trying to add
     */
    public void addWorkDay(WorkDay workDay, boolean isWeekendEnabled) throws WeekendIsNotEnabledException, NotNewDateException {
        if ((workDay.isWeekday() || isWeekendEnabled) && isNewDate(workDay)) {
            days.add(workDay);
            sumPerMonth = 0;
            requiredMinPerMonth = 0;
        } else if (!isNewDate(workDay)) {
            throw new NotNewDateException("You have already added this day. You should choose an other day!");
        } else {
            throw new WeekendIsNotEnabledException("You cannot add this day, because it is on weekend and it is not enabled.");
        }
    }

    /**
     * This method decides if the date of workDay already exist in the list of
     * days
     *
     * @param workDay the day we check
     * @return true, if it is a new date, false if it isn't new.
     */
    public boolean isNewDate(WorkDay workDay) {
        for (WorkDay wd : days) {
            if (wd.getActualDay().equals(workDay.getActualDay())) {
                return false;
            }
        }
        return true;
    }

}

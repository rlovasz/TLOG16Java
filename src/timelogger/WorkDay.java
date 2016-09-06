package timelogger;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * With the instantiation of this class we can create work days.
 * We can set the date of the work day, 
 * and the required minutes we should work today. 
 * We can check if the work day is weekday, and we can add tasks for the day.
 * We can ask for the sum of the working minutes on this work day, and the extra minutes.
 * @author precognox
 */
@Getter
public class WorkDay {

    private static final List<DayOfWeek> WEEKDAYS = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
    private static final int DEFAULT_REQUIRED_MIN_PER_DAY = 450;
    @Setter
    private List<Task> tasks = new ArrayList<>();
    @Setter
    private long requiredMinPerDay;
    @Setter
    private LocalDate actualDay;
    private long extraMinPerDay;
    private long sumPerDay;

    /**
     * This method is a constructor with a long and a LocalDate parameter.
     * @param requiredMinPerDay In this parameter you can set the minutes you should work today.
     * @param actualDay In this parameter you can set the date, it will be the actual day.
     */
    public WorkDay(long requiredMinPerDay, LocalDate actualDay) {
        this.requiredMinPerDay = requiredMinPerDay;
        this.actualDay = actualDay;
    }
    
    /**
     * This method is a constructor with a long parameter.
     * The default actual day will be today (server time).
     * @param requiredMinPerDay In this parameter you can set the minutes you should work today.
     */
    public WorkDay(long requiredMinPerDay) {
        this(requiredMinPerDay, LocalDate.now());
    }

    /**
     * This method is a constructor with a LocalDate parameter.
     * The default required minutes will be 450 min=7.5 h
     * @param actualDay In this parameter you can set the date, it will be the actual day.
     */
    public WorkDay(LocalDate actualDay) {
        this(DEFAULT_REQUIRED_MIN_PER_DAY, actualDay);
    }

    /**
     * This method is a constructor without any parameters.
     * The default actual day will be today (server time).
     * The default required minutes will be 450 min=7.5 h
     */
    public WorkDay() {
        this(DEFAULT_REQUIRED_MIN_PER_DAY, LocalDate.now());
    }

    /**
     * This method calculates the difference between
     * the minutes while the employee worked and
     * the minutes while the employee should have worked
     * @return with the signed value of the extra minutes on this work day.
     * If it is positive the employee worked more,
     * if it is negative the employee worked less, then the required.
     */
    public long getExtraMinPerDay() {
        return getSumPerDay() - requiredMinPerDay;
    }

    /**
     * This methods calculates the sum of the minutes 
     * of the tasks of this work day.
     * @return with the minutes while the employee worked on this work day
     */
    public long getSumPerDay() {
        sumPerDay = 0;
        for (Task t : tasks) {
            sumPerDay += t.getMinPerTask();
        }
        return sumPerDay;
    }

    /**
     * This method adds a new task to the List named tasks, 
     * after it checks if the minutes of the task are the 
     * multiple of a quarter hour
     * @param task It is a Task type parameter, which will be added
     */
    public void addTask(Task task) {
        if(task.isMultipleQuarterHour())
        {
        tasks.add(task);
        }
    }

    /**
     * This method decides if this work day is a weekday or not
     * @return true if it is a weekday, false if it is on weekend
     */
    public boolean isWeekday() {
        return WEEKDAYS.contains(DayOfWeek.from(actualDay));
    }
}

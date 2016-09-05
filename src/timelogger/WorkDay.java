/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timelogger;

import java.time.*;
import java.util.*;

/**
 *
 * @author precognox
 */
public class WorkDay {
    private static final List<DayOfWeek> WEEKDAYS = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
    private static final int DEFAULT_REQUIRED_MIN_PER_DAY=450;
    private List<Task> tasks = new ArrayList<>();
    private long requiredMinPerDay;
    private LocalDate actualDay;
    private long extraMinPerDay;
    private long sumPerDay;

    

    public WorkDay(long requiredMinPerDay, LocalDate actualDay) {
        this.requiredMinPerDay = requiredMinPerDay;
        this.actualDay = actualDay;
    }

    public WorkDay(long requiredMinPerDay) {
        this(requiredMinPerDay, LocalDate.now());
    }
    
    public WorkDay(LocalDate actualDay) {
        this(DEFAULT_REQUIRED_MIN_PER_DAY,actualDay);
    }

    public WorkDay() {
        this(DEFAULT_REQUIRED_MIN_PER_DAY,LocalDate.now());
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setRequiredMinPerDay(long requiredMinPerDay) {
        this.requiredMinPerDay = requiredMinPerDay;
    }

    public void setActualDay(LocalDate actualDay) {
        this.actualDay = actualDay;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public long getRequiredMinPerDay() {
        return requiredMinPerDay;
    }

    public long getExtraMinPerDay() {
        return getSumPerDay()-requiredMinPerDay;
    }

    public long getSumPerDay() {   
        sumPerDay=0;
        for(Task t: tasks)
        {
        sumPerDay+=t.getMinPerTask();
        }
        return sumPerDay;
    }
    
    public void addTask(Task t)
    {
    tasks.add(t);
    }
    
    public boolean isWeekday()
    {
    return WEEKDAYS.contains(DayOfWeek.from(actualDay));
    }
}

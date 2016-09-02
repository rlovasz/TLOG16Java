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
    private List<Task> tasks;
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
        this(450,actualDay);
    }

    public WorkDay() {
        this(450,LocalDate.now());
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

    public LocalDate getActualDay() {
        return actualDay;
    }

    public long getExtraMinPerDay() {
        return extraMinPerDay;
    }

    public long getSumPerDay() {
        return sumPerDay;
    }
    
    
}

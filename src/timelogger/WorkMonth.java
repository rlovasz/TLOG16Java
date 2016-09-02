package timelogger;


import java.util.*;
import javax.swing.JOptionPane;

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
    public void addWorkDay(WorkDay wd)
    {
        if(wd.isWeekday()){days.add(wd);}
        else
        {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure? Do you work on weekend?", "Warning message", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){days.add(wd);}
        }
    }
}

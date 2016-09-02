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
    private List<WorkDay> days;
    private long sumPerMonth;
    private long extraMinPerMonth;

    public List<WorkDay> getDays() {
        return days;
    }

    public long getSumPerMonth() {
        return sumPerMonth;
    }

    public long getExtraMinPerMonth() {
        return extraMinPerMonth;
    }
}

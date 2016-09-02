/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timelogger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

/**
 *
 * @author precognox
 */
public class TimeLogger {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       WorkDay wd = new WorkDay(LocalDate.of(2016, Month.AUGUST, 28));
       Task t = new Task("1485","This is a comment",LocalTime.of(7, 30), LocalTime.of(8, 45));
       WorkMonth aug = new WorkMonth();
       wd.addTask(t);
       aug.addWorkDay(wd);
       System.out.print(aug.getSumPerMonth());
    }
    
}

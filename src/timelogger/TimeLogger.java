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
     * @throws timelogger.NotMultipleQuarterHourException
     * @throws timelogger.NotExpectedTimeOrderException
     * @throws timelogger.EmptyTimeFieldException
     * @throws timelogger.WeekendIsNotEnabledException
     */
    public static void main(String[] args) throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, WeekendIsNotEnabledException, NoTaskIdException, NotSeparatedTaskTimesException, NotNewDateException {
        WorkDay wd = new WorkDay(LocalDate.of(2016, Month.AUGUST, 28));
        Task t = new Task("1485", "This is a comment", LocalTime.of(7, 30), LocalTime.of(8, 45));
        WorkMonth aug = new WorkMonth();
        wd.addTask(t);
        aug.addWorkDay(wd);
        System.out.print(aug.getSumPerMonth());
    }

}

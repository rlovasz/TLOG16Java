package timelogger;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WorkDayTest {

    public WorkDayTest() {
    }
    Task t1;
    Task t2;
    Task t3;
    Task t4;
    Task t5;
    WorkDay wd1;
    WorkDay wd2;
    WorkDay wd3;
    WorkDay wd4;
    WorkDay wd5;
    WorkDay wd6;
    WorkDay wd7;

    @Before
    public void setUp() throws NoTaskIdException, InvalidTaskIdException, NegativeMinutesOfWorkException, FutureWorkException {
        wd1 = new WorkDay(420);
        wd2 = new WorkDay();
        wd3 = new WorkDay(420, 2016, 9, 1);
        wd4 = new WorkDay(2016, 9, 1);
        wd5 = new WorkDay(75, 2016, 9, 1);
        wd6 = new WorkDay(60);
        wd7 = new WorkDay(2016, 8, 28);
        t1 = new Task("1856", "This is a comment", 7, 30, 8, 45);
        t2 = new Task("1486", "This is a comment", 8, 45, 9, 45);
        t3 = new Task("1234", "", 7, 10, 8, 20);
        t4 = new Task("1486", "This is a comment", 8, 30, 9, 45);
        t5 = new Task("1486", "This is a comment", 7, 30, 8, 45);
    }

    @Test
    public void testGetExtraMinPerDayZero() throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        wd5.addTask(t1);
        long expResult = 0;
        long result = wd5.getExtraMinPerDay();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetExtraMinPerDayNegative() throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        wd4.addTask(t1);
        long expResult = -375;
        long result = wd4.getExtraMinPerDay();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetExtraMinPerDayPositive() throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        wd6.addTask(t1);
        long expResult = 15;
        long result = wd6.getExtraMinPerDay();
        assertEquals(expResult, result);
    }

    @Test()
    public void testGetExtraMinPerDayNoTask() throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException {
        long expResult = (-1) * wd6.getRequiredMinPerDay();
        long result = wd6.getExtraMinPerDay();
        assertEquals(expResult, result);
    }

    @Test(expected = NegativeMinutesOfWorkException.class)
    public void testSetRequiredMinPerDayNegative() throws NegativeMinutesOfWorkException {
        wd1.setRequiredMinPerDay(-15);
    }

    @Test(expected = NegativeMinutesOfWorkException.class)
    public void testWorkDayNegativeRequiredMin() throws NegativeMinutesOfWorkException, FutureWorkException {
        WorkDay wd = new WorkDay(-100);
    }

    @Test(expected = FutureWorkException.class)
    public void testSetActualDayFuture() throws FutureWorkException {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        wd1.setActualDay(tomorrow.getYear(), tomorrow.getMonthValue(), tomorrow.getDayOfMonth());
    }

    @Test(expected = FutureWorkException.class)
    public void testWorkDayFuture() throws NegativeMinutesOfWorkException, FutureWorkException {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        WorkDay wd = new WorkDay(tomorrow.getYear(), tomorrow.getMonthValue(), tomorrow.getDayOfMonth());
    }

    @Test
    public void testGetSumPerDayNormal() throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        wd6.addTask(t1);
        wd6.addTask(t2);
        long expResult = 135;
        long result = wd6.getSumPerDay();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSumPerDayNoTask() throws NotExpectedTimeOrderException, EmptyTimeFieldException {
        long expResult = 0;
        long result = wd1.getSumPerDay();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddTaskNormal() throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        wd1.addTask(t1);
        wd1.getSumPerDay();
        assertEquals(t1.getMinPerTask(), wd1.getSumPerDay());
    }

    @Test(expected = NotMultipleQuarterHourException.class)
    public void testAddTaskNotMultipleQuarterHour() throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        wd1.addTask(t3);
    }

    @Test(expected = NotSeparatedTaskTimesException.class)
    public void testAddTaskNotSeparatedTimes() throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        wd1.addTask(t1);
        wd1.addTask(t5);
    }

    @Test
    public void testIsSeparatedTimeTrue() throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        wd1.addTask(t1);
        boolean expResult = true;
        boolean result = wd1.isSeparatedTime(t2);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsSeparatedTimeFalseT1() throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        wd1.addTask(t1);
        boolean expResult = false;
        boolean result = wd1.isSeparatedTime(t4);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsSeparatedTimeFalseT2() throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        wd1.addTask(t4);
        boolean expResult = false;
        boolean result = wd1.isSeparatedTime(t1);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsSeparatedTimeFalseT3() throws NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        wd1.addTask(t5);
        boolean expResult = false;
        boolean result = wd1.isSeparatedTime(t1);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsWeekdayTrue() {
        boolean expResult = true;
        boolean result = wd1.isWeekday();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsWeekdayFalse() {
        boolean expResult = false;
        boolean result = wd7.isWeekday();
        assertEquals(expResult, result);
    }

}

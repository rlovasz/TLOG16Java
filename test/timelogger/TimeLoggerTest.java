package timelogger;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimeLoggerTest {

    public TimeLoggerTest() {
    }
    Task t1;
    WorkDay wd1;
    WorkDay wd2;
    WorkDay wd3;
    WorkDay wd4;
    WorkDay wd5;
    WorkDay wd6;
    WorkDay wd7;
    WorkDay wd8;
    WorkMonth april;
    WorkMonth april2;
    WorkMonth august;
    WorkMonth september;
    WorkMonth august15;
    TimeLogger tl1;

    @Before
    public void setUp() throws InvalidTaskIdException, NoTaskIdException, NegativeMinutesOfWorkException, FutureWorkException {
        t1 = new Task("4654", "", 7, 30, 10, 30);
        wd1 = new WorkDay(2016, 9, 8);
        wd2 = new WorkDay(2016, 9, 7);
        wd3 = new WorkDay(2016, 8, 30);
        wd4 = new WorkDay(2016, 8, 31);
        wd5 = new WorkDay(2016, 4, 14);
        wd6 = new WorkDay(2016, 4, 13);
        wd7 = new WorkDay(2015, 8, 12);
        wd8 = new WorkDay(2015, 8, 13);
        april = new WorkMonth(2016, 4);
        april2 = new WorkMonth(2016, 4);
        august = new WorkMonth(2016, 8);
        september = new WorkMonth(2016, 9);
        august15 = new WorkMonth(2015, 8);
        tl1 = new TimeLogger();
    }

    @Test
    public void testAddMonthNormal() throws Exception {
        wd5.addTask(t1);
        april.addWorkDay(wd5);
        tl1.addMonth(april);
        assertEquals(tl1.getMonths().get(0).getSumPerMonth(), t1.getMinPerTask());

    }

    @Test(expected = NotNewMonthException.class)
    public void testAddMonthNotNewMonth() throws NotNewMonthException {
        tl1.addMonth(april);
        tl1.addMonth(april2);
    }

    @Test
    public void testIsNewMonthTrue() throws NotNewMonthException {
        boolean expResult = true;
        tl1.addMonth(april);
        boolean result = tl1.isNewMonth(september);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsNewMonthFalse() throws NotNewMonthException {
        boolean expResult = false;
        tl1.addMonth(april);
        boolean result = tl1.isNewMonth(april2);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testMinNormal() throws WeekendNotEnabledException, NotNewDateException, NotTheSameMonthException, NoMonthsException, NotNewMonthException {
        september.addWorkDay(wd1);
        september.addWorkDay(wd2);
        august.addWorkDay(wd3);
        august.addWorkDay(wd4);
        april.addWorkDay(wd5);
        april.addWorkDay(wd6);
        tl1.addMonth(april);
        tl1.addMonth(september);
        tl1.addMonth(august);
        assertEquals(april, tl1.Min());
    }

    @Test(expected = NoMonthsException.class)
    public void testMinNoMonths() throws NoMonthsException {
        tl1.Min();

    }

}

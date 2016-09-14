package timelogger;

import java.time.LocalTime;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Textmenu {

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input, year, month, day, hour, min;
        String answer;
        TimeLogger months = new TimeLogger();
        do {

            System.out.println("0. Exit");
            System.out.println("1. Add new month");
            System.out.println("2. List months");
            System.out.println("3. Add day to a specific month");
            System.out.println("4. List days of a specific month");
            System.out.println("5. Add task to a specific day");
            System.out.println("6. List tasks of a specific day");
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    System.out.println("year:");
                    year = scanner.nextInt();
                    System.out.println("month:");
                    month = scanner.nextInt();
                    try {
                        months.addMonth(new WorkMonth(year, month));
                    } catch (NotNewMonthException ex) {
                        Logger.getLogger(Textmenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 2:
                    printListOfMonths(months);
                    break;
                case 3:
                    printListOfMonths(months);
                    System.out.println("Choose a month!");
                    month = scanner.nextInt();
                    System.out.println("day:");
                    day = scanner.nextInt();
                    System.out.println("Do you have 7.5 required working hours this day? (Y/N)");
                    answer = scanner.next();
                    double requiredHour;
                    if ("N".equals(answer)) {
                        System.out.println("Please type in how many required working hours do you have this day!");
                        requiredHour = scanner.nextFloat();
                    } else {
                        requiredHour = 7.5;
                    }
                    try {
                        WorkMonth actualMonth = months.getMonths().get(month);
                        actualMonth.addWorkDay(new WorkDay((long) (requiredHour * 60), actualMonth.getDate().getYear(), actualMonth.getDate().getMonthValue(), day));
                    } catch (NegativeMinutesOfWorkException | FutureWorkException | WeekendNotEnabledException | NotNewDateException | NotTheSameMonthException ex) {
                        Logger.getLogger(TimeLogger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 4:
                    printListOfMonths(months);
                    System.out.println("Choose a month!");
                    month = scanner.nextInt();
                    printListOfDays(month, months);
                    break;
                case 5:
                    printListOfMonths(months);
                    System.out.println("Choose a month!");
                    month = scanner.nextInt();
                    printListOfDays(month, months);
                    System.out.println("Choose a day!");
                    day = scanner.nextInt();
                    WorkDay workDay = months.getMonths().get(month).getDays().get(day);
                    Task task = new Task();
                    System.out.println("Please type in a valid task id!");
                    answer = scanner.next();
                    try {
                        task.setTaskId(answer);
                    } catch (InvalidTaskIdException | NoTaskIdException ex) {
                        Logger.getLogger(TimeLogger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Please type in what did you exactly do!");
                    answer = scanner.next();
                    task.setComment(answer);
                    System.out.println("Please type in the hour part of the beginning time!");
                    hour = scanner.nextInt();
                    System.out.println("Please type in the minute part of the beginning time!");
                    min = scanner.nextInt();
                    LocalTime startTime = LocalTime.of(hour, min);
                    task.setStartTime(startTime);
                    System.out.println("Please type in the hour part of the finishing time!");
                    hour = scanner.nextInt();
                    System.out.println("Please type in the minute part of the finishing time!");
                    min = scanner.nextInt();
                    LocalTime endTime = LocalTime.of(hour, min);
                    task.setEndTime(endTime);
                    try {
                        workDay.addTask(task);
                    } catch (NotMultipleQuarterHourException | NotExpectedTimeOrderException | EmptyTimeFieldException | NotSeparatedTaskTimesException ex) {
                        Logger.getLogger(TimeLogger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 6:
                    printListOfMonths(months);
                    System.out.println("Choose a month!");
                    month = scanner.nextInt();
                    printListOfDays(month, months);
                    System.out.println("Choose a day!");
                    day = scanner.nextInt();

                    try {
                        printListOfTasks(month, day, months);
                    } catch (NoTaskIdException ex) {
                        Logger.getLogger(TimeLogger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                default:
                    break;

            }
        } while (input != 0);
    }

    private static void printListOfTasks(int month, int day, TimeLogger months) throws NoTaskIdException {
        for (int i = 0; i < months.getMonths().get(month).getDays().get(day).getTasks().size(); i++) {
            System.out.println((i + 1) + ". " + "TaskId: " + months.getMonths().get(month).getDays().get(day).getTasks().get(i).getTaskId() +
                    " Comment: " + months.getMonths().get(month).getDays().get(day).getTasks().get(i).getComment() + 
                    " Start: " + months.getMonths().get(month).getDays().get(day).getTasks().get(i).getStartTime().toString() + 
                    " End: " + months.getMonths().get(month).getDays().get(day).getTasks().get(i).getEndTime().toString());
        }
    }

    private static void printListOfDays(int choosen, TimeLogger months) {
        for (int i = 0; i < months.getMonths().get(choosen).getDays().size(); i++) {
            System.out.println(i + ". " + months.getMonths().get(choosen).getDays().get(i).getActualDay().toString());
        }
    }

    private static void printListOfMonths(TimeLogger months) {
        for (int i = 0; i < months.getMonths().size(); i++) {
            System.out.println(i + ". " + months.getMonths().get(i).getDate().toString());
        }
    }
}

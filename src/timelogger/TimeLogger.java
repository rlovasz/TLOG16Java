package timelogger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TimeLogger {

    private static List<WorkMonth> months = new ArrayList();

    public static List<WorkMonth> getMonths() {
        return months;
    }

    public static void setMonths(List<WorkMonth> months) {
        TimeLogger.months = months;
    }

    /**
     * This method adds a new month to the TimeLogger, if it is in the same
     * year, as the earlier ones
     *
     * @param workMonth the month to add
     * @throws NotSameYearException, if it is not in the same year like the
     * earliers
     */
    public void addMonth(WorkMonth workMonth) throws NotSameYearException {
        if (isSameYear(workMonth)) {
            months.add(workMonth);
        } else {
            throw new NotSameYearException("You should add new month from the same year.");
        }
    }

    /**
     * This method calculates the first month of the TimeLogger
     *
     * @return with the first month
     * @throws NoMonthsException, if the months list is empty
     */
    public WorkMonth Min() throws NoMonthsException {
        if (!months.isEmpty()) {
            WorkMonth min = months.get(0);
            for (WorkMonth workMonth : months) {
                if (min.getDays().get(0).getActualDay().getMonthValue() > workMonth.getDays().get(0).getActualDay().getMonthValue()) {
                    min = workMonth;
                }
            }
            return min;
        } else {
            throw new NoMonthsException("There are no months yet.");
        }
    }

    /**
     * This method decides if the workMonth is in the same year as the months of
     * the TimeLogger
     *
     * @param workMonth the parameter about to decide
     * @return with true, if it is in the same year, false, if it is not in the
     * same year
     */
    public boolean isSameYear(WorkMonth workMonth) {
        for (WorkMonth wm : months) {
            if ((workMonth.getDays().get(0).getActualDay().getYear() != wm.getDays().get(0).getActualDay().getYear()) && !months.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input, year, month, day, hour, min;
        String answer;
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
                    months.add(new WorkMonth(year, month));
                    break;
                case 2:
                    printListOfMonths();
                    break;
                case 3:
                    printListOfMonths();
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
                        WorkMonth actualMonth = months.get(month);
                        actualMonth.addWorkDay(new WorkDay((long) (requiredHour * 60), actualMonth.getDate().getYear(), actualMonth.getDate().getMonthValue(), day));
                    } catch (NegativeMinutesOfWorkException | FutureWorkException | WeekendNotEnabledException | NotNewDateException | NotTheSameMonthException ex) {
                        Logger.getLogger(TimeLogger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 4:
                    printListOfMonths();
                    System.out.println("Choose a month!");
                    month = scanner.nextInt();
                    printListOfDays(month);
                    break;
                case 5:
                    printListOfMonths();
                    System.out.println("Choose a month!");
                    month = scanner.nextInt();
                    printListOfDays(month);
                    System.out.println("Choose a day!");
                    day = scanner.nextInt();
                    WorkDay workDay = months.get(month).getDays().get(day);
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
                    printListOfMonths();
                    System.out.println("Choose a month!");
                    month = scanner.nextInt();
                    printListOfDays(month);
                    System.out.println("Choose a day!");
                    day = scanner.nextInt();

                    try {
                        printListOfTasks(month, day);
                    } catch (NoTaskIdException ex) {
                        Logger.getLogger(TimeLogger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                default:
                    break;

            }
        } while (input != 0);
    }

    private static void printListOfTasks(int month, int day) throws NoTaskIdException {
        for (int i = 0; i < months.get(month).getDays().get(day).getTasks().size(); i++) {
            System.out.println((i + 1) + ". " + "TaskId: " + months.get(month).getDays().get(day).getTasks().get(i).getTaskId() + " Comment: " + months.get(month).getDays().get(day).getTasks().get(i).getComment() + " Start: " + months.get(month).getDays().get(day).getTasks().get(i).getStartTime().toString() + " End: " + months.get(month).getDays().get(day).getTasks().get(i).getEndTime().toString());
        }
    }

    private static void printListOfDays(int choosen) {
        for (int i = 0; i < months.get(choosen).getDays().size(); i++) {
            System.out.println(i + ". " + months.get(choosen).getDays().get(i).getActualDay().toString());
        }
    }

    private static void printListOfMonths() {
        for (int i = 0; i < months.size(); i++) {
            System.out.println(i + ". " + months.get(i).getDate().toString());
        }
    }
}

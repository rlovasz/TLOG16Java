package timelogger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TimeLogger {

    @Getter
    @Setter
    private List<WorkMonth> months = new ArrayList();

    /**
     * This method adds a new month to the TimeLogger, if it is in the same
     * year, as the earlier ones
     *
     * @param workMonth the month to add
     * @throws timelogger.NotNewMonthException, if the workMonth is already
     * exists
     */
    public void addMonth(WorkMonth workMonth) throws NotNewMonthException {
        if (isNewMonth(workMonth)) {
            months.add(workMonth);
        } else {
            throw new NotNewMonthException("This month is already exists.");
        }

    }
    

    /**
     * This method decides if the work month is in the list of the months
     * already
     *
     * @param workMonth, the parameter about to decide
     * @return true, if it is new, false, if it is already exists
     */
    public boolean isNewMonth(WorkMonth workMonth) {
        for (WorkMonth wm : months) {
            if ((wm.getDate().equals(workMonth.getDate())) && !months.isEmpty()) {
                return false;
            }
        }
        return true;
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
                if (min.getDate().getYear() > workMonth.getDate().getYear()
                        || (min.getDate().getYear() == workMonth.getDate().getYear()
                        && min.getDate().getMonthValue() > workMonth.getDate().getMonthValue())) {
                    min = workMonth;
                }
            }
            return min;
        } else {
            throw new NoMonthsException("There are no months yet.");
        }
    }

    
}

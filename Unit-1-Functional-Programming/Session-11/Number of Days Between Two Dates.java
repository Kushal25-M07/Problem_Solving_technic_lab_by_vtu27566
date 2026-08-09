import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Solution {
    // Days in each month (index 1 = Jan, index 12 = Dec)
    private final int[] MONTH_DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int daysBetweenDates(String date1, String date2) {
        // Return the absolute difference in days from a fixed starting point (1971)
        return Math.abs(daysFrom1971(date1) - daysFrom1971(date2));
    }

    private int daysFrom1971(String date) {
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        int totalDays = 0;

        // 1. Add days for all the fully completed years since 1971
        for (int i = 1971; i < year; i++) {
            totalDays += isLeapYear(i) ? 366 : 365;
        }

        // 2. Add days for all the fully completed months in the current year
        for (int i = 1; i < month; i++) {
            totalDays += MONTH_DAYS[i];
            // Add an extra day if the current year is a leap year and we are past February
            if (i == 2 && isLeapYear(year)) {
                totalDays += 1;
            }
        }

        // 3. Add the days of the current month
        totalDays += day;

        return totalDays;
    }

    private boolean isLeapYear(int year) {
        // A leap year is divisible by 4, but not by 100, unless it is also divisible by 400
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}

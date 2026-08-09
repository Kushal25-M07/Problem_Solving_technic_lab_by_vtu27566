class Solution {
    public int dayOfYear(String date) {

        String[] parts = date.split("-");

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        int[] monthDays = {31,28,31,30,31,30,31,31,30,31,30,31};

        int totalDays = day;

        // Add days of previous months
        for (int i = 0; i < month - 1; i++) {
            totalDays += monthDays[i];
        }

        // Add one day for leap year if date is after February
        if (isLeapYear(year) && month > 2) {
            totalDays++;
        }

        return totalDays;
    }

    public boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }
}

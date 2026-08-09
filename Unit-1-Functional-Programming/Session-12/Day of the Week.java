class Solution {
    public String dayOfTheWeek(int day, int month, int year) {

        String[] week = {"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int totalDays = 0;

        for(int i = 1971; i < year; i++){
            if ((i % 4 == 0 && i % 100 != 0) || (i % 400 == 0)) {
                totalDays += 366;
            } else {
                totalDays += 365;
            }
        }

        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            daysOfMonth[1] = 29;
        }

        for (int i = 0; i < month - 1; i++) {
            totalDays += daysOfMonth[i];
        }

        totalDays += (day - 1);

        int dayIndex = totalDays % 7;
        return week[dayIndex];
    }
}

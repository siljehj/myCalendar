package calendarproject.classes;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity implements Comparable<Activity> {

    private String activityName;
    private String activityCategory;
    private int date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int column;
    private int row;

    public Activity(String name, String category, String date, String startTime, String endTime) {
        validateActivity(name, category, date, startTime, endTime);
        this.activityName = name;
        this.activityCategory = category;
        this.date = Integer.valueOf(date);
        LocalTime start = LocalTime.of(Integer.valueOf(startTime.substring(0, 2)),
                Integer.valueOf(startTime.substring(3)));
        LocalTime end = LocalTime.of(Integer.valueOf(endTime.substring(0, 2)),
                Integer.valueOf(endTime.substring(3)),
                00);
        this.startTime = start;
        this.endTime = end;
        findIndex();
    }

    public String getName() {
        return activityName;
    }

    public String getCategory() {
        return activityCategory;
    }

    public int getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    private void findIndex() {
        // finding the row and column
        if (this.getDate() < 8) {
            this.row = 0;
            this.column = this.getDate() - 1;
        } else if (this.getDate() < 15) {
            this.row = 1;
            this.column = this.getDate() - 8;
        } else if (this.getDate() < 22) {
            this.row = 2;
            this.column = this.getDate() - 15;
        } else {
            this.row = 3;
            this.column = this.getDate() - 22;
        }
    }

    public int compareTo(Activity activty1) {
        if (this.getStartTime().isBefore(activty1.getStartTime())) {
            return -1;
        } else if (this.getStartTime().isAfter(activty1.getStartTime())) {
            return 1;
        } else {
            if (this.getEndTime().isBefore(activty1.getEndTime())) {
                return -1;
            } else if (this.getEndTime().isAfter(activty1.getEndTime())) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private void validateActivity(String name, String category, String date, String startTime, String endTime) {
        List<String> categories = new ArrayList<>();
        Collections.addAll(categories, "School", "Work", "Workout", "Social", "Other");
        if (name.equals("") || category.equals("") || date.equals("") || startTime.equals("") || endTime.equals("")) {
            throw new IllegalArgumentException("One or more variables are missing.");
        } else if (!categories.contains(category)) {
            throw new IllegalArgumentException("Invalid category.");
        }
        else if (!Activity.isDigit(date)) {
            throw new IllegalArgumentException("Date must be a number.");
        } else if (!Activity.isValidDate(Integer.valueOf(date))) {
            throw new IllegalArgumentException("Date must be between 1st and 28th.");
        } else if (!Activity.isValidTime(startTime)) {
            throw new IllegalArgumentException("Invalid start time.");
        } else if (!Activity.isValidTime(endTime)) {
            throw new IllegalArgumentException("Invalid end time.");
        } else if (!Activity.isValidTimes(startTime, endTime)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
    }

    public static boolean isValidDate(int date) {
        return date >= 1 && date <= 28;
    }

    public static boolean isValidTime(String time) {
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(time);
        return m.matches();
    }

    public static boolean isValidTimes(String startTime, String endTime) {
        LocalTime start = LocalTime.of(Integer.valueOf(startTime.substring(0, 2)),
                Integer.valueOf(startTime.substring(3)));
        LocalTime end = LocalTime.of(Integer.valueOf(endTime.substring(0, 2)), Integer.valueOf(endTime.substring(3)),
                00);
        return !start.equals(end) && start.isBefore(end);
    }

    public static boolean isDigit(String date) {
        try {
            Integer.parseInt(date);
            return true;
        } catch (NumberFormatException er) {
            return false;
        }
    }

}

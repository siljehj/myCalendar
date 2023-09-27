package calendarproject.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonalCalendar {

    private String calendarName;
    private String path;
    private CalendarTile[][] myCalendar;
    private boolean deleted = false;

    public PersonalCalendar(String name) {
        validateName(name);
        myCalendar = new CalendarTile[4][7];
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 7; column++) {
                myCalendar[row][column] = new CalendarTile((7 * row) + column + 1);
            }
        }
        this.calendarName = name;
        this.path = "C:\\Users\\silje\\Documents\\prosjekt\\TDT4100-prosjekt\\src\\main\\resources\\calendarproject\\File\\" + calendarName + ".txt";
        FileHandler.makeCalendarFile(path);
        addActivitiesFromFile();
    }

    private PersonalCalendar() {
        // calendar with no name or file, just to be used for filtering
        myCalendar = new CalendarTile[4][7];
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 7; column++) {
                myCalendar[row][column] = new CalendarTile((7 * row) + column + 1);
            }
        }
    }

    public String getName() {
        checkIfDeleted();
        return calendarName;
    }

    public int getActivityCount() {
        checkIfDeleted();
        int count = 0;
        for (CalendarTile[] rowOfTiles : myCalendar) {
            for (CalendarTile tile : rowOfTiles) {
                count += tile.getActivityCount();
            }
        }
        return count;
    }

    public CalendarTile getTileFromIndex(int row, int column) {
        checkIfDeleted();
        validateIndex(row, column);
        return myCalendar[row][column];
    }

    public CalendarTile getTileFromDate(int date) {
        checkIfDeleted();
        validateDate(date);
        return myCalendar[findIndex(date)[0]][findIndex(date)[1]];
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void addNewActivity(Activity activity) {
        checkIfDeleted();
        if (this.containsActivity(activity)) {
            throw new IllegalStateException("Activity already in calendar.");
        }
        myCalendar[activity.getRow()][activity.getColumn()].addActivity(activity);
        FileHandler.writeActivityToFile(activity, this.path);
    }

    public void removeAllActivites() {
        checkIfDeleted();
        for (CalendarTile[] rowOfTiles : myCalendar) {
            for (CalendarTile tile : rowOfTiles) {
                tile.removeAllActivites();
            }
        }
        FileHandler.deleteContentFromFile(this.path);
    }

    public PersonalCalendar filterCalendar(String filter) {
        checkIfDeleted();
        validateFilter(filter);
        PersonalCalendar filteredCalendar = new PersonalCalendar();
        for (CalendarTile[] row : myCalendar) {
            for (CalendarTile tile : row) {
                for (Activity a : tile.getActivities()) {
                    if (a.getCategory().equals(filter)) {
                        filteredCalendar.addNewFilteredActivity(a);
                    }
                }
            }
        }
        return filteredCalendar;
    }

    public boolean containsActivity(Activity activity) {
        checkIfDeleted();
        for (CalendarTile[] row : myCalendar) {
            for (CalendarTile tile : row) {
                if (tile.containsActivity(activity)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteCalendar() {
        checkIfDeleted();
        FileHandler.deleteFile(calendarName + ".txt");
        this.deleted = true;
    }

    private void addActivitiesFromFile() {
        checkIfDeleted();
        for (Activity activity : FileHandler.readCalendarFromFile(this.path)) {
            myCalendar[activity.getRow()][activity.getColumn()].addActivity(activity);
        }
    }

    private void addNewFilteredActivity(Activity activity) {
        myCalendar[activity.getRow()][activity.getColumn()].addActivity(activity);
    }

    private int[] findIndex(int date) {
        int row, column;
        if (date < 8) {
            row = 0;
            column = date - 1;
        } else if (date < 15) {
            row = 1;
            column = date - 8;
        } else if (date < 22) {
            row = 2;
            column = date - 15;
        } else {
            row = 3;
            column = date - 22;
        }
        return new int[] { row, column };
    }

    private void validateIndex(int row, int column) {
        if (row < 0 || row > 3 || column < 0 || column > 6) {
            throw new IllegalArgumentException("Invalid index.");
        }
    }

    private void validateDate(int date) {
        if (date < 1 || date > 28) {
            throw new IllegalArgumentException("Invalid date.");
        }
    }

    private void validateName(String name) {
        if (!name.chars().allMatch(Character::isLetterOrDigit)) {
            throw new IllegalArgumentException("Calendar name must be all letters.");
        }
    }

    private void validateFilter(String filter) {
        List<String> categories = new ArrayList<>();
        Collections.addAll(categories, "School", "Work", "Workout", "Social", "Other");
        if (!categories.contains(filter)) {
            throw new IllegalArgumentException("Invalid filter.");
        }
    }

    private void checkIfDeleted() {
        if (this.deleted) {
            throw new IllegalStateException("This calendar does not exist.");
        }
    }

}

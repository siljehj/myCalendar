package calendarproject.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalendarTile {

    private int date;
    private List<Activity> activites = new ArrayList<>();

    public CalendarTile(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public int getActivityCount() {
        return activites.size();
    }

    public Activity getActivity(int index) {
        validateIndex(index);
        return activites.get(index);
    }

    public List<Activity> getActivities() {
        return activites;
    }

    public int getIndexOfAcitivity(Activity activity) {
        return activites.indexOf(activity);
    }

    protected void addActivity(Activity activity) {
        activites.add(activity);
        Collections.sort(activites);
    }

    protected void removeAllActivites() {
        activites.clear();
    }

    protected boolean containsActivity(Activity activity) {
        return activites.contains(activity);
    }

    private void validateIndex(int i) {
        if (i < 0 || i >= activites.size()) {
            throw new IllegalArgumentException("Invalid index.");
        }
    }

}

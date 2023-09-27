package calendarproject.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalendarFileTest {

    private PersonalCalendar c1;
    private Activity a1, a2, a3;
    private String pathToActualState = "C:\\Users\\silje\\Documents\\prosjekt\\TDT4100-prosjekt\\src\\test\\resources\\calendarproject\\File\\ActualState.txt";
    private String pathToExpectedState = "C:\\Users\\silje\\Documents\\prosjekt\\TDT4100-prosjekt\\src\\test\\resources\\calendarproject\\File\\ExpectedState.txt";

    @BeforeEach
    public void setUp() {
        c1 = new PersonalCalendar("TestCalendar1");
        a1 = new Activity("Run", "Workout", "14", "12:00", "15:00");
        a2 = new Activity("Date", "Social", "13", "14:00", "18:00");
        a3 = new Activity("Birthday party", "Social", "3", "15:00", "19:00");
    }

    @AfterEach
    public void deleteFile() {
        c1.deleteCalendar();
        FileHandler.deleteContentFromFile(pathToActualState);
    }

    @Test
    @DisplayName("Test write state to file")
    public void writeStateToFile() {
        c1.addNewActivity(a1);
        FileHandler.writeActivityToFile(a1, this.pathToActualState);
        c1.addNewActivity(a2);
        FileHandler.writeActivityToFile(a2, this.pathToActualState);
        c1.addNewActivity(a3);
        FileHandler.writeActivityToFile(a3, this.pathToActualState);

        List<String> actualLines = FileHandler.readLines(pathToActualState);
        List<String> expectedLines = FileHandler.readLines(pathToExpectedState);

        assertEquals(expectedLines.size(), actualLines.size(), "Wrong amount of lines.");

        for (int i = 0; i < actualLines.size(); i++) {
            assertEquals(expectedLines.get(i), actualLines.get(i));
        }
    }

    @Test
    @DisplayName("Test read calendar from file")
    public void readCalendarFromFile() {
        List<Activity> expectedActivites = new ArrayList<>();
        expectedActivites.add(a1);
        expectedActivites.add(a2);
        expectedActivites.add(a3);

        List<Activity> actualActivites = FileHandler.readCalendarFromFile(pathToExpectedState);

        assertEquals(expectedActivites.size(), actualActivites.size(), "Wrong amount of activities.");

        for (int i = 0; i < actualActivites.size(); i++) {
            assertEqualActivities(actualActivites.get(i), expectedActivites.get(i));
        }
    }

    private void assertEqualActivities(Activity a1, Activity a2) {
        assertEquals(a1.getName(), a2.getName());
        assertEquals(a1.getCategory(), a2.getCategory());
        assertEquals(a1.getDate(), a2.getDate());
        assertEquals(a1.getStartTime(), a2.getStartTime());
        assertEquals(a1.getEndTime(), a2.getEndTime());
        assertEquals(a1.getRow(), a2.getRow());
        assertEquals(a1.getColumn(), a2.getColumn());
    }

}

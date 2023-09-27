package calendarproject.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalendarTileTest {

    private PersonalCalendar c1;
    private Activity a1, a2, a3, a4;
    private CalendarTile t1, t2;

    @BeforeEach
    public void setUp() {
        c1 = new PersonalCalendar("TestCalendar1");
        a1 = new Activity("Cinema", "Social", "2", "17:00", "19:00");
        a2 = new Activity("Run", "Workout", "2", "10:00", "12:00");
        a3 = new Activity("Math exam", "School", "2", "09:00", "13:00");
        a4 = new Activity("Date", "Social", "10", "19:00", "21:00");
        c1.addNewActivity(a1);
        c1.addNewActivity(a2);
        c1.addNewActivity(a3);
        c1.addNewActivity(a4);
        t1 = c1.getTileFromDate(2);
        t2 = c1.getTileFromDate(10);
    }

    @AfterEach
    public void deleteFile() {
        c1.deleteCalendar();
    }

    @Test
    @DisplayName("Test activity count")
    public void testCount() {
        assertEquals(3, t1.getActivityCount(), "Wrong number of activities at date " + t1.getDate());
        assertEquals(1, t2.getActivityCount(), "Wrong number of activities at date " + t2.getDate());
    }

    @Test
    @DisplayName("Test order of activities")
    public void testOrder() {
        assertEquals(a3, t1.getActivity(0), "Wrong order of activities.");
        assertEquals(a2, t1.getActivity(1), "Wrong order of activities.");
        assertEquals(a1, t1.getActivity(2), "Wrong order of activities.");
        assertEquals(2, t1.getIndexOfAcitivity(a1), "Wrong order of activities.");
        assertEquals(1, t1.getIndexOfAcitivity(a2), "Wrong order of activities.");
        assertEquals(0, t1.getIndexOfAcitivity(a3), "Wrong order of activities.");
    }

}

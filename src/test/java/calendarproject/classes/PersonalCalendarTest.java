package calendarproject.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonalCalendarTest {

    private PersonalCalendar c1, c2;
    private Activity a1, a2, a3;

    @BeforeEach
    public void setUp() {
        c1 = new PersonalCalendar("TestCalendar1");
        c2 = new PersonalCalendar("TestCalendar2");
        a1 = new Activity("Cinema", "Social", "2", "17:00", "19:00");
        a2 = new Activity("Run", "Workout", "2", "10:00", "12:00");
        a3 = new Activity("Math exam", "School", "2", "09:00", "13:00");
        c1.addNewActivity(a1);
        c1.addNewActivity(a2);
        c1.addNewActivity(a3);
    }

    @AfterEach
    public void deleteFile() {
        if (!c1.isDeleted()) {
            c1.deleteCalendar();
        }
        if (!c2.isDeleted()) {
            c2.deleteCalendar();
        }
    }

    @Test
    @DisplayName("Test constructor")
    public void testConstructor() {
        assertEquals("TestCalendar1", c1.getName(), "Filename was wrong.");
        assertEquals(3, c1.getActivityCount(), "Activity count was wrong.");
        assertFalse(c1.isDeleted());
        assertEquals(0, c2.getActivityCount(), "Activity count was wrong.");
        assertTrue(c1.containsActivity(a1), "Calendar does not contain " + a1.getName());
        assertFalse(c2.containsActivity(a1), "Calednar does not contain " + a1.getName());
        assertThrows(IllegalArgumentException.class, () -> {
            new PersonalCalendar("Siljes Calendar");
        }, "Name with space should have thrown IllegalArugment-exception!");
        assertThrows(IllegalArgumentException.class, () -> {
            new PersonalCalendar("Silje<3");
        }, "Name with other character(s) than letters should have thrown IllegalArugment-exception!");
    }

    @Test
    @DisplayName("Test add activity.")
    public void testAddActivity() {
        Activity a4 = new Activity("Programming class", "School", "19", "12:15", "14:00");
        c1.addNewActivity(a4);
        assertTrue(c1.containsActivity(a4), a4.getName() + " was not added to calendar.");
        assertEquals(4, c1.getActivityCount(), "Activity count was wrong.");
        assertThrows(IllegalStateException.class, () -> {
            c1.addNewActivity(a4);
        }, "Trying to add activity already in calendar should have thrown IllegalState-exception!");
    }

    @Test
    @DisplayName("Test remove all activities.")
    public void testRemoveAllActivites() {
        c1.removeAllActivites();
        assertFalse(c1.containsActivity(a1), a1.getName() + " should have been removed from " + c1.getName());
        assertEquals(0, c1.getActivityCount(), "Activity count was wrong.");
    }

    @Test
    @DisplayName("Test filter calendar.")
    public void testFilter() {
        Activity a4 = new Activity("Programming class", "School", "19", "12:15", "14:00");
        c1.addNewActivity(a4);
        PersonalCalendar f1 = c1.filterCalendar("School");
        assertEquals(2, f1.getActivityCount(), "Wrong activity count.");
        assertTrue(f1.containsActivity(a4));
        assertFalse(f1.containsActivity(a1));
        assertThrows(IllegalArgumentException.class, () -> {
            c1.filterCalendar("Sport");
        }, "Trying to filter by invalid category should throw IllegalArgument-exception!");
    }

    @Test
    @DisplayName("Test delete calendar.")
    public void testDeleteCalendar() {
        c2.deleteCalendar();
        assertTrue(c2.isDeleted(), "The calendar should have been deleted.");
        assertThrows(IllegalStateException.class, () -> {
            c2.addNewActivity(a1);
        }, "Trying to add activity to deleted calendar should have thrown IllegalState-exception!");
        assertThrows(IllegalStateException.class, () -> {
            c2.removeAllActivites();
        }, "Trying to remove all activies from deleted calendar should have thrown IllegalState-exception!");
        assertThrows(IllegalStateException.class, () -> {
            c2.filterCalendar("Social");
        }, "Trying to remove all activies from deleted calendar should have thrown IllegalState-exception!");
        assertThrows(IllegalStateException.class, () -> {
            c2.deleteCalendar();
        }, "Trying to deletede already deleted calendar should have thrown IllegalState-exception!");
    }

}

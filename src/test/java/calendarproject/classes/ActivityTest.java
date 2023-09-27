package calendarproject.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ActivityTest {

    private Activity a1, a2;

    @BeforeEach
    public void setUp() {
        a1 = new Activity("Date", "Social", "13", "19:00", "21:30");
        }

    @Test
    @DisplayName("Test constructor.")
    public void testConstructor() {    
        LocalTime startTime1 = LocalTime.of(19, 00);
        LocalTime endTime1 = LocalTime.of(21, 30);
        assertEquals("Date", a1.getName(), "Name was wrong.");
        assertEquals("Social", a1.getCategory(), "Category was wrong.");
        assertEquals(13, a1.getDate(), "Date was wrong.");
        assertEquals(startTime1, a1.getStartTime(), "Start time was wrong.");
        assertEquals(endTime1, a1.getEndTime(), "End time was wrong.");
    }

    @Test
    @DisplayName("Test invalid input.")
    public void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Activity("", "Workout", "16", "14:00", "16:00");
        }, "Missing name should have thrown IllegalArugment-exception!");
        assertThrows(IllegalArgumentException.class, () -> {
            new Activity("Dance class", "Dancing", "16", "14:00", "16:00");
        }, "Invalid category should have thrown IllegalArugment-exception!");
        assertThrows(IllegalArgumentException.class, () -> {
            new Activity("Dance class", "Workout", "Lol", "14:00", "16:00");
        }, "Non-integer date should have thrown IllegalArugment-exception!");
        assertThrows(IllegalArgumentException.class, () -> {
            new Activity("Dance class", "Workout", "0", "14:00", "16:00");
        }, "Invalid date should have thrown IllegalArugment-exception!");
        assertThrows(IllegalArgumentException.class, () -> {
            new Activity("Dance class", "Workout", "29", "14:00", "16:00");
        }, "Invalid date should have thrown IllegalArugment-exception!");
        assertThrows(IllegalArgumentException.class, () -> {
            new Activity("Dance class", "Workout", "28", "14.00", "16:00");
        }, "Invalid start time should have thrown IllegalArugment-exception!");
        assertThrows(IllegalArgumentException.class, () -> {
            new Activity("Dance class", "Workout", "28", "29:00", "16:00");
        }, "Invalid start time should have thrown IllegalArugment-exception!");
        assertThrows(IllegalArgumentException.class, () -> {
            new Activity("Dance class", "Workout", "28", "14:00", "16.00");
        }, "Invalid start time should have thrown IllegalArugment-exception!");
        assertThrows(IllegalArgumentException.class, () -> {
            new Activity("Dance class", "Workout", "28", "14:00", "91:00");
        }, "Invalid start time should have thrown IllegalArugment-exception!");
        assertThrows(IllegalArgumentException.class, () -> {
            new Activity("Dance class", "Workout", "28", "14:00", "12:00");
        }, "End time before start time should have thrown IllegalArugment-exception!");
        assertThrows(IllegalArgumentException.class, () -> {
            new Activity("Dance class", "Workout", "28", "14:00", "14:00");
        }, "End time equal to start time should have thrown IllegalArugment-exception!");
    }

    @Test
    @DisplayName("Test index of activity.")
    public void testIndex() {
        a2 = new Activity("Football game", "Workout", "1", "18:00", "20:00");
        assertEquals(1, a1.getRow(), "Row index was wrong.");
        assertEquals(5, a1.getColumn(), "Column index was wrong.");
        assertEquals(0, a2.getRow(), "Row index was wrong.");
        assertEquals(0, a2.getColumn(), "Column index was wrong.");
    }
    
}

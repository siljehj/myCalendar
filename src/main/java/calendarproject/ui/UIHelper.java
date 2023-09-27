package calendarproject.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import calendarproject.classes.Activity;
import calendarproject.classes.CalendarTile;
import calendarproject.classes.PersonalCalendar;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UIHelper {

    // method for initializing when opening the app

    public static PersonalCalendar initializeCalendar(GridPane grid) {
        return new PersonalCalendar("CalendarFromApp");
    }

    // methods for making empty grids and tiles

    public static void makeEmptyGrid(GridPane grid) {
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 7; column++) {
                makeEmptyTile(grid, column, row);
            }
        }
    }

    public static void makeEmptyTile(GridPane grid, int col, int row) {
        FlowPane pane = new FlowPane();
        GridPane.setConstraints(pane, col, row);
        pane.setMaxSize(135, 120);
        pane.setStyle("-fx-border-color: palevioletred; -fx-border-width: 1;");
        pane.setAlignment(Pos.TOP_RIGHT);

        // adding the number at the top
        Integer date = (7 * row) + col + 1;
        Text dateText = new Text(date.toString() + ". ");

        // GridPane.setMargin(text, new Insets(8));
        dateText.setFill(Color.PALEVIOLETRED);
        dateText.setStyle(
                "-fx-font-size: 25px; -fx-font-weight: bold; -fx-stroke: black; -fx-stroke-width: 0.3; -fx-stroke-type: outside;");
        pane.getChildren().add(dateText);
        grid.add(pane, col, row);
    }

    public static void deleteGrid(GridPane grid) {
        grid.getChildren().clear();
    }

    // methods for making grid and tiles from calendar

    public static void makeGridFromCalendar(PersonalCalendar calendar, GridPane grid) {
        makeEmptyGrid(grid);
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 7; column++) {
                for (Activity act : calendar.getTileFromIndex(row, column).getActivities()) {
                    updateGrid(grid, calendar, act);
                }
            }
        }
    }

    public static void makeTileFromCalendar(GridPane grid, int row, int col, PersonalCalendar calendar) {

        // creating a new flowplane at the desired coordinates
        FlowPane pane = new FlowPane();
        GridPane.setConstraints(pane, col, row);
        pane.setStyle("-fx-border-color: palevioletred; -fx-border-width: 1;");
        pane.setAlignment(Pos.TOP_RIGHT);
        Integer date = (7 * row) + col + 1;
        Text dateText = new Text(date.toString() + ". ");
        dateText.setFill(Color.PALEVIOLETRED);
        dateText.setStyle(
                "-fx-font-size: 25px; -fx-font-weight: bold; -fx-stroke: black; -fx-stroke-width: 0.3; -fx-stroke-type: outside;");
        pane.getChildren().add(dateText);

        // creating a text area to add each activity to a new line

        TextArea activitesText = new TextArea();
        String activitiesString = "";

        CalendarTile tile = calendar.getTileFromIndex(row, col);
        for (Activity a : tile.getActivities()) {
            activitiesString += a.getStartTime() + "-" + a.getEndTime() + ": " + a.getName() + "\n";
        }

        activitiesString = activitiesString.trim(); // removing \n from start and end
        activitesText.appendText(activitiesString);

        activitesText.setFont(Font.font(11));
        activitesText.setEditable(false);
        activitesText.setStyle(
                "-fx-control-inner-background: #FEEEF7; -fx-focus-color: transparent; -fx-text-box-border: transparent;");

        activitesText.setPrefSize(133, 84);
        activitesText.setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        activitesText.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);

        pane.getChildren().add(activitesText);
        grid.add(pane, col, row);
    }

    // methods for adding activities and updating the grid

    public static boolean addActivityConfirmed() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation alert");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to add this activity? You can't delete it or edit it later.");
        alert.getDialogPane().setPrefSize(300, 100);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static boolean addActivity(String name, String category,
            String date, String startTime, String endTime, PersonalCalendar calendar, GridPane grid) {
        try {
            Activity newActivity = new Activity(name, category, date, startTime, endTime);
            calendar.addNewActivity(newActivity);
            UIHelper.updateGrid(grid, calendar, newActivity);
            UIHelper.filterCalendar(grid, calendar, "#NoFilter");
            return true;
        } catch (IllegalArgumentException e) {
            UIHelper.invalidInputAlert(name, category, date, startTime, endTime);
            return false;
        }
    }

    public static void updateGrid(GridPane grid, PersonalCalendar calendar, Activity activity) {
        for (Node node : grid.getChildren()) {
            if (GridPane.getColumnIndex(node) == activity.getColumn()
                    && GridPane.getRowIndex(node) == activity.getRow()) {
                grid.getChildren().remove(node);
                break;
            }
        }
        makeTileFromCalendar(grid, activity.getRow(), activity.getColumn(), calendar);
    }

    // methods for making a new empty calendar

    public static void makeNewCalendar(GridPane grid, PersonalCalendar calendar) {
        UIHelper.deleteGrid(grid);
        UIHelper.makeEmptyGrid(grid);
        calendar.removeAllActivites();
    }

    public static boolean newCalendarConfirmed() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation alert");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to make a new calendar? Your activities won't be saved.");
        alert.getDialogPane().setPrefSize(300, 100);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    // method for updating the menu buttons

    public static MenuButton updateMenuButton(AnchorPane background, String choosenCategory, int prefWidth,
            int prefHeight,
            MenuButton button, int x, int y) {

        // removing menu button
        for (Node node : background.getChildren()) {
            if (node instanceof MenuButton && node.getAccessibleText() == button.getText()) {
                background.getChildren().remove(node);
                break;
            }
        }

        // creating a new menu button with the choosen category as label
        MenuButton menuButton = new MenuButton(choosenCategory);
        List<String> categories = new ArrayList<>();
        Collections.addAll(categories, "School", "Work", "Workout", "Social", "Other");
        if (button.getWidth() == 150) {
            categories.add("#NoFilter");
        }
        for (String category : categories) {
            if (!category.equals(choosenCategory)) {
                MenuItem item = new MenuItem(category);
                item.setStyle("-fx-pref-width: " + Integer.toString(prefWidth - 12) + ";");
                menuButton.getItems().add(item);
            }
        }

        menuButton.setPrefSize(prefWidth, prefHeight);
        menuButton.setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        menuButton.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);

        background.getChildren().add(menuButton);
        menuButton.relocate(x, y);
        return menuButton;
    }

    // method for filtering the calendar by category

    public static void filterCalendar(GridPane grid, PersonalCalendar calendar, String filter) {
        deleteGrid(grid);
        if (filter.equals("#NoFilter")) {
            makeGridFromCalendar(calendar, grid);
        } else {
            PersonalCalendar filtered = calendar.filterCalendar(filter);
            makeGridFromCalendar(filtered, grid);
        }
    }

    // alert making method

    public static void invalidInputAlert(String name, String category, String date, String startTime, String endTime) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Invalid input!");
        alert.setHeaderText(null);
        alert.getDialogPane().setPrefSize(300, 100);
        if (name.equals("") || category.equals("") || date.equals("") || startTime.equals("") || endTime.equals("")) {
            alert.setContentText("You must fill out all fields!");
        } else if (!Activity.isDigit(date)) {
            alert.setContentText("Date must be a number!");
        } else if (!Activity.isValidDate(Integer.valueOf(date))) {
            alert.setContentText("Date must be between 1st and 28th.");
        } else if (!Activity.isValidTime(startTime)) {
            alert.setContentText("Invalid start time. Must be of the form HH:MM.");
        } else if (!Activity.isValidTime(endTime)) {
            alert.setContentText("Invalid end time.");
        } else if (!Activity.isValidTimes(startTime, endTime)) {
            alert.setContentText("Start time must be before end time. Must be of the form HH:MM.");
        }
        alert.showAndWait();
    }

}
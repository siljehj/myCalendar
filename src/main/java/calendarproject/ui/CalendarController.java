package calendarproject.ui;

import calendarproject.classes.PersonalCalendar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class CalendarController {

    private PersonalCalendar calendar;

    @FXML
    private AnchorPane background;

    @FXML
    private GridPane grid;

    @FXML
    private TextField activityName;

    @FXML
    private MenuButton activityCategory;

    @FXML
    private MenuItem school;

    @FXML
    private MenuItem work;

    @FXML
    private MenuItem workout;

    @FXML
    private MenuItem social;

    @FXML
    private MenuItem other;

    @FXML
    private TextField date;

    @FXML
    private TextField startTime;

    @FXML
    private TextField endTime;

    @FXML
    private Button addActivityButton;

    @FXML
    private Button newCalendarButton;

    @FXML
    private MenuButton filterButton;

    @FXML
    private MenuItem noFilter;

    @FXML
    private Button filter;

    // initalizing method

    @FXML
    public void initialize() {
        this.calendar = UIHelper.initializeCalendar(grid);
        UIHelper.makeGridFromCalendar(this.calendar, grid);
    }

    // methods called when choosing from the category drop down menu

    @FXML
    public void changeCategoryToSchool() {

        // making a new cateogry menu button with the chosen category as the title
        MenuButton newButton = UIHelper.updateMenuButton(background, school.getText(), 162, 25, this.activityCategory,
                200,
                625);
        this.activityCategory.setText(school.getText());

        // setting on action to each item
        for (MenuItem item : newButton.getItems()) {
            item.setOnAction(event -> changeCategory(item.getText()));
        }
    }

    @FXML
    public void changeCategoryToWork() {
        MenuButton newButton = UIHelper.updateMenuButton(background, work.getText(), 162, 25, this.activityCategory,
                200,
                625);
        this.activityCategory.setText(work.getText());
        for (MenuItem item : newButton.getItems()) {
            item.setOnAction(event -> changeCategory(item.getText()));
        }
    }

    @FXML
    public void changeCategoryToWorkout() {
        MenuButton newButton = UIHelper.updateMenuButton(background, workout.getText(), 162, 25, this.activityCategory,
                200, 625);
        this.activityCategory.setText(workout.getText());
        for (MenuItem item : newButton.getItems()) {
            item.setOnAction(event -> changeCategory(item.getText()));
        }
    }

    @FXML
    public void changeCategoryToSocial() {
        MenuButton newButton = UIHelper.updateMenuButton(background, social.getText(), 162, 25, this.activityCategory,
                200, 625);
        this.activityCategory.setText(social.getText());
        for (MenuItem item : newButton.getItems()) {
            item.setOnAction(event -> changeCategory(item.getText()));
        }
    }

    @FXML
    public void changeCategoryToOther() {
        MenuButton newButton = UIHelper.updateMenuButton(background, other.getText(), 162, 25, this.activityCategory,
                200, 625);
        this.activityCategory.setText(other.getText());
        for (MenuItem item : newButton.getItems()) {
            item.setOnAction(event -> changeCategory(item.getText()));
        }
    }

    private void resetCategory() {
        MenuButton newButton = UIHelper.updateMenuButton(background, "", 162, 25, this.activityCategory,
                200, 625);
        this.activityCategory.setText("");
        for (MenuItem item : newButton.getItems()) {
            item.setOnAction(event -> changeCategory(item.getText()));
        }
    }

    private void changeCategory(String category) {
        if (category.equals("School")) {
            changeCategoryToSchool();
        } else if (category.equals("Work")) {
            changeCategoryToWork();
        } else if (category.equals("Workout")) {
            changeCategoryToWorkout();
        } else if (category.equals("Social")) {
            changeCategoryToSocial();
        } else if (category.equals("Other")) {
            changeCategoryToOther();
        }
    }

    // method called when pressing the add activity button

    @FXML
    public void addActivityAlertMessage() {
        if (UIHelper.addActivityConfirmed()) {
            addActivity();
        }
    }

    @FXML
    public void addActivity() {
        if (UIHelper.addActivity(this.activityName.getText(), this.activityCategory.getText(),
                this.date.getText(), this.startTime.getText(), this.endTime.getText(), this.calendar, this.grid)) {
            removeText();
            removeFilter();
        }
    }

    // methods called when choosing from the filter drop down menu

    @FXML
    public void filterBySchool() {

        // making a new filter menu button with the chosen category as the title
        MenuButton newButton = UIHelper.updateMenuButton(background, school.getText(), 150, 25, this.filterButton,
                24,
                705);
        this.filterButton.setText(school.getText());

        // setting on action to each item
        for (MenuItem item : newButton.getItems()) {
            item.setOnAction(event -> changeFilter(item.getText()));
        }
    }

    @FXML
    public void filterByWork() {
        MenuButton newButton = UIHelper.updateMenuButton(background, work.getText(), 150, 25, this.filterButton,
                24,
                705);
        this.filterButton.setText(work.getText());
        for (MenuItem item : newButton.getItems()) {
            item.setOnAction(event -> changeFilter(item.getText()));
        }
    }

    @FXML
    public void filterByWorkout() {
        MenuButton newButton = UIHelper.updateMenuButton(background, workout.getText(), 150, 25, this.filterButton,
                24,
                705);
        this.filterButton.setText(workout.getText());
        for (MenuItem item : newButton.getItems()) {
            item.setOnAction(event -> changeFilter(item.getText()));
        }
    }

    @FXML
    public void filterBySocial() {
        MenuButton newButton = UIHelper.updateMenuButton(background, social.getText(), 150, 25, this.filterButton,
                24,
                705);
        this.filterButton.setText(social.getText());
        for (MenuItem item : newButton.getItems()) {
            item.setOnAction(event -> changeFilter(item.getText()));
        }
    }

    @FXML
    public void filterByOther() {
        MenuButton newButton = UIHelper.updateMenuButton(background, other.getText(), 150, 25, this.filterButton,
                24,
                705);
        this.filterButton.setText(other.getText());
        for (MenuItem item : newButton.getItems()) {
            item.setOnAction(event -> changeFilter(item.getText()));
        }
    }

    @FXML
    public void removeFilter() {
        MenuButton newButton = UIHelper.updateMenuButton(background, noFilter.getText(), 150, 25, this.filterButton,
                24,
                705);
        this.filterButton.setText(noFilter.getText());
        for (MenuItem item : newButton.getItems()) {
            item.setOnAction(event -> changeFilter(item.getText()));
        }
    }

    private void changeFilter(String filter) {
        if (filter.equals("School")) {
            filterBySchool();
        } else if (filter.equals("Work")) {
            filterByWork();
        } else if (filter.equals("Workout")) {
            filterByWorkout();
        } else if (filter.equals("Social")) {
            filterBySocial();
        } else if (filter.equals("Other")) {
            filterByOther();
        } else if (filter.equals("#NoFilter")) {
            removeFilter();
        }
    }

    // method called when pressing the filter button

    @FXML
    public void filterCalendar() {
        UIHelper.filterCalendar(this.grid, this.calendar, this.filterButton.getText());
    }

    // methods called when pressing the new calendar button

    @FXML
    public void newCalendarAlertMessage() {
        if (UIHelper.newCalendarConfirmed()) {
            makeNewCalendar();
        }
    }

    private void makeNewCalendar() {
        UIHelper.makeNewCalendar(this.grid, this.calendar);
        removeText();
    }

    // method from clearing the text fields after pressing the new calendar button
    // or the add activity button

    private void removeText() {
        this.activityName.clear();
        this.date.clear();
        this.startTime.clear();
        this.endTime.clear();
        resetCategory();
    }
}

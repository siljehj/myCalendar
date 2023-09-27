package calendarproject.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    public static void makeCalendarFile(String path) {
        try {
            File newFile = new File(
                    path);
            if (newFile.createNewFile()) {
                System.out.println("Created a new calendar.");
            } else {
                System.out.println("This calendar already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    public static List<Activity> readCalendarFromFile(String path) {
        List<Activity> activities = new ArrayList<>();
        BufferedReader reader = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(
                    path);
            reader = new BufferedReader(fileReader);
            String line = reader.readLine();

            while (line != null) {
                activities.add(handleLine(line));
                line = reader.readLine();
            }
            reader.close();
            return activities;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(
                    path);
            reader = new BufferedReader(fileReader);
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static Activity handleLine(String line) {
        String[] activityArray = line.split(",");
        return new Activity(activityArray[0], activityArray[1], activityArray[2], activityArray[3], activityArray[4]);
    }

    public static void writeActivityToFile(Activity activity, String path) {
        List<String> list = Arrays.asList(activity.getName(), activity.getCategory(),
                Integer.toString(activity.getDate()), activity.getStartTime().toString(),
                activity.getEndTime().toString());
        String newLine = String.join(",", list);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(
                    path,
                    true);
            fileWriter.write(newLine);
            fileWriter.write(System.lineSeparator());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("OOoops");
            e.printStackTrace();
        }
    }

    public static void deleteContentFromFile(String path) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(
                    path,
                    false);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(String fileName) {
        try {
            File newFile = new File(
                    "C:\\Users\\silje\\Documents\\prosjekt\\TDT4100-prosjekt\\src\\main\\resources\\calendarproject\\File\\"
                            + fileName);
            if (newFile.delete()) {
                System.out.println("Successfully deleted file: " + fileName);
            } else {
                System.out.println("Failed to delete this file.");
            }
        } catch (Exception e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

}

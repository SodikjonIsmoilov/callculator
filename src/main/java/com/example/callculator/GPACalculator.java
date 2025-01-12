package com.example.callculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

public class GPACalculator extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);


        Label courseLabel = new Label("Course Name:");
        TextField courseField = new TextField();
        Label creditLabel = new Label("Credits:");
        TextField creditField = new TextField();
        Label gradeLabel = new Label("Grade:");
        TextField gradeField = new TextField();

        grid.add(courseLabel, 0, 0);
        grid.add(courseField, 1, 0);
        grid.add(creditLabel, 0, 1);
        grid.add(creditField, 1, 1);
        grid.add(gradeLabel, 0, 2);
        grid.add(gradeField, 1, 2);

        Button addCourseButton = new Button("Add Course");
        grid.add(addCourseButton, 1, 3);

        ListView<String> courseListView = new ListView<>();
        grid.add(courseListView, 0, 4, 2, 1);

        Button calculateButton = new Button("Calculate GPA");
        Label resultLabel = new Label("GPA: ");

        VBox vbox = new VBox(10, grid, calculateButton, resultLabel);
        vbox.setPadding(new Insets(10));

        addCourseButton.setOnAction(e -> {
            String course = courseField.getText();
            String credits = creditField.getText();
            String grade = gradeField.getText();

            if (course.isEmpty() || credits.isEmpty() || grade.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "All fields are required", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            try {
                Integer.parseInt(credits);
                Double.parseDouble(grade);
                courseListView.getItems().add(course + " | Credits: " + credits + " | Grade: " + grade);
                courseField.clear();
                creditField.clear();
                gradeField.clear();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid number format for credits or grade!", ButtonType.OK);
                alert.showAndWait();
            }
        });
        calculateButton.setOnAction(e -> {
            double totalGradePoints = 0;
            int totalCredits = 0;

            for (String item : courseListView.getItems()) {
                String[] parts = item.split("\\| ");
                int credits = Integer.parseInt(parts[1].trim().split(":")[1].trim());
                double grade = Double.parseDouble(parts[2].trim().split(":")[1].trim());
                totalGradePoints += credits * grade;
                totalCredits += credits;
            }

            if (totalCredits == 0) {
                resultLabel.setText("GPA: N/A (No Courses added");

            }else {
                double gpa = totalGradePoints / totalCredits;
                resultLabel.setText(String.format("GPA: %.2f", gpa));
            }
        });

        Scene scene = new Scene(vbox, 300, 400);
        primaryStage.setTitle("GPA calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

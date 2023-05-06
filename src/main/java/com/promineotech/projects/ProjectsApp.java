package com.promineotech.projects;

import com.promineotech.projects.entity.Project;
import com.promineotech.projects.exception.DbException;
import com.promineotech.projects.service.ProjectService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ProjectsApp {
        private Scanner scanner = new Scanner(System.in);                       //Step 2
        private ProjectService projectService = new ProjectService();

        //@formatter:off
        private List<String> operations = List.of(                              //Step 1
                "1) Add a Project"

        );
        //@formatter:on

        public static void main(String[] args) {                                //Step 3
            new ProjectsApp().processUserSelections();
        }

        private void processUserSelections(){
            boolean done = false;                                               //Step 4


            while (!done){                                                      //Step 5
                try {
                    int selection = getUserSelection();                         //Step 11
                    switch (selection) {
                        case -1:
                            done = exitMenu();
                            break;

                        case 1:
                            createProject();
                            break;

                        default:
                            System.out.println("\n" + selection + " is not valid. Try again");
                            break;
                    }
                }catch (Exception e) {
                    System.out.println("\nError: " + e.toString() + "Try again.");  //Step 5.2
                }
            }
        }

        private void createProject() {                                      //Step 12
            String projectName = getStringInput("Enter the project name");
            BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
            BigDecimal actualHours = getDecimalInput("Enter the actual hours");
            Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
            String notes = getStringInput("Enter the project notes");

            Project project = new Project();

            project.setProjectName(projectName);
            project.setEstimatedHours(estimatedHours);
            project.setActualHours(actualHours);
            project.setDifficulty(difficulty);
            project.setNotes(notes);

            Project dbProject = projectService.addProject(project);
            System.out.println("You have successfully created project: " + dbProject);
        }

        private boolean exitMenu() {
            System.out.println("\nExiting the menu.");
            return true;
        }

        private int getUserSelection() {                                         //Step 6
            printOperations();
            Integer input = getIntInput("\nEnter a menu selection");

            return Objects.isNull(input) ? -1 : input;
        }


        private void printOperations() {                                         //Step 7
            System.out.println();
            System.out.println("\n These are available selections. Press Enter key to quit:");

            operations.forEach(line-> System.out.println("   " + line));         //Step 8
        }
        private Integer getIntInput(String prompt) {                             //Steps 9
            String input = getStringInput(prompt);

            if (Objects.isNull(input)){
                return null;
            }
            try {
                return Integer.valueOf(input);
            }
            catch (NumberFormatException e){
                throw new DbException(input + " is not a valid number.");
            }
        }
        private BigDecimal getDecimalInput(String prompt) {                    //Step 13
            String input = getStringInput(prompt);

            if (Objects.isNull(input)){
                return null;
            }
            try {
                return new BigDecimal(input).setScale(2);
            }
            catch (NumberFormatException e){
                throw new DbException(input + " is not a valid decimal number.");
            }
        }

        private String getStringInput(String prompt) {                      //Steps 10
            System.out.print(prompt + ": ");
            String input = scanner.nextLine();
            return input.isBlank() ? null : input.trim();
        }
    }
//}

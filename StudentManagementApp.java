package StudentManagementApp;
import java.util.ArrayList;
import java.util.Scanner;


class Student {
    String name;
    String studentID;
    int[] marks;

  
    public Student(String name, String studentID, int[] marks) {
        this.name = name;
        this.studentID = studentID;
        this.marks = marks;
    }

   
    public double calculateAverage() {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
       
        return marks.length == 0 ? 0 : (double) sum / marks.length;
    }

    
    public String determineGrade() {
        double avg = calculateAverage();
        if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "F";
    }

   
    public void displayDetails() {
        System.out.println("ID: " + studentID + " | Name: " + name + " | Avg Marks: " + String.format("%.2f", calculateAverage()) + " | Grade: " + determineGrade());
    }
}

class StudentManager {
    ArrayList<Student> students = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void addStudent() {
        System.out.print("Enter Name: ");
        String name = scanner.next(); 
        System.out.print("Enter Student ID: ");
        String studentID = scanner.next();
        System.out.print("Enter marks for 5 subjects (e.g., 85 70 90 65 75): ");
        int[] marks = new int[5];
        for (int i = 0; i < 5; i++) {
            
            while (true) {
                try {
                    marks[i] = scanner.nextInt();
                    if (marks[i] < 0 || marks[i] > 100) {
                        System.out.print("Marks must be between 0 and 100. Enter mark for subject " + (i + 1) + ": ");
                    } else {
                        break;
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.print("Invalid input. Please enter an integer mark for subject " + (i + 1) + ": ");
                    scanner.next(); 
                }
            }
        }
        students.add(new Student(name, studentID, marks));
        System.out.println("Student added successfully!");
    }

    
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records to display.");
            return;
        }
        System.out.println("\n--- Student Records ---");
        for (Student student : students) {
            student.displayDetails();
        }
        System.out.println("---------------------");
    }

  
    public void searchStudentByID() {
        System.out.print("Enter Student ID to search: ");
        String id = scanner.next();
        boolean found = false;
        for (Student student : students) {
            if (student.studentID.equals(id)) {
                student.displayDetails();
                found = true;
                break; 
            }
        }
        if (!found) {
            System.out.println("Student with ID '" + id + "' not found!");
        }
    }

   
    public void calculateStatistics() {
        if (students.isEmpty()) {
            System.out.println("No student records found to calculate statistics.");
            return;
        }

        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;
        double total = 0;

        for (Student student : students) {
            double avg = student.calculateAverage();
            if (avg > highest) highest = avg;
            if (avg < lowest) lowest = avg;
            total += avg;
        }

        System.out.println("\n--- Class Statistics ---");
        System.out.println("Number of Students: " + students.size());
        System.out.println("Highest Average Marks: " + String.format("%.2f", highest));
        System.out.println("Lowest Average Marks: " + String.format("%.2f", lowest));
        System.out.println("Class Average Marks: " + String.format("%.2f", (total / students.size())));
        System.out.println("---------------------");
    }

   
    public void showMenu() {
        
        while (true) {
            System.out.println("\n--- Student Information Management System ---");
            System.out.println("1. Add Student Record");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Calculate Class Statistics");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = -1; 
            try {
                choice = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next();
                continue; 
            }
            
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchStudentByID();
                    break;
                case 4:
                    calculateStatistics();
                    break;
                case 5:
                    System.out.println("Exiting Student Information Management System. Goodbye!");
                    scanner.close(); 
                    return; 
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 5.");
                    break;
            }
        }
    }
}

public class StudentManagementApp {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        manager.showMenu();
    }
}
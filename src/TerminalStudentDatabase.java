import java.util.HashMap;
import java.util.Scanner;

public class TerminalStudentDatabase {

    private static HashMap<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nStudent Database Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Remove Student");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    removeStudent(scanner);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }

        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String id = scanner.next();
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        System.out.print("Enter student gender (M/F): ");
        String gender = scanner.next();
        System.out.print("Enter assignment 1 score: ");
        double assignment1 = scanner.nextDouble();
        System.out.print("Enter assignment 2 score: ");
        double assignment2 = scanner.nextDouble();

        Student student = new Student(id, name, age, gender, assignment1, assignment2);
        students.put(id, student);
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        System.out.println("\nCurrent Students:");
        for (Student student : students.values()) {
            System.out.println(student);
        }
    }

    private static void removeStudent(Scanner scanner) {
        System.out.print("Enter student ID to remove: ");
        String id = scanner.next();
        if (students.containsKey(id)) {
            students.remove(id);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static class Student {
        private String id;
        private String name;
        private int age;
        private String gender;
        private double assignment1;
        private double assignment2;
        private double finalscore;

        public Student(String id, String name, int age, String gender, double assignment1, double assignment2) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.assignment1 = assignment1;
            this.assignment2 = assignment2;
            calculateFinalScore();
        }

        public void calculateFinalScore() {
            this.finalscore = (this.assignment1 * 0.35) + (this.assignment2 * 0.65);
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender +
                    ", Assignment 1 Score: " + assignment1 + ", Assignment 2 Score: " + assignment2 + ", Final " +
                    "Score: " + finalscore;
        }
    }
}

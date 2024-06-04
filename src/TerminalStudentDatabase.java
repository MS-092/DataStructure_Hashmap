import java.util.HashMap;
import java.util.Scanner;

public class TerminalStudentDatabase {

    private static HashMap<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initializeStudentData();

        boolean running = true;
        while (running) {
            System.out.println("\nStudent Database Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            long startTime, endTime, duration;
            long startMemory, endMemory, memoryUsed;

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
                    searchStudent(scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }

        scanner.close();
    }

    private static void initializeStudentData() {
        String[] names = {"Blake", "Sophia", "Logan", "Charlotte", "Eli", "Aurora", "Mason", "Emily", "Alexander",
                "Madison", "Levi", "Chloe", "Carter", "Aria", "James", "Harper", "Lucas", "Addison", "Henry", "Luna",
                "Owen", "Mila", "Jack", "Evelyn", "Wyatt", "Scarlett", "Oliver", "Avery", "Benjamin", "Victoria", "William", "Ella", "Michael", "Nora", "Daniel", "Penelope", "Jacob", "Grace", "Ethan", "Zoey", "Matthew", "Layla", "Noah", "Riley", "Liam", "Sofia", "Sebastian", "Camila", "Aiden", "Audrey", "Gabriel", "Brooklyn", "Jackson", "Bella", "David", "Paisley", "Joseph", "Skylar", "Samuel", "Clara", "Christopher", "Lucy", "Ryan", "Everly", "Isaac", "Anna", "Nathan", "Caroline", "Andrew", "Genesis", "John", "Aaliyah", "Luke", "Kennedy", "Olivia", "Kinsley", "Isaiah", "Allison", "Elijah", "Maya", "Jonathan", "Sarah", "Julian", "Madelyn", "Hunter", "Adeline", "Anthony", "Alexa", "Nicholas", "Ariana", "Evan", "Elena", "Isaiah", "Gabriella", "Lucas", "Aria", "Ethan", "Grace", "Liam", "Lily", "Mason", "Sophia", "Noah", "Ava", "James", "Emma", "Oliver", "Isabella", "Elijah", "Charlotte", "William", "Amelia", "Benjamin", "Ella", "Alexander", "Mia", "Henry", "Harper", "Logan", "Abigail", "Daniel", "Evelyn", "Michael", "Layla", "Jackson", "Scarlett", "Sebastian", "Avery", "Matthew", "Sofia", "David", "Camila", "Joseph", "Chloe", "Samuel", "Emily", "Christopher", "Madison", "Gabriel", "Luna", "Carter", "Nora", "Owen", "Zoe", "Isaac", "Penelope", "Wyatt", "Addison", "John", "Elizabeth", "Nathan", "Victoria", "Dylan", "Aubrey", "Luke", "Zoey", "Andrew", "Audrey", "Joshua", "Aurora", "Nicholas", "Stella", "Jonathan", "Brooklyn", "Caleb", "Mila", "Jack", "Hannah", "Ryan", "Ellie", "Evan", "Ariana", "Nathaniel", "Mackenzie", "Eli", "Natalie", "Isaiah", "Leah", "Hunter", "Maya", "Christian", "Samantha", "Aaron", "Skylar", "Connor", "Nevaeh"
        };

        String[] genders = {"M", "F"};

        // set amount of students for the dataset, all are randomly(1-200)
        int numofStudents = 25;
        for (int i = 1; i <= numofStudents; i++) {
            String id = "S" + i;
            String name = names[i - 1];
            int age = 18 + (int) (Math.random() * 3);
            String gender = genders[i % 2];
            double assignment1 = Math.random() * 100;
            double assignment2 = Math.random() * 100;

            Student student = new Student(id, name, age, gender, assignment1, assignment2);
            students.put(id, student);
        }
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

        long startTime = System.nanoTime();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Student student = new Student(id, name, age, gender, assignment1, assignment2);
        students.put(id, student);
        System.out.println("Student added successfully.");
        long endTime = System.nanoTime();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long duration = (endTime - startTime) / 1000000;
        long memoryUsed = endMemory - startMemory;
        System.out.println("Time taken to add student: " + duration + " ms");
        System.out.println("Memory used for add: " + memoryUsed + " bytes");
    }

    private static void viewStudents() {

        long startTime = System.nanoTime();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("\nCurrent Students:");
        for (Student student : students.values()) {
            System.out.println(student);
        }
        long endTime = System.nanoTime();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long duration = (endTime - startTime) / 1000000;
        long memoryUsed = endMemory - startMemory;
        System.out.println("Time taken to view student: " + duration + " ms");
        System.out.println("Memory used for view: " + memoryUsed + " bytes");
    }

    private static void removeStudent(Scanner scanner) {

        System.out.print("Enter student ID to remove: ");
        String id = scanner.next();

        long startTime = System.nanoTime();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (students.containsKey(id)) {
            students.remove(id);
            System.out.println("Student name successfully.");
        } else {
            System.out.println("Student not found.");
        }
        long endTime = System.nanoTime();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long duration = (endTime - startTime) / 1000000;
        long memoryUsed = endMemory - startMemory;
        System.out.println("Time taken to remove student: " + duration + " ms");
        System.out.println("Memory used for remove: " + memoryUsed + " bytes");
    }

    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter student ID to search: ");
        String id = scanner.next();

        long startTime = System.nanoTime();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Student student = linearSearch(id);
        long endTime = System.nanoTime();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        if (student != null) {
            System.out.println("Student found:");
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }

        long duration = (endTime - startTime) / 1000000;
        long memoryUsed = endMemory - startMemory;
        System.out.println("Time taken to search student: " + duration + " ms");
        System.out.println("Memory used for search: " + memoryUsed + " bytes");
    }

    private static Student linearSearch(String id) {
        for (Student student : students.values()) {
            if (student.id.equals(id)) {
                return student;
            }
        }
        return null;
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
                    ", Assignment 1 Score: " + String.format("%.2f", assignment1) +
                    ", Assignment 2 Score: " + String.format("%.2f", assignment2) +
                    ", Final Score: " + String.format("%.2f", finalscore);
        }
    }
}
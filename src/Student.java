class Student {
    private String id;
    private String name;
    private int age;
    private String gender;
    private double assignment1;
    private double assignment2;
    private double finalScore;
    private static double formula1 = 0.35;
    private static double formula2 = 0.65;

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
        this.finalScore = (assignment1 * formula1) + (assignment2 * formula2);
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public double getAssignment1() { return assignment1; }
    public double getAssignment2() { return assignment2; }
    public double getFinalScore() { return finalScore; }

    public void setAssignment1(double assignment1) {
        this.assignment1 = assignment1;
        calculateFinalScore();
    }

    public void setAssignment2(double assignment2) {
        this.assignment2 = assignment2;
        calculateFinalScore();
    }

    public static void setFormula1(double newFormula1) {
        formula1 = newFormula1;
    }

    public static void setFormula2(double newFormula2) {
        formula2 = newFormula2;
    }
}
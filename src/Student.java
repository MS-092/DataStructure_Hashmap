public class Student {
    private String id;
    private String name;
    private int age;
    private String gender;
    private double assignment1;
    private double assignment2;
    private double finalScore;
    private double formula1 = 0.35;
    private double formula2 = 0.65;

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
        this.finalScore = (this.assignment1 * formula1) + (this.assignment2 * formula2);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public double getAssignment1() {
        return assignment1;
    }

    public void setAssignment1(double assignment1) {
        this.assignment1 = assignment1;
    }

    public double getAssignment2() {
        return assignment2;
    }

    public void setAssignment2(double assignment2) {
        this.assignment2 = assignment2;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFormula1(double formula1) {
        this.formula1 = formula1;
    }

    public void setFormula2(double formula2) {
        this.formula2 = formula2;
    }
}
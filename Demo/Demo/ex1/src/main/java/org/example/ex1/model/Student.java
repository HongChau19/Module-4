package org.example.ex1.model;

public class Student {
    private String studentId;
    private String name;
    private float gpa;
    private String rank;

    public Student() {
    }

    public Student(String studentId, String name, float gpa, String rank) {
        this.studentId = studentId;
        this.name = name;
        this.gpa = gpa;
        this.rank = rank;
    }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public float getGpa() { return gpa; }
    public void setGpa(float gpa) { this.gpa = gpa; }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }
}
    
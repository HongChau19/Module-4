package org.example.ex2.models;


@Enity
@Table(name = "students")
public class Student {

    @Id
    @NotBlank(message = "Không được để trống mã Sinh viên")
    @Size(min = 3, max = 10, message = "Mã sinh viên từ 3 ký tự đến 10 ký tự")
    private String id;

    @NotBlank(message = "Không được để trống tên Sinh viên")
    private String name;

    String avatar;

    private float gpa;
    private String rank;

    public Student() {}

    public Student(String id, String name, float gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.rank = calculateRank(gpa);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public float getGpa() { return gpa; }
    public void setGpa(float gpa) {
        this.gpa = gpa;
        this.rank = calculateRank(gpa);
    }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }

    private String calculateRank(float gpa) {
        if (gpa >= 9.0) return "Xuất sắc";
        if (gpa >= 8.0) return "Giỏi";
        if (gpa >= 6.5) return "Khá";
        if (gpa >= 5.0) return "Trung bình";
        return "Yếu";
    }

    @Override
    public String toString() {
        return "Student{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", gpa=" + gpa + ", rank='" + rank + '\'' + '}';
    }
}
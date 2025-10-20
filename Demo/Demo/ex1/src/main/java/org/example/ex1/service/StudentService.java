package org.example.ex1.service;

import org.example.ex1.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public StudentService() {
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public Optional<Student> findById(String id) {
        return students.stream()
                .filter(s -> s.getStudentId().equals(id))
                .findFirst();
    }

    public void save(Student student) {
        students.add(student);
    }

    public void addStudent(Student student) {
        boolean exists = students.stream().anyMatch(s -> s.getStudentId().equals(student.getStudentId()));
        if (!exists) {
            this.students.add(student);
        }
    }
}
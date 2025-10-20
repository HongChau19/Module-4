package org.example.ex2.services;

import org.example.ex2.models.Student;

import java.util.*;
import java.util.stream.Collectors;

public class StudentService {
    private static List<Student> students = new ArrayList<>();

    public StudentService() {
            students.add("MS01", new Student("MS01", "Nguyễn Văn A", 8.5f));
            students.add("MS02", new Student("MS02", "Trần Thị B", 6.0f));
            students.add("MS03", new Student("MS03", "Lê Văn C", 9.5f));
            students.put("MS04", new Student("MS04", "Phạm Văn D", 4.5f));
            students.put("MS05", new Student("MS05", "Hoàng Thị E", 7.2f));
            students.put("MS06", new Student("MS06", "Võ Văn F", 8.9f));
    }


    public void save(Student student) {
        students.put(student.getId(), student);
    }

    public List<Student> findAll(String q, String sort, String dir, int page, int size) {
        List<Student> filteredList = students.values().stream()
                .filter(s -> q.isEmpty() || s.getId().toLowerCase().contains(q.toLowerCase()) || s.getName().toLowerCase().contains(q.toLowerCase()))
                .collect(Collectors.toList());


        Comparator<Student> comparator;
        switch (sort.toLowerCase()) {
            case "name":
                comparator = Comparator.comparing(Student::getName);
                break;
            case "gpa":
                comparator = Comparator.comparing(Student::getGpa);
                break;
            case "id":
            default:
                comparator = Comparator.comparing(Student::getId);
                break;
        }

        if ("desc".equalsIgnoreCase(dir)) {
            comparator = comparator.reversed();
        }
        filteredList.sort(comparator);


        int start = (page - 1) * size;
        int end = Math.min(start + size, filteredList.size());

        if (start > filteredList.size()) {
            return Collections.emptyList();
        }

        return filteredList.subList(start, end);
    }


    public int count(String q) {
        return (int) students.values().stream()
                .filter(s -> q.isEmpty() || s.getId().toLowerCase().contains(q.toLowerCase()) || s.getName().toLowerCase().contains(q.toLowerCase()))
                .count();
    }


    public Optional<Student> findById(String id) {
        return Optional.ofNullable(students.get(id));
    }


    public void deleteById(String id) {
        students.remove(id);
    }

    public boolean existsById(String id) {
        return students.containsKey(id);
    }
}
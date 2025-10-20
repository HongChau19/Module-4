package org.example.ex2.services;

import com.mysql.cj.xdevapi.SessionFactory;
import org.example.ex2.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBStudentService implements IStudentService {
    private
    private static SessionFactory sessionFactory;

    @Override
    public List<Student> getStudents(){
            return List.of();
    }
    @Override
    public void save(Student student){
    }
    }

}

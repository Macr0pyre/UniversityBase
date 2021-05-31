package org.example.DAL.DAO;

import org.example.DAL.models.Student;

import java.util.List;

public interface StudentDAO {
    void add(Student student);

    List<Student> getAll();

    Student getById(Long id);

    Student getBySurname(String surname);

    void update(Student student);

    void delete(Student student);
}

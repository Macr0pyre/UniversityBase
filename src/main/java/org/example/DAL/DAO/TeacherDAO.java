package org.example.DAL.DAO;

import org.example.DAL.models.Teacher;

import java.util.List;

public interface TeacherDAO {
    void add(Teacher teacher);

    List<Teacher> getAll();

    Teacher getById(Long id);

    Teacher getBySurname(String surname);

    void update(Teacher teacher);

    void delete(Teacher teacher);
}

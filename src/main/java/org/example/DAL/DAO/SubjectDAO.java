package org.example.DAL.DAO;

import org.example.DAL.models.Subject;

import java.util.List;

public interface SubjectDAO {
    void add(Subject subject);

    List<Subject> getAll();

    Subject getById(Long id);

    Subject getByName(String subjectName);

    void update(Subject subject);

    void delete(Subject subject);
}

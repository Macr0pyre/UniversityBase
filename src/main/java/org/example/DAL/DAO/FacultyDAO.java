package org.example.DAL.DAO;

import org.example.DAL.models.Faculty;

import java.util.List;

public interface FacultyDAO {
    void add(Faculty faculty);

    List<Faculty> getAll();

    Faculty getById(Long id);

    Faculty getByName(String facultyName);

    void update(Faculty faculty);

    void delete(Faculty faculty);
}

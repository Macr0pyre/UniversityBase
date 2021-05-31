package org.example.DAL.DAO;

import org.example.DAL.models.University;

import java.util.List;

public interface UniversityDAO {
    void add(University university);

    List<University> getAll();

    University getById(Long id);

    University getByName(String uniName);

    void update(University university);

    void delete(University university);
}

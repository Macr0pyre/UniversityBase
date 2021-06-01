package org.example.DAL.DAO;

import org.example.DAL.models.StudentGroup;

import java.util.List;

public interface StudentGroupDAO {
    void add(StudentGroup studentGroup);

    List<StudentGroup> getAll();

    StudentGroup getById(Long id);

    StudentGroup getByName(String groupName);

    void update(StudentGroup studentGroup);

    void delete(StudentGroup studentGroup);
}

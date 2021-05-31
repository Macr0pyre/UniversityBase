package org.example.DAL.DAO;

import org.example.DAL.models.Department;

import java.util.List;

public interface DepartmentDAO {
    void add(Department department);

    List<Department> getAll();

    Department getById(Long id);

    Department getByFacultyId(Long facultyId);

    Department getByName(String departmentName);

    void update(Department department);

    void delete(Department department);
}

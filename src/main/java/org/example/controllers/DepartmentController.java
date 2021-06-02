package org.example.controllers;

import org.example.DAL.DAO.DepartmentDAO;
import org.example.DAL.DAO.FacultyDAO;
import org.example.DAL.DAO.TeacherDAO;
import org.example.DAL.models.Department;
import org.example.DAL.models.Faculty;
import org.example.DAL.models.Teacher;
import org.example.DAL.repositories.DepartmentRepository;
import org.example.DAL.repositories.FacultyRepository;
import org.example.DAL.repositories.TeacherRepository;

import java.util.List;
import java.util.Set;

public class DepartmentController {
    private static DepartmentDAO departmentRepository = new DepartmentRepository();
    private static TeacherDAO teacherRepository = new TeacherRepository();
    private static FacultyDAO facultyRepository = new FacultyRepository();

    public boolean addDepartment(String name, Long facultyID, Long headTeacherID) {
        try {
            Teacher teacher = teacherRepository.getById(headTeacherID);
            Faculty faculty = facultyRepository.getById(facultyID);

            Department department = new Department();
            department.setName(name);
            department.setHead(teacher);
            department.setFaculty(faculty);

            departmentRepository.add(department);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.getAll();
    }

    public Department getDepartmentById(Long departmentId) {
        try {
            return departmentRepository.getById(departmentId);
        } catch (Exception e) {
            return null;
        }
    }

    public Department getDepartmentByName(String departmentName) {
        try {
            return departmentRepository.getByName(departmentName);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateDepartmentName(Long departmentId, String newDepartmentName) {
        try {
            Department department = departmentRepository.getById(departmentId);
            department.setName(newDepartmentName);

            departmentRepository.update(department);;
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean addTeacherToDepartment(Long departmentID, Long teacherId) {
        try {
            Department department = departmentRepository.getById(departmentID);
            Teacher teacher = teacherRepository.getById(teacherId);

            department.addTeacher(teacher);
            departmentRepository.update(department);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeTeacherFromDepartment(Long departmentID, Long teacherId) {
        try {
            Department department = departmentRepository.getById(departmentID);
            Teacher teacher = teacherRepository.getById(teacherId);

            department.removeTeacher(teacher);
            departmentRepository.update(department);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Teacher> getDepartmentTeachers(Long departmentId) {
        try {
            Department department = departmentRepository.getById(departmentId);

            return department.getTeachers();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteDepartment(Long departmentId){
        try{
            Department department = departmentRepository.getById(departmentId);

            departmentRepository.delete(department);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}

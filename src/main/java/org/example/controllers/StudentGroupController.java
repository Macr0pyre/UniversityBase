package org.example.controllers;

import org.example.DAL.DAO.DepartmentDAO;
import org.example.DAL.DAO.StudentGroupDAO;
import org.example.DAL.models.Department;
import org.example.DAL.models.StudentGroup;
import org.example.DAL.repositories.DepartmentRepository;
import org.example.DAL.repositories.StudentGroupRepository;

import java.util.List;

public class StudentGroupController {
    private static StudentGroupDAO studentGroupRepository = new StudentGroupRepository();
    private static DepartmentDAO departmentRepository = new DepartmentRepository();

    public boolean addStudentGroup(String name, Long departmentID) {
        try {
            Department department = departmentRepository.getById(departmentID);

            StudentGroup studentGroup = new StudentGroup();
            studentGroup.setName(name);
            studentGroup.setDepartment(department);

            departmentRepository.add(department);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<StudentGroup> getAllStudentGroups() {
        return studentGroupRepository.getAll();
    }

    public StudentGroup getStudentGroupById(Long id) {
        try {
            return studentGroupRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public StudentGroup getStudentGroupByName(String name) {
        try {
            return studentGroupRepository.getByName(name);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateStudentGroupName(Long studentGroupId, String newStudentGroupName) {
        try {
            StudentGroup studentGroup = studentGroupRepository.getById(studentGroupId);
            studentGroup.setName(newStudentGroupName);

            studentGroupRepository.update(studentGroup);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateStudentGroupDepartment(Long studentGroupId, Long newDepartmentId) {
        try {
            StudentGroup studentGroup = studentGroupRepository.getById(studentGroupId);
            Department department = departmentRepository.getById(newDepartmentId);
            studentGroup.setDepartment(department);

            studentGroupRepository.update(studentGroup);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean deleteStudentGroup(Long studentGroupId) {
        try{
            StudentGroup studentGroup = studentGroupRepository.getById(studentGroupId);

            studentGroupRepository.delete(studentGroup);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
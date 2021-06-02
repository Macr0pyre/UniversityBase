package org.example.controllers;

import org.example.DAL.DAO.CreditDAO;
import org.example.DAL.DAO.ExamDAO;
import org.example.DAL.DAO.StudentDAO;
import org.example.DAL.DAO.StudentGroupDAO;
import org.example.DAL.models.*;
import org.example.DAL.repositories.CreditRepository;
import org.example.DAL.repositories.ExamRepository;
import org.example.DAL.repositories.StudentGroupRepository;
import org.example.DAL.repositories.StudentRepository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class StudentController {
    private static StudentDAO studentRepository = new StudentRepository();
    private static StudentGroupDAO studentGroupRepository  = new StudentGroupRepository();
    private static ExamDAO examRepository = new ExamRepository();
    private static CreditDAO creditRepository = new CreditRepository();

    public boolean addStudent(String surname, String name, String patronymic, String email, Long studentGroupID, String birthdate) {
        try {
            StudentGroup studentGroup = studentGroupRepository.getById(studentGroupID);

            Student student = new Student();
            student.setName(name);
            student.setSurname(surname);
            student.setPatronymic(patronymic);
            student.setEmail(email);
            student.setGroup(studentGroup);
            student.setBirthDate(Date.valueOf(birthdate));

            studentRepository.add(student);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAll();
    }

    public Student getStudentById(Long id) {
        try {
            return studentRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Student getStudentBySurname(String surname) {
        try {
            return studentRepository.getBySurname(surname);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateStudentName(Long studentId, String newStudentName) {
        try {
            Student student = studentRepository.getById(studentId);
            student.setName(newStudentName);

            studentRepository.update(student);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateStudentSurname(Long studentId, String newStudentSurname) {
        try {
            Student student = studentRepository.getById(studentId);
            student.setSurname(newStudentSurname);

            studentRepository.update(student);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateStudentPatronymic(Long studentId, String newStudentPatronymic) {
        try {
            Student student = studentRepository.getById(studentId);
            student.setPatronymic(newStudentPatronymic);

            studentRepository.update(student);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateStudentEmail(Long studentId, String newStudentEmail) {
        try {
            Student student = studentRepository.getById(studentId);
            student.setEmail(newStudentEmail);

            studentRepository.update(student);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateStudentBirthDate(Long studentId, String newStudentBirthDate) {
        try {
            Student student = studentRepository.getById(studentId);
            student.setBirthDate(Date.valueOf(newStudentBirthDate));

            studentRepository.update(student);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateStudentGroup(Long studentId, Long groupId) {
        try {
            Student student = studentRepository.getById(studentId);
            StudentGroup studentGroup = studentGroupRepository.getById(groupId);
            student.setGroup(studentGroup);

            studentRepository.update(student);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean deleteStudent(Long studentId) {
        try {
            Student student = studentRepository.getById(studentId);

            studentRepository.delete(student);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
package org.example.controllers;

import org.example.DAL.DAO.TeacherDAO;
import org.example.DAL.models.Teacher;
import org.example.DAL.repositories.TeacherRepository;

import java.util.List;

public class TeacherController {
    private static TeacherDAO teacherRepository = new TeacherRepository();

    public boolean addTeacher(String surname, String name, String patronymic, String email, String degree, String position) {
        try {
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setSurname(surname);
            teacher.setPatronymic(patronymic);
            teacher.setEmail(email);
            teacher.setDegree(degree);
            teacher.setPosition(position);

            teacherRepository.add(teacher);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.getAll();
    }

    public Teacher getTeacherById(Long id) {
        try {
            return teacherRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Teacher getTeacherBySurname(String surname) {
        try {
            return teacherRepository.getBySurname(surname);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateTeacherName(Long teacherId, String newTeacherName) {
        try {
            Teacher teacher = teacherRepository.getById(teacherId);
            teacher.setName(newTeacherName);

            teacherRepository.update(teacher);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateTeacherSurname(Long teacherId, String newTeacherSurname) {
        try {
            Teacher teacher = teacherRepository.getById(teacherId);
            teacher.setSurname(newTeacherSurname);

            teacherRepository.update(teacher);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateTeacherPatronymic(Long teacherId, String newTeacherPatronymic) {
        try {
            Teacher teacher = teacherRepository.getById(teacherId);
            teacher.setPatronymic(newTeacherPatronymic);

            teacherRepository.update(teacher);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateTeacherEmail(Long teacherId, String newTeacherEmail) {
        try {
            Teacher teacher = teacherRepository.getById(teacherId);
            teacher.setEmail(newTeacherEmail);

            teacherRepository.update(teacher);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateTeacherDegree(Long teacherId, String newTeacherDegree) {
        try {
            Teacher teacher = teacherRepository.getById(teacherId);
            teacher.setDegree(newTeacherDegree);

            teacherRepository.update(teacher);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateTeacherPosition(Long teacherId, String newTeacherPosition) {
        try {
            Teacher teacher = teacherRepository.getById(teacherId);
            teacher.setPosition(newTeacherPosition);

            teacherRepository.update(teacher);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean deleteTeacher(Long teacherId) {
        try {
            Teacher teacher = teacherRepository.getById(teacherId);

            teacherRepository.delete(teacher);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}

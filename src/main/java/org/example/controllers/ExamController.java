package org.example.controllers;

import org.example.DAL.DAO.ExamDAO;
import org.example.DAL.DAO.SubjectDAO;
import org.example.DAL.DAO.TeacherDAO;
import org.example.DAL.models.Credit;
import org.example.DAL.models.Exam;
import org.example.DAL.models.Subject;
import org.example.DAL.models.Teacher;
import org.example.DAL.repositories.ExamRepository;
import org.example.DAL.repositories.SubjectRepository;
import org.example.DAL.repositories.TeacherRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ExamController {
    private static ExamDAO examRepository = new ExamRepository();
    private static TeacherDAO teacherRepository = new TeacherRepository();
    private static SubjectDAO subjectRepository = new SubjectRepository();

    public boolean addExam(String name, Long teacherID, Long subjectID, String date, String time) {
        try {
            Teacher teacher = teacherRepository.getById(teacherID);
            Subject subject = subjectRepository.getById(subjectID);

            Exam exam = new Exam();
            exam.setName(name);
            exam.setTeacher(teacher);
            exam.setSubject(subject);
            exam.setDate(Date.valueOf(date));
            exam.setTime(Time.valueOf(time));

            examRepository.add(exam);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<Exam> getAllExams() {
        return examRepository.getAll();
    }

    public Exam getExamById(Long id) {
        try {
            return examRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Exam getExamByName(String name) {
        try {
            return examRepository.getByName(name);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateExamName(Long examId, String newCreditName) {
        try {
            Exam exam = examRepository.getById(examId);
            exam.setName(newCreditName);

            examRepository.update(exam);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void updateExamTeacher(Long examId, Long newTeacherId) {
        Exam exam = examRepository.getById(examId);
        Teacher teacher = teacherRepository.getById(newTeacherId);
        exam.setTeacher(teacher);

        examRepository.update(exam);
    }

    public void updateExamSubject(Long examId, Long newSubjectId) {
        Exam exam = examRepository.getById(examId);
        Subject subject = subjectRepository.getById(newSubjectId);
        exam.setSubject(subject);

        examRepository.update(exam);
    }

    public void updateExamDate(Long examId, String newDate) {
        Exam exam = examRepository.getById(examId);
        exam.setDate(Date.valueOf(newDate));

        examRepository.update(exam);
    }

    public void updateExamTime(Long examId, String newTime) {
        Exam exam = examRepository.getById(examId);
        exam.setTime(Time.valueOf(newTime));

        examRepository.update(exam);
    }

    public boolean deleteExam(Long examId) {
        try{
            Exam exam = examRepository.getById(examId);

            examRepository.delete(exam);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}

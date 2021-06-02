package org.example.controllers;

import org.example.DAL.DAO.CreditDAO;
import org.example.DAL.DAO.SubjectDAO;
import org.example.DAL.DAO.TeacherDAO;
import org.example.DAL.models.Credit;
import org.example.DAL.models.Exam;
import org.example.DAL.models.Subject;
import org.example.DAL.models.Teacher;
import org.example.DAL.repositories.CreditRepository;
import org.example.DAL.repositories.SubjectRepository;
import org.example.DAL.repositories.TeacherRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class CreditController {
    private static CreditDAO creditRepository = new CreditRepository();
    private static TeacherDAO teacherRepository = new TeacherRepository();
    private static SubjectDAO subjectRepository = new SubjectRepository();

    public boolean addCredit(String name, Long teacherID, Long subjectID, String date, String time) {
        try {
            Teacher teacher = teacherRepository.getById(teacherID);
            Subject subject = subjectRepository.getById(subjectID);

            Credit credit = new Credit();
            credit.setName(name);
            credit.setTeacher(teacher);
            credit.setSubject(subject);
            credit.setDate(Date.valueOf(date));
            credit.setTime(Time.valueOf(time));

            creditRepository.add(credit);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<Credit> getAllCredits() {
        return creditRepository.getAll();
    }

    public Credit getCreditById(Long id) {
        try {
            return creditRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Credit getCreditByName(String name) {
        try {
            return creditRepository.getByName(name);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateCreditName(Long creditId, String newCreditName) {
        try {
            Credit credit = creditRepository.getById(creditId);
            credit.setName(newCreditName);

            creditRepository.update(credit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void updateCreditTeacher(Long creditId, Long newTeacherId) {
        Credit credit = creditRepository.getById(creditId);
        Teacher teacher = teacherRepository.getById(newTeacherId);
        credit.setTeacher(teacher);

        creditRepository.update(credit);
    }

    public void updateCreditSubject(Long creditId, Long newSubjectId) {
        Credit credit = creditRepository.getById(creditId);
        Subject subject = subjectRepository.getById(newSubjectId);
        credit.setSubject(subject);

        creditRepository.update(credit);
    }

    public void updateCreditDate(Long creditId, String newDate) {
        Credit credit = creditRepository.getById(creditId);
        credit.setDate(Date.valueOf(newDate));

        creditRepository.update(credit);
    }

    public void updateCreditTime(Long creditId, String newTime) {
        Credit credit = creditRepository.getById(creditId);
        credit.setTime(Time.valueOf(newTime));

        creditRepository.update(credit);
    }

    public boolean deleteCredit(Long creditId) {
        try{
            Credit credit = creditRepository.getById(creditId);

            creditRepository.delete(credit);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}

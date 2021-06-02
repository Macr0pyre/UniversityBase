package org.example.controllers;

import org.example.DAL.DAO.SubjectDAO;
import org.example.DAL.models.Subject;
import org.example.DAL.repositories.SubjectRepository;

import java.util.List;

public class SubjectController {
    private static SubjectDAO subjectRepository = new SubjectRepository();

    public boolean addSubject(String name) {
        try{
            Subject subjectCheck = subjectRepository.getByName(name);

            return false;
        }
        catch(Exception e) {
            Subject subject = new Subject();
            subject.setName(name);

            subjectRepository.add(subject);
            return true;
        }
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.getAll();
    }

    public Subject getSubjectById(Long id) {
        try {
            return subjectRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Subject getSubjectByName(String name) {
        try {
            return subjectRepository.getByName(name);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateSubjectName(Long subjectId, String newSubjectName) {
        if (subjectRepository.getByName(newSubjectName) != null)
            return false;

        try {
            Subject subject = subjectRepository.getById(subjectId);
            subject.setName(newSubjectName);

            subjectRepository.update(subject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteSubject(Long subjectId) {
        try{
            Subject subject = subjectRepository.getById(subjectId);

            subjectRepository.delete(subject);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean deleteSubject(String subjectName) {
        try{
            Subject subject = subjectRepository.getByName(subjectName);

            subjectRepository.delete(subject);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}

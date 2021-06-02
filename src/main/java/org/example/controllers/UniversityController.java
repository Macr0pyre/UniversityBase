package org.example.controllers;

import org.example.DAL.DAO.UniversityDAO;
import org.example.DAL.models.University;
import org.example.DAL.repositories.UniversityRepository;

import java.util.List;

public class UniversityController {
    private static UniversityDAO universityRepository = new UniversityRepository();

    public boolean addUniversity(String name) {
        try{
            University universityCheck = universityRepository.getByName(name);

            return false;
        }
        catch(Exception e) {
            University university = new University();
            university.setName(name);

            universityRepository.add(university);
            return true;
        }
    }

    public List<University> getAllUniversities() {
        return universityRepository.getAll();
    }

    public University getUniversityById(Long id) {
        try {
            return universityRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public University getUniversityByName(String name) {
        try {
            return universityRepository.getByName(name);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateUniversityName(Long universityId, String newUniversityName) {
        try {
            University university = universityRepository.getById(universityId);
            university.setName(newUniversityName);

            universityRepository.update(university);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteUniversity(Long universityId) {
        try{
            University university = universityRepository.getById(universityId);

            universityRepository.delete(university);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean deleteUniversity(String universityName) {
        try{
            University university = universityRepository.getByName(universityName);

            universityRepository.delete(university);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}

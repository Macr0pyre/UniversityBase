package org.example.controllers;

import org.example.DAL.DAO.FacultyDAO;
import org.example.DAL.DAO.UniversityDAO;
import org.example.DAL.models.Faculty;
import org.example.DAL.models.University;
import org.example.DAL.repositories.FacultyRepository;
import org.example.DAL.repositories.UniversityRepository;

import java.util.List;

public class FacultyController {
    private static FacultyDAO facultyRepository = new FacultyRepository();
    private static UniversityDAO universityRepository = new UniversityRepository();

    public boolean addFaculty(String name, String dean, Long universityId) {
        try {
            University university = universityRepository.getById(universityId);

            Faculty faculty = new Faculty();
            faculty.setName(name);
            faculty.setDean(dean);
            faculty.setUniversity(university);

            facultyRepository.add(faculty);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.getAll();
    }

    public Faculty getFacultyById(Long id) {
        try {
            return facultyRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Faculty getFacultyByName(String name) {
        try {
            return facultyRepository.getByName(name);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateFacultyName(Long facultyId, String newFacultyName) {
        try {
            Faculty faculty = facultyRepository.getById(facultyId);
            faculty.setName(newFacultyName);

            facultyRepository.update(faculty);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void updateFacultyUniversity(Long facultyId, Long newUniversityId) {
        Faculty faculty = facultyRepository.getById(facultyId);
        University university = universityRepository.getById(newUniversityId);
        faculty.setUniversity(university);

        facultyRepository.update(faculty);
    }

    public void updateFacultyDean(Long facultyId, String newDean) {
        Faculty faculty = facultyRepository.getById(facultyId);
        faculty.setDean(newDean);

        facultyRepository.update(faculty);
    }

    public boolean deleteFaculty(Long facultyId) {
        try{
            Faculty faculty = facultyRepository.getById(facultyId);

            facultyRepository.delete(faculty);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}

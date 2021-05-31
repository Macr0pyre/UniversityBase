package org.example.DAL.DAO;

import org.example.DAL.models.Exam;

import java.util.List;

public interface ExamDAO {
    void add(Exam exam);

    List<Exam> getAll();

    Exam getById(Long id);

    Exam getByName(String examName);

    void update(Exam exam);

    void delete(Exam exam);
}

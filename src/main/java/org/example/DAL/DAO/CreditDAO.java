package org.example.DAL.DAO;

import org.example.DAL.models.Credit;

import java.util.List;

public interface CreditDAO {
    void add(Credit credit);

    List<Credit> getAll();

    Credit getById(Long id);

    Credit getByName(String creditName);

    void update(Credit credit);

    void delete(Credit credit);
}

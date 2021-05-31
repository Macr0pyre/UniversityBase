package org.example.DAL.DAO;

import org.example.DAL.models.Schedule;

import java.util.List;

public interface ScheduleDAO {
    void add(Schedule schedule);

    List<Schedule> getAll();

    Schedule getById(Long id);

    void update(Schedule schedule);

    void delete(Schedule schedule);
}

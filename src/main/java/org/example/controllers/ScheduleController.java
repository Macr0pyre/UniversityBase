package org.example.controllers;

import org.example.DAL.DAO.ScheduleDAO;
import org.example.DAL.DAO.StudentGroupDAO;
import org.example.DAL.DAO.SubjectDAO;
import org.example.DAL.DAO.TeacherDAO;
import org.example.DAL.models.Schedule;
import org.example.DAL.models.StudentGroup;
import org.example.DAL.models.Subject;
import org.example.DAL.models.Teacher;
import org.example.DAL.repositories.ScheduleRepository;
import org.example.DAL.repositories.StudentGroupRepository;
import org.example.DAL.repositories.SubjectRepository;
import org.example.DAL.repositories.TeacherRepository;

import java.sql.Time;
import java.util.List;

public class ScheduleController {
    private static ScheduleDAO scheduleRepository = new ScheduleRepository();
    private static StudentGroupDAO studentGroupRepository = new StudentGroupRepository();
    private static SubjectDAO subjectRepository = new SubjectRepository();
    private static TeacherDAO teacherRepository = new TeacherRepository();

    public boolean addSchedule(Long studentGroupID, Long subjectID, Long teacherID, int semester, String startTime, String endTime, String dayOfWeek) {
        try {
            StudentGroup studentGroup = studentGroupRepository.getById(studentGroupID);
            Subject subject = subjectRepository.getById(subjectID);
            Teacher teacher = teacherRepository.getById(teacherID);

            Schedule schedule = new Schedule();
            schedule.setGroup(studentGroup);
            schedule.setSubject(subject);
            schedule.setTeacher(teacher);
            schedule.setSemester(semester);
            schedule.setStartTime(Time.valueOf(startTime));
            schedule.setEndTime(Time.valueOf(endTime));
            schedule.setDayOfWeek(dayOfWeek);

            scheduleRepository.add(schedule);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.getAll();
    }

    public Schedule getScheduleById(Long id) {
        try {
            return scheduleRepository.getById(id);
        }
        catch (Exception e) {
            return null;
        }
    }

    public void updateScheduleStudentGroup(Long scheduleId, Long newStudentGroupId) {
        Schedule schedule = scheduleRepository.getById(scheduleId);
        StudentGroup studentGroup = studentGroupRepository.getById(newStudentGroupId);
        schedule.setGroup(studentGroup);

        scheduleRepository.update(schedule);
    }

    public void updateScheduleSubject(Long scheduleId, Long newSubjectId) {
        Schedule schedule = scheduleRepository.getById(scheduleId);
        Subject subject = subjectRepository.getById(newSubjectId);
        schedule.setSubject(subject);

        scheduleRepository.update(schedule);
    }

    public void updateScheduleTeacher(Long scheduleId, Long newTeacherId) {
        Schedule schedule = scheduleRepository.getById(scheduleId);
        Teacher teacher = teacherRepository.getById(newTeacherId);
        schedule.setTeacher(teacher);

        scheduleRepository.update(schedule);
    }

    public void updateScheduleStartTime(Long scheduleId, String newStartTime) {
        Schedule schedule = scheduleRepository.getById(scheduleId);
        schedule.setStartTime(Time.valueOf(newStartTime));

        scheduleRepository.update(schedule);
    }

    public void updateScheduleEndTime(Long scheduleId, String newEndTime) {
        Schedule schedule = scheduleRepository.getById(scheduleId);
        schedule.setEndTime(Time.valueOf(newEndTime));

        scheduleRepository.update(schedule);
    }

    public void updateScheduleSemester(Long scheduleId, int newSemester) {
        Schedule schedule = scheduleRepository.getById(scheduleId);
        schedule.setSemester(newSemester);

        scheduleRepository.update(schedule);
    }

    public void updateScheduleDayOfWeek(Long scheduleId, String newDayOfWeek) {
        Schedule schedule = scheduleRepository.getById(scheduleId);
        schedule.setDayOfWeek(newDayOfWeek);

        scheduleRepository.update(schedule);
    }

    public boolean deleteSchedule(Long scheduleId) {
        try{
            Schedule schedule = scheduleRepository.getById(scheduleId);

            scheduleRepository.delete(schedule);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}

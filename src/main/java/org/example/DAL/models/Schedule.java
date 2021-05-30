package org.example.DAL.models;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Group group;

    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;

    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    private int semester;

    private Time startTime;

    private Time endTime;

    private String dayOfWeek;

    public Schedule() {
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return semester == schedule.semester && Objects.equals(id, schedule.id) && Objects.equals(group, schedule.group) && Objects.equals(subject, schedule.subject) && Objects.equals(teacher, schedule.teacher) && Objects.equals(startTime, schedule.startTime) && Objects.equals(endTime, schedule.endTime) && Objects.equals(dayOfWeek, schedule.dayOfWeek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group, subject, teacher, semester, startTime, endTime, dayOfWeek);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

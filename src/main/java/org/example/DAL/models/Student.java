package org.example.DAL.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Entity
public class Student extends Person{
    @ManyToOne(cascade = CascadeType.ALL)
    private StudentGroup studentGroup;

    private Date birthDate;

    @ElementCollection
    @JoinTable(name = "exam_achievements",
            joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "exam_id")
    @Column(name = "mark")
    private Map<Exam, Integer> examMarked;

    @ElementCollection
    @JoinTable(name = "credit_achievements",
            joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "credit_id")
    @Column(name = "mark")
    private Map<Credit, Boolean> creditMarked;

    public Student() {
    }

    public StudentGroup getGroup() {
        return studentGroup;
    }

    public void setGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Map<Exam, Integer> getExamMarked() {
        return examMarked;
    }

    public void setExamMarked(Map<Exam, Integer> examMarked) {
        this.examMarked = examMarked;
    }

    public Map<Credit, Boolean> getCreditMarked() {
        return creditMarked;
    }

    public void setCreditMarked(Map<Credit, Boolean> creditMarked) {
        this.creditMarked = creditMarked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(studentGroup, student.studentGroup) && Objects.equals(birthDate, student.birthDate) && Objects.equals(examMarked, student.examMarked) && Objects.equals(creditMarked, student.creditMarked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentGroup, birthDate, examMarked, creditMarked);
    }
}

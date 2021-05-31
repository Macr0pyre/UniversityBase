package org.example;

import org.example.DAL.models.*;
import org.example.util.HibernateUtil;
import org.example.util.StringHandler;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Application {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        loadData(sessionFactory.openSession());

        HibernateUtil.shutdown();

    }

    private static void loadData(Session session){

        final Transaction transaction = session.beginTransaction();

        University university1 = new University();
        university1.setName("СГУ");

        University university2 = new University();
        university2.setName("СГТУ");

        Faculty faculty1 = new Faculty();
        faculty1.setName("Компьютерных наук и информационных технологий");
        faculty1.setDean("Юдакова Ольга Ивановна");
        faculty1.setUniversity(university1);

        Faculty faculty2 = new Faculty();
        faculty2.setName("ИНСТИТУТ ЭНЕРГЕТИКИ И ТРАНСПОРТНЫХ СИСТЕМ");
        faculty2.setDean("Пупкин Василий Петрович");
        faculty2.setUniversity(university2);

        Teacher teacher1 = new Teacher();
        teacher1.setSurname("Попов");
        teacher1.setName("Максим");
        teacher1.setPatronymic("Павлович");
        teacher1.setDegree("Кандидат технических наук");
        teacher1.setEmail("popov@gmail.com");
        teacher1.setPosition("Старший преподаватель");

        Teacher teacher2 = new Teacher();
        teacher2.setSurname("Семенова");
        teacher2.setName("Марина");
        teacher2.setPatronymic("Алексеевна");
        //teacher2.setDegree("Кандидат технических наук");
        teacher2.setEmail("semenovama@mail.ru");
        teacher2.setPosition("Преподаватель");

        Teacher teacher3 = new Teacher();
        teacher3.setSurname("Елочкина");
        teacher3.setName("Светлана");
        teacher3.setPatronymic("Владимировна");
        //teacher3.setDegree("Кандидат технических наук");
        teacher3.setEmail("elochka@mail.com");
        teacher3.setPosition("Старший преподаватель");

        Department department1 = new Department();
        department1.setName("Кафедра информатики и программирования");
        department1.setFaculty(faculty1);
        department1.setHead(teacher1);
        Set<Teacher> teachersForDep1 = new HashSet<>();
        teachersForDep1.add(teacher1);
        teachersForDep1.add(teacher3);
        department1.setTeachers(teachersForDep1);

        Department department2 = new Department();
        department2.setName("Кафедра управления транспортом");
        department2.setFaculty(faculty2);
        department2.setHead(teacher2);
        Set<Teacher> teachersForDep2 = new HashSet<>();
        teachersForDep2.add(teacher2);
        department2.setTeachers(teachersForDep2);

        StudentGroup studentGroup1 = new StudentGroup();
        studentGroup1.setName("341");
        studentGroup1.setDepartment(department1);

        StudentGroup studentGroup2 = new StudentGroup();
        studentGroup2.setName("122a");
        studentGroup2.setDepartment(department2);

        Subject subject1 = new Subject();
        subject1.setName("Информатика и программирование");

        Subject subject2 = new Subject();
        subject2.setName("Философия");

        Subject subject3 = new Subject();
        subject3.setName("Теория вероятностей");

        Exam exam1 = new Exam();
        exam1.setName("Экзамен по информатике и программированию");
        exam1.setTeacher(teacher1);
        exam1.setSubject(subject1);
        exam1.setDate(Date.valueOf("2021-6-2"));
        exam1.setTime(Time.valueOf("10:00:00"));

        Exam exam2 = new Exam();
        exam2.setName("Экзамен по философии");
        exam2.setTeacher(teacher2);
        exam2.setSubject(subject2);
        exam2.setDate(Date.valueOf("2021-6-12"));
        exam2.setTime(Time.valueOf("9:00:00"));

        Credit credit1 = new Credit();
        credit1.setName("Зачет по философии");
        credit1.setTeacher(teacher2);
        credit1.setSubject(subject2);
        credit1.setDate(Date.valueOf("2021-5-23"));
        credit1.setTime(Time.valueOf("13:50:00"));

        Credit credit2 = new Credit();
        credit2.setName("Зачет по теории вероятностей");
        credit2.setTeacher(teacher3);
        credit2.setSubject(subject3);
        credit2.setDate(Date.valueOf("2021-5-27"));
        credit2.setTime(Time.valueOf("10:00:00"));

        Student student1 = new Student();
        student1.setSurname("Андреева");
        student1.setName("Ирина");
        student1.setPatronymic("Викторовна");
        student1.setGroup(studentGroup1);
        student1.setEmail("iraengels2000@gmail.com");
        student1.setBirthDate(Date.valueOf("2000-7-24"));
        Map<Exam, Integer> examsStudent1 = new HashMap<>();
        examsStudent1.put(exam1, 5);
        student1.setExamMarked(examsStudent1);
        Map<Credit, Boolean> creditsStudent1 = new HashMap<>();
        creditsStudent1.put(credit1, true);
        student1.setCreditMarked(creditsStudent1);

        Student student2 = new Student();
        student2.setSurname("Жуков");
        student2.setName("Михаил");
        student2.setPatronymic("Георгиевич");
        student2.setGroup(studentGroup2);
        student2.setEmail("cshuk@mail.ru");
        student2.setBirthDate(Date.valueOf("2001-1-30"));
        Map<Exam, Integer> examsStudent2 = new HashMap<>();
        examsStudent2.put(exam2, 4);
        student2.setExamMarked(examsStudent2);
        Map<Credit, Boolean> creditsStudent2 = new HashMap<>();
        creditsStudent2.put(credit2, true);
        student2.setCreditMarked(creditsStudent2);

        Student student3 = new Student();
        student3.setSurname("Боб");
        student3.setName("Рауль");
        student3.setPatronymic("Фазиевич");
        student3.setGroup(studentGroup1);
        student3.setEmail("bob128@mail.ru");
        student3.setBirthDate(Date.valueOf("2000-11-15"));
        Map<Exam, Integer> examsStudent3 = new HashMap<>();
        examsStudent3.put(exam1, 3);
        student3.setExamMarked(examsStudent3);
        Map<Credit, Boolean> creditsStudent3 = new HashMap<>();
        creditsStudent3.put(credit1, false);
        student3.setCreditMarked(creditsStudent3);

        Schedule schedule1 = new Schedule();
        schedule1.setGroup(studentGroup1);
        schedule1.setSubject(subject1);
        schedule1.setTeacher(teacher1);
        schedule1.setSemester(5);
        schedule1.setStartTime(Time.valueOf("8:20:00"));
        schedule1.setEndTime(Time.valueOf("10:00:00"));
        schedule1.setDayOfWeek("Вторник");

        Schedule schedule2 = new Schedule();
        schedule2.setGroup(studentGroup1);
        schedule2.setSubject(subject3);
        schedule2.setTeacher(teacher3);
        schedule2.setSemester(5);
        schedule2.setStartTime(Time.valueOf("10:00:00"));
        schedule2.setEndTime(Time.valueOf("11:35:00"));
        schedule2.setDayOfWeek("Пятница");

        Schedule schedule3 = new Schedule();
        schedule3.setGroup(studentGroup2);
        schedule3.setSubject(subject2);
        schedule3.setTeacher(teacher2);
        schedule3.setSemester(2);
        schedule3.setStartTime(Time.valueOf("12:05:00"));
        schedule3.setEndTime(Time.valueOf("13:40:00"));
        schedule3.setDayOfWeek("Среда");

        DBUser user = new DBUser();
        user.setEmail("mail@mail.ru");
        user.setPassword(StringHandler.encryptString("1234"));

        session.save(university1);
        session.save(university2);
        session.save(faculty1);
        session.save(faculty2);
        session.save(teacher1);
        session.save(teacher2);
        session.save(teacher3);
        session.save(department1);
        session.save(department2);
        session.save(studentGroup1);
        session.save(studentGroup2);
        session.save(subject1);
        session.save(subject2);
        session.save(subject3);
        session.save(exam1);
        session.save(exam2);
        session.save(credit1);
        session.save(credit2);
        session.save(student1);
        session.save(student2);
        session.save(student3);
        session.save(schedule1);
        session.save(schedule2);
        session.save(schedule3);
        session.save(user);

        transaction.commit();
        session.close();
    }
}

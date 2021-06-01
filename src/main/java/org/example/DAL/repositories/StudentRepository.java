package org.example.DAL.repositories;

import org.example.DAL.DAO.StudentDAO;
import org.example.DAL.models.Faculty;
import org.example.DAL.models.Faculty_;
import org.example.DAL.models.Student;
import org.example.DAL.models.Student_;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentRepository implements StudentDAO {
    @Override
    public void add(Student student) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Student> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = query.from(Student.class);
        query.select(studentRoot);

        List<Student> resultList = session.createQuery(query).getResultList();

        transaction.commit();
        session.close();

        return resultList;
    }

    @Override
    public Student getById(Long id) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = query.from(Student.class);
        query.where(criteriaBuilder.equal(studentRoot.get(Student_.id), id));
        query.select(studentRoot);

        Student student = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return student;
    }

    @Override
    public Student getBySurname(String surname) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = query.from(Student.class);
        query.where(criteriaBuilder.equal(studentRoot.get(Student_.surname), surname));
        query.select(studentRoot);

        Student student = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return student;
    }

    @Override
    public void update(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Student student) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(student);

        transaction.commit();
        session.close();
    }
}

package org.example.DAL.repositories;

import org.example.DAL.DAO.TeacherDAO;
import org.example.DAL.models.Subject;
import org.example.DAL.models.Subject_;
import org.example.DAL.models.Teacher;
import org.example.DAL.models.Teacher_;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeacherRepository implements TeacherDAO {
    @Override
    public void add(Teacher teacher) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(teacher);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Teacher> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> teacherRoot = query.from(Teacher.class);
        query.select(teacherRoot);

        List<Teacher> resultList = session.createQuery(query).getResultList();

        transaction.commit();
        session.close();

        return resultList;
    }

    @Override
    public Teacher getById(Long id) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> teacherRoot = query.from(Teacher.class);
        query.where(criteriaBuilder.equal(teacherRoot.get(Teacher_.id), id));
        query.select(teacherRoot);

        Teacher teacher = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return teacher;
    }

    @Override
    public Teacher getBySurname(String surname) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> teacherRoot = query.from(Teacher.class);
        query.where(criteriaBuilder.equal(teacherRoot.get(Teacher_.surname), surname));
        query.select(teacherRoot);

        Teacher teacher = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return teacher;
    }

    @Override
    public void update(Teacher teacher) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(teacher);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Teacher teacher) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(teacher);

        transaction.commit();
        session.close();
    }
}

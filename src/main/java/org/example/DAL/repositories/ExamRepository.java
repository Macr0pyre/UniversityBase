package org.example.DAL.repositories;

import org.example.DAL.DAO.ExamDAO;
import org.example.DAL.models.Department;
import org.example.DAL.models.Department_;
import org.example.DAL.models.Exam;
import org.example.DAL.models.Exam_;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExamRepository implements ExamDAO {
    @Override
    public void add(Exam exam) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(exam);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Exam> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Exam> query = criteriaBuilder.createQuery(Exam.class);
        Root<Exam> examRoot = query.from(Exam.class);
        query.select(examRoot);

        List<Exam> resultList = session.createQuery(query).getResultList();

        transaction.commit();
        session.close();

        return resultList;
    }

    @Override
    public Exam getById(Long id) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Exam> query = criteriaBuilder.createQuery(Exam.class);
        Root<Exam> examRoot = query.from(Exam.class);
        query.where(criteriaBuilder.equal(examRoot.get(Exam_.id), id));
        query.select(examRoot);

        Exam exam = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return exam;
    }

    @Override
    public Exam getByName(String examName) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Exam> query = criteriaBuilder.createQuery(Exam.class);
        Root<Exam> examRoot = query.from(Exam.class);
        query.where(criteriaBuilder.equal(examRoot.get(Exam_.name), examName));
        query.select(examRoot);

        Exam exam = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return exam;
    }

    @Override
    public void update(Exam exam) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(exam);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Exam exam) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(exam);

        transaction.commit();
        session.close();
    }
}

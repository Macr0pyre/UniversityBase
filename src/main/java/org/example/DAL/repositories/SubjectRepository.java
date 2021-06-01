package org.example.DAL.repositories;

import org.example.DAL.DAO.SubjectDAO;
import org.example.DAL.models.Student;
import org.example.DAL.models.Student_;
import org.example.DAL.models.Subject;
import org.example.DAL.models.Subject_;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class SubjectRepository implements SubjectDAO {
    @Override
    public void add(Subject subject) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(subject);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Subject> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Subject> query = criteriaBuilder.createQuery(Subject.class);
        Root<Subject> subjectRoot = query.from(Subject.class);
        query.select(subjectRoot);

        List<Subject> resultList = session.createQuery(query).getResultList();

        transaction.commit();
        session.close();

        return resultList;
    }

    @Override
    public Subject getById(Long id) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Subject> query = criteriaBuilder.createQuery(Subject.class);
        Root<Subject> subjectRoot = query.from(Subject.class);
        query.where(criteriaBuilder.equal(subjectRoot.get(Subject_.id), id));
        query.select(subjectRoot);

        Subject subject = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return subject;
    }

    @Override
    public Subject getByName(String subjectName) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Subject> query = criteriaBuilder.createQuery(Subject.class);
        Root<Subject> subjectRoot = query.from(Subject.class);
        query.where(criteriaBuilder.equal(subjectRoot.get(Subject_.name), subjectName));
        query.select(subjectRoot);

        Subject subject = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return subject;
    }

    @Override
    public void update(Subject subject) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(subject);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Subject subject) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(subject);

        transaction.commit();
        session.close();
    }
}

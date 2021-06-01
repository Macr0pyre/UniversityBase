package org.example.DAL.repositories;

import org.example.DAL.DAO.UniversityDAO;
import org.example.DAL.models.Teacher;
import org.example.DAL.models.Teacher_;
import org.example.DAL.models.University;
import org.example.DAL.models.University_;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UniversityRepository implements UniversityDAO {
    @Override
    public void add(University university) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(university);

        transaction.commit();
        session.close();
    }

    @Override
    public List<University> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<University> query = criteriaBuilder.createQuery(University.class);
        Root<University> universityRoot = query.from(University.class);
        query.select(universityRoot);

        List<University> resultList = session.createQuery(query).getResultList();

        transaction.commit();
        session.close();

        return resultList;
    }

    @Override
    public University getById(Long id) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<University> query = criteriaBuilder.createQuery(University.class);
        Root<University> universityRoot = query.from(University.class);
        query.where(criteriaBuilder.equal(universityRoot.get(University_.id), id));
        query.select(universityRoot);

        University university = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return university;
    }

    @Override
    public University getByName(String uniName) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<University> query = criteriaBuilder.createQuery(University.class);
        Root<University> universityRoot = query.from(University.class);
        query.where(criteriaBuilder.equal(universityRoot.get(University_.name), uniName));
        query.select(universityRoot);

        University university = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return university;
    }

    @Override
    public void update(University university) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(university);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(University university) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(university);

        transaction.commit();
        session.close();
    }
}

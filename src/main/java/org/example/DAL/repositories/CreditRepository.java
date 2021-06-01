package org.example.DAL.repositories;

import org.example.DAL.DAO.CreditDAO;
import org.example.DAL.models.Credit;
import org.example.DAL.models.Credit_;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CreditRepository implements CreditDAO {
    @Override
    public void add(Credit credit) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(credit);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Credit> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Credit> query = criteriaBuilder.createQuery(Credit.class);
        Root<Credit> creditRoot = query.from(Credit.class);
        query.select(creditRoot);

        List<Credit> resultList = session.createQuery(query).getResultList();

        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public Credit getById(Long id) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Credit> query = criteriaBuilder.createQuery(Credit.class);
        Root<Credit> creditRoot = query.from(Credit.class);
        query.where(criteriaBuilder.equal(creditRoot.get(Credit_.id), id));
        query.select(creditRoot);

        Credit credit = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return credit;
    }

    @Override
    public Credit getByName(String creditName) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Credit> query = criteriaBuilder.createQuery(Credit.class);
        Root<Credit> creditRoot = query.from(Credit.class);
        query.where(criteriaBuilder.equal(creditRoot.get(Credit_.name), creditName));
        query.select(creditRoot);

        Credit credit = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return credit;
    }

    @Override
    public void update(Credit credit) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(credit);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Credit credit) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(credit);

        transaction.commit();
        session.close();
    }
}

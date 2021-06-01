package org.example.DAL.repositories;

import org.example.DAL.DAO.FacultyDAO;
import org.example.DAL.models.Exam;
import org.example.DAL.models.Exam_;
import org.example.DAL.models.Faculty;
import org.example.DAL.models.Faculty_;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class FacultyRepository implements FacultyDAO {
    @Override
    public void add(Faculty faculty) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(faculty);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Faculty> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Faculty> query = criteriaBuilder.createQuery(Faculty.class);
        Root<Faculty> facultyRoot = query.from(Faculty.class);
        query.select(facultyRoot);

        List<Faculty> resultList = session.createQuery(query).getResultList();

        transaction.commit();
        session.close();

        return resultList;
    }

    @Override
    public Faculty getById(Long id) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Faculty> query = criteriaBuilder.createQuery(Faculty.class);
        Root<Faculty> facultyRoot = query.from(Faculty.class);
        query.where(criteriaBuilder.equal(facultyRoot.get(Faculty_.id), id));
        query.select(facultyRoot);

        Faculty faculty = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return faculty;
    }

    @Override
    public Faculty getByName(String facultyName) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Faculty> query = criteriaBuilder.createQuery(Faculty.class);
        Root<Faculty> facultyRoot = query.from(Faculty.class);
        query.where(criteriaBuilder.equal(facultyRoot.get(Faculty_.name), facultyName));
        query.select(facultyRoot);

        Faculty faculty = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return faculty;
    }

    @Override
    public void update(Faculty faculty) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(faculty);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Faculty faculty) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(faculty);

        transaction.commit();
        session.close();
    }
}

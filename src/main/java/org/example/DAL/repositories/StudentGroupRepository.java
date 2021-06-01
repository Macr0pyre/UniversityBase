package org.example.DAL.repositories;

import org.example.DAL.DAO.StudentGroupDAO;
import org.example.DAL.models.StudentGroup;
import org.example.DAL.models.StudentGroup_;
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

public class StudentGroupRepository implements StudentGroupDAO {
    @Override
    public void add(StudentGroup studentGroup) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(studentGroup);

        transaction.commit();
        session.close();
    }

    @Override
    public List<StudentGroup> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StudentGroup> query = criteriaBuilder.createQuery(StudentGroup.class);
        Root<StudentGroup> studentGroupRoot = query.from(StudentGroup.class);
        query.select(studentGroupRoot);

        List<StudentGroup> resultList = session.createQuery(query).getResultList();

        transaction.commit();
        session.close();

        return resultList;
    }

    @Override
    public StudentGroup getById(Long id) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StudentGroup> query = criteriaBuilder.createQuery(StudentGroup.class);
        Root<StudentGroup> studentGroupRoot = query.from(StudentGroup.class);
        query.where(criteriaBuilder.equal(studentGroupRoot.get(StudentGroup_.id), id));
        query.select(studentGroupRoot);

        StudentGroup studentGroup = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return studentGroup;
    }

    @Override
    public StudentGroup getByName(String groupName) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StudentGroup> query = criteriaBuilder.createQuery(StudentGroup.class);
        Root<StudentGroup> studentGroupRoot = query.from(StudentGroup.class);
        query.where(criteriaBuilder.equal(studentGroupRoot.get(StudentGroup_.name), groupName));
        query.select(studentGroupRoot);

        StudentGroup studentGroup = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return studentGroup;
    }

    @Override
    public void update(StudentGroup studentGroup) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(studentGroup);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(StudentGroup studentGroup) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(studentGroup);

        transaction.commit();
        session.close();
    }
}

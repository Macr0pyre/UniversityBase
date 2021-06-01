package org.example.DAL.repositories;

import org.example.DAL.DAO.DepartmentDAO;
import org.example.DAL.models.Credit;
import org.example.DAL.models.Credit_;
import org.example.DAL.models.Department;
import org.example.DAL.models.Department_;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DepartmentRepository implements DepartmentDAO {
    @Override
    public void add(Department department) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(department);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Department> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = criteriaBuilder.createQuery(Department.class);
        Root<Department> departmentRoot = query.from(Department.class);
        query.select(departmentRoot);

        List<Department> resultList = session.createQuery(query).getResultList();

        transaction.commit();
        session.close();

        return resultList;
    }

    @Override
    public Department getById(Long id) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = criteriaBuilder.createQuery(Department.class);
        Root<Department> departmentRoot = query.from(Department.class);
        query.where(criteriaBuilder.equal(departmentRoot.get(Department_.id), id));
        query.select(departmentRoot);

        Department department = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return department;
    }

    @Override
    public Department getByName(String departmentName) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = criteriaBuilder.createQuery(Department.class);
        Root<Department> departmentRoot = query.from(Department.class);
        query.where(criteriaBuilder.equal(departmentRoot.get(Department_.name), departmentName));
        query.select(departmentRoot);

        Department department = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return department;
    }

    @Override
    public void update(Department department) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(department);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Department department) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(department);

        transaction.commit();
        session.close();
    }
}

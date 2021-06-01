package org.example.DAL.repositories;

import org.example.DAL.DAO.ScheduleDAO;
import org.example.DAL.models.Schedule;
import org.example.DAL.models.Schedule_;
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

public class ScheduleRepository implements ScheduleDAO {
    @Override
    public void add(Schedule schedule) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(schedule);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Schedule> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Schedule> query = criteriaBuilder.createQuery(Schedule.class);
        Root<Schedule> scheduleRoot = query.from(Schedule.class);
        query.select(scheduleRoot);

        List<Schedule> resultList = session.createQuery(query).getResultList();

        transaction.commit();
        session.close();

        return resultList;
    }

    @Override
    public Schedule getById(Long id) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Schedule> query = criteriaBuilder.createQuery(Schedule.class);
        Root<Schedule> scheduleRoot = query.from(Schedule.class);
        query.where(criteriaBuilder.equal(scheduleRoot.get(Schedule_.id), id));
        query.select(scheduleRoot);

        Schedule schedule = session.createQuery(query).getSingleResult();

        transaction.commit();
        session.close();

        return schedule;
    }

    @Override
    public void update(Schedule schedule) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(schedule);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Schedule schedule) throws NoResultException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(schedule);

        transaction.commit();
        session.close();
    }
}

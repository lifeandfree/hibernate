package ru.innopolis.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.innopolis.entity.Instructor;
import ru.innopolis.util.HibernateUtil;

import java.util.List;

public class InstructorDao {
    public void saveInstructor(Instructor instructor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(instructor);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Instructor> getInstructors() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Instructor", Instructor.class).list();
        }
    }
}

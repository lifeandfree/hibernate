package ru.innopolis.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.innopolis.entity.Student;
import ru.innopolis.util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class StudentDao {
    public void saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(student);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Student> getStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }

    public void insertStudent() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            String hql = "INSERT INTO Student (firstName, lastName, email) SELECT firstName, lastName, email FROM Student";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void deleteStudent(Student student) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
/*       Object mergeStudent = session.merge(student);
      session.remove(mergeStudent);*/
            final Query sqlQuery = session.createSQLQuery("DELETE FROM student WHERE id=" + student.getId());
            int result = sqlQuery.executeUpdate();
            System.out.println("Rows affected: " + result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}

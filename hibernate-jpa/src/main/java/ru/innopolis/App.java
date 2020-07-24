package ru.innopolis;

import ru.innopolis.util.JPAUtil;

import javax.persistence.EntityManager;

public class App {
    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Student student = new Student("Ivan", "Bunin", "ivanbu@javaguides.com");
        System.out.println(student);
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();

        student.setLastName("Ivan");
        System.out.println(student);

        EntityManager entityManager1 = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager1.getTransaction().begin();

        entityManager1.merge(student);
        entityManager1.getTransaction().commit();
        entityManager1.close();

        EntityManager entityManager2 = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager2.getTransaction().begin();

        System.out.println("=====");
        final Student student1 = entityManager2.getReference(Student.class, student.getId());
        System.out.println("=====");

        entityManager2.getTransaction().commit();
        System.out.println(student1);
        entityManager2.close();

        JPAUtil.shutdown();
    }

}

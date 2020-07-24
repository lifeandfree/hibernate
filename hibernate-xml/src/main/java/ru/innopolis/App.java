package ru.innopolis;

import ru.innopolis.dao.StudentDao;
import ru.innopolis.entity.Student;
import ru.innopolis.util.HibernateUtil;

import java.util.List;

public class App {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        Student student = new Student("Ivan", "Bunin", "ivanbu@javaguides.com");
        studentDao.saveStudent(student);

        List<Student> students = studentDao.getStudents();
        students.forEach(s -> System.out.println(s.getFirstName()));

        HibernateUtil.shutdown();
    }
}

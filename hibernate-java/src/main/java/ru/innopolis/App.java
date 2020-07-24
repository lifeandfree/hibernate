package ru.innopolis;


import ru.innopolis.dao.CourseDao;
import ru.innopolis.dao.InstructorDao;
import ru.innopolis.dao.StudentDao;
import ru.innopolis.entity.Course;
import ru.innopolis.entity.Instructor;
import ru.innopolis.entity.InstructorDetail;
import ru.innopolis.entity.Student;

import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {

        //create some instructor
        InstructorDao instructorDao = new InstructorDao();
        Instructor instructor = new Instructor("Ernesto", "Guevara", "ergu@javaguides.com");
        InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Guitar");
        instructorDetail.setInstructor(instructor);
        instructor.setInstructorDetail(instructorDetail);

        // create some courses
        CourseDao courseDao = new CourseDao();

        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        tempCourse1.setInstructor(instructor);

        Course tempCourse2 = new Course("The Pinball Masterclass");
        tempCourse2.setInstructor(instructor);

        courseDao.saveCourse(tempCourse1);
        courseDao.saveCourse(tempCourse2);

        //create some students
        StudentDao studentDao = new StudentDao();

        Student tempStudent1 = new Student("Ivan", "Bunin", "ivanbu@javaguides.com");
        tempStudent1.setCourses(Collections.singletonList(tempCourse1));
        studentDao.saveStudent(tempStudent1);

        Student tempStudent2 = new Student("Matvey", "Mashin", "mama@javaguides.com");
        tempStudent2.setCourses(Collections.singletonList(tempCourse2));
        studentDao.saveStudent(tempStudent2);


        studentDao.insertStudent();
//        studentDao.deleteStudent(tempStudent1);

        List<Student> students = studentDao.getStudents();
        System.out.println("==========================================");
        students.forEach(System.out::println);
        System.out.println("==========================================");
        List<Instructor> instructors = instructorDao.getInstructors();
        System.out.println("==========================================");
        instructors.forEach(System.out::println);
        System.out.println("==========================================");

    }
}

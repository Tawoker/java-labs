package lab2.registration;

import lab2.registration.model.CourseInstance;
import lab2.registration.model.SubscribedStudent;
import lab2.registration.service.DataPreparationForServices;
import lab2.registration.service.StudentServiceImplement;
import lab2.registration.service.CourseInstructorServiceImplement;
import lab2.registration.model.CourseInstructor;

public class Main {
    public static void main(String args[]) throws Exception {
        DataPreparationForServices data = new DataPreparationForServices();
        StudentServiceImplement studentService = new StudentServiceImplement(data.getCoursesInfo(),
                data.getCoursesWithSubs(),
                data.getSubscribedStudents(),
                data.getCourseInstructor());
        CourseInstructorServiceImplement courseInstructorService = new CourseInstructorServiceImplement(data.getCoursesInfo(),
                data.getCoursesWithSubs(),
                data.getSubscribedStudents(),
                data.getCourseInstructor());
        studentService.subscribe(102, 100002);
        studentService.subscribe(103, 100002);
        studentService.subscribe(104, 100002);
        studentService.unsubscribe(103, 100002);
        SubscribedStudent[] instructorStudents = courseInstructorService.findStudentsByInstructorId(9002);
        CourseInstance[] allSubs102Student = studentService.findAllSubscriptionsByStudentId(102);
        SubscribedStudent[] studentsOn100002Course = courseInstructorService.findStudentsByCourseId(100002);
        CourseInstructor[] replaceFor9002 = courseInstructorService.findReplacement(9002, 20935);
    }
}

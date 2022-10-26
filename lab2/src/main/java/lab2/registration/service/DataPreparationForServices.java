package lab2.registration.service;

import lab2.registration.model.*;
import lab2.registration.reader.CourseDataReader;
import lab2.registration.reader.InstructorDataReader;
import lab2.registration.reader.StudentDataReader;

import java.io.IOException;

public class DataPreparationForServices {

    private CourseInfo[] coursesInfo;

    private CourseInstanceWithSubscribe[] coursesWithSubs;

    private SubscribedStudent[] subscribedStudents;

    private CourseInstructor[] courseInstructors;


    public DataPreparationForServices() throws IOException {
        courseInstructors = new InstructorDataReader().readCourseInstructorData();
        coursesInfo = new CourseDataReader().readCourseInfoData();
        coursesWithSubs = new CourseDataReader().readCourseInstanceWithSubscribe();

        SubscribedStudent[] bachelorsArray = new StudentDataReader().readBachelorStudentData();
        SubscribedStudent[] mastersArray = new StudentDataReader().readMasterStudentData();

        subscribedStudents = new SubscribedStudent[bachelorsArray.length + mastersArray.length];

        for(int i = 0; i < bachelorsArray.length; i++) {
            subscribedStudents[i] = bachelorsArray[i];
            subscribedStudents[i].setStudentCategory(StudentCategory.BACHELOR);
        }

        for (int i = bachelorsArray.length; i < mastersArray.length + bachelorsArray.length; i++) {
            subscribedStudents[i] = mastersArray[i - bachelorsArray.length];
            subscribedStudents[i].setStudentCategory(StudentCategory.MASTER);
        }

        for (int i = 0; i < courseInstructors.length; i++)
            courseInstructors[i].addAllTeachingCourses(coursesWithSubs);
    }

    public CourseInfo[] getCoursesInfo() {
        return coursesInfo;
    }

    public void setCoursesInfo(CourseInfo[] coursesInfo) {
        this.coursesInfo = coursesInfo;
    }

    public CourseInstanceWithSubscribe[] getCoursesWithSubs() {
        return coursesWithSubs;
    }

    public void setCoursesWithSubs(CourseInstanceWithSubscribe[] coursesWithSubs) {
        this.coursesWithSubs = coursesWithSubs;
    }

    public SubscribedStudent[] getSubscribedStudents() {
        return subscribedStudents;
    }

    public void setSubscribedStudents(SubscribedStudent[] subscribedStudents) {
        this.subscribedStudents = subscribedStudents;
    }

    public CourseInstructor[] getCourseInstructor() {
        return courseInstructors;
    }

    public void setCourseInstructors(CourseInstructor[] courseInstructors) {
        this.courseInstructors = courseInstructors;
    }
}

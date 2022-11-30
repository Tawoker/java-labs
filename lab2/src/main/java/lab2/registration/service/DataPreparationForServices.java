package lab2.registration.service;

import lab2.registration.model.*;
import lab2.registration.reader.CourseDataReader;
import lab2.registration.reader.InstructorDataReader;
import lab2.registration.reader.StudentDataReader;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class DataPreparationForServices {

    private List<CourseInfo> coursesInfo;

    private List<CourseInstanceWithSubscribe> coursesWithSubs;

    private List<SubscribedStudent> subscribedStudents;

    private List<CourseInstructor> courseInstructors;


    public DataPreparationForServices() throws IOException {
        courseInstructors = new InstructorDataReader().readCourseInstructorData();
        coursesInfo = new CourseDataReader().readCourseInfoData();
        coursesWithSubs = new CourseDataReader().readCourseInstanceWithSubscribe();

        List<SubscribedStudent> bachelorsList = new StudentDataReader().readBachelorStudentData();
        bachelorsList.forEach(x -> x.setStudentCategory(StudentCategory.BACHELOR));

        List<SubscribedStudent> mastersList = new StudentDataReader().readMasterStudentData();
        mastersList.forEach(x -> x.setStudentCategory(StudentCategory.MASTER));

        subscribedStudents = new ArrayList<SubscribedStudent>();
        subscribedStudents.addAll(bachelorsList);
        subscribedStudents.addAll(mastersList);

        courseInstructors.forEach(courseInstructor -> courseInstructor.addAllTeachingCourses(coursesWithSubs));
    }

    public List<CourseInfo> getCoursesInfo() {
        return coursesInfo;
    }

    public void setCoursesInfo(List<CourseInfo> coursesInfo) {
        this.coursesInfo = coursesInfo;
    }

    public List<CourseInstanceWithSubscribe> getCoursesWithSubs() {
        return coursesWithSubs;
    }

    public void setCoursesWithSubs(List<CourseInstanceWithSubscribe> coursesWithSubs) {
        this.coursesWithSubs = coursesWithSubs;
    }

    public List<SubscribedStudent> getSubscribedStudents() {
        return subscribedStudents;
    }

    public void setSubscribedStudents(List<SubscribedStudent> subscribedStudents) {
        this.subscribedStudents = subscribedStudents;
    }

    public List<CourseInstructor> getCourseInstructor() {
        return courseInstructors;
    }

    public void setCourseInstructors(List<CourseInstructor> courseInstructors) {
        this.courseInstructors = courseInstructors;
    }
}

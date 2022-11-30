package lab2.registration.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubscribedStudent extends Student{

    /**
     * список курсов, на которые подписан студент
     */
    private List<CourseInstanceWithSubscribe> subscribedCourses;

    private StudentCategory studentCategory;

    public SubscribedStudent() throws IOException {
        subscribedCourses = new ArrayList<CourseInstanceWithSubscribe>();
    }

    public StudentCategory getStudentCategory() {
        return studentCategory;
    }

    public void setStudentCategory(StudentCategory studentCategory) {
        this.studentCategory = studentCategory;
    }

    public void subscribeToCourse(CourseInstanceWithSubscribe courseInstance){
        this.subscribedCourses.add(courseInstance);
    }

    public void unsubscribeByCourseInstanceId(long courseInstanceId){
        subscribedCourses.removeIf(course -> course.getId() == courseInstanceId);
    }

    public List<CourseInstanceWithSubscribe> getSubscribedCourses() {
        return subscribedCourses;
    }

    public void setSubscribedCourses(List<CourseInstanceWithSubscribe> subscribedCourses) {
        this.subscribedCourses = subscribedCourses;
    }

}

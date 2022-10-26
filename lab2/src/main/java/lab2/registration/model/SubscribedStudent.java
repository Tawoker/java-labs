package lab2.registration.model;

import lab2.registration.reader.CourseDataReader;

import java.io.IOException;

public class SubscribedStudent extends Student{

    /**
     * список курсов, на которые подписан студент
     */
    private CourseInstanceWithSubscribe[] subscribedCourses;

    private StudentCategory studentCategory;

    public SubscribedStudent() throws IOException {
        subscribedCourses = new CourseInstanceWithSubscribe[new CourseDataReader().readCourseInstance().length];
    }

    public StudentCategory getStudentCategory() {
        return studentCategory;
    }

    public void setStudentCategory(StudentCategory studentCategory) {
        this.studentCategory = studentCategory;
    }

    public void subscribeToCourse(CourseInstanceWithSubscribe courseInstance){
        this.subscribedCourses[getAmountSubscribedCourses()] = courseInstance;
    }

    public void unsubscribeByCourseInstanceId(long courseInstanceId){
        boolean flag = false;
        for (int i = 0; i < subscribedCourses.length - 1; i++) {
            if (!flag)
                if (subscribedCourses[i].getId() == courseInstanceId) {
                    flag = true;
                    subscribedCourses[i] = subscribedCourses[i + 1];
                } else
                    subscribedCourses[i] = subscribedCourses[i + 1];
        }
        subscribedCourses[subscribedCourses.length - 1] = null;
    }

    public CourseInstanceWithSubscribe[] getSubscribedCourses() {
        return subscribedCourses;
    }

    public void setSubscribedCourses(CourseInstanceWithSubscribe[] subscribedCourses) {
        this.subscribedCourses = subscribedCourses;
    }

    public int getAmountSubscribedCourses() {
        int amount = 0;
        for (int i = 0; i < subscribedCourses.length; i++)
            if(subscribedCourses[i] != null)
                amount++;
        return amount;
    }
}

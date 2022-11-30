package lab2.registration.model;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class CourseInstanceWithSubscribe extends CourseInstance {

    private List<SubscribedStudent> subscribedStudents;

    public CourseInstanceWithSubscribe() throws IOException {
        this.subscribedStudents = new ArrayList<SubscribedStudent>();
    }

    public List<SubscribedStudent> getSubscribedStudents() {
        return subscribedStudents;
    }

    public void setSubscribedStudents(List<SubscribedStudent> subscribedStudents) {
        this.subscribedStudents = subscribedStudents;
    }

    public void addStudent(SubscribedStudent student){
        this.subscribedStudents.add(student);
    }

    public void deleteStudentById(long id) {
        this.subscribedStudents.removeIf(student -> student.getId() == id);
    }
}

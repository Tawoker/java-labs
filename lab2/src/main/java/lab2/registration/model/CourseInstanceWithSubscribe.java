package lab2.registration.model;

import lab2.registration.reader.StudentDataReader;

import java.io.IOException;

public class CourseInstanceWithSubscribe extends CourseInstance {

    private SubscribedStudent[] subscribedStudents;

    public CourseInstanceWithSubscribe() throws IOException {
        if (this.getCapacity() == 0)
            this.subscribedStudents = new SubscribedStudent[new StudentDataReader().readBachelorStudentData().length + new StudentDataReader().readMasterStudentData().length];
        else
            this.subscribedStudents = new SubscribedStudent[this.getCapacity()];
    }

    public int getAmountSubscribedStudents() {
        int amount = 0;
        for (int i = 0; i < subscribedStudents.length; i++)
            if(subscribedStudents[i] != null)
                amount++;
        return amount;
    }

    public SubscribedStudent[] getSubscribedStudents() {
        return subscribedStudents;
    }

    public void setSubscribedStudents(SubscribedStudent[] subscribedStudents) {
        this.subscribedStudents = subscribedStudents;
    }

    public void addStudent(SubscribedStudent student){
        this.subscribedStudents[getAmountSubscribedStudents()] = student;
    }

    public void deleteStudentById(long id) {
        boolean flag = false;
        for (int i = 0; i < subscribedStudents.length - 1; i++) {
            if (!flag) {
                if (subscribedStudents[i].getId() == id) {
                    flag = true;
                    subscribedStudents[i] = subscribedStudents[i + 1];
                }
            }else
                subscribedStudents[i] = subscribedStudents[i + 1];
        }
        subscribedStudents[subscribedStudents.length - 1] = null;
    }
}

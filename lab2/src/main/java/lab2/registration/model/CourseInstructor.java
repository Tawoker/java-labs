package lab2.registration.model;

import lab2.registration.reader.CourseDataReader;
import lab2.registration.reader.StudentDataReader;

import java.io.IOException;

public class CourseInstructor extends Instructor{

    private CourseInstanceWithSubscribe[] teachingCourses;

    private int amountTeachingCourses;

    private SubscribedStudent[] myStudents;


    public CourseInstructor() throws IOException {
        amountTeachingCourses = 0;
        this.myStudents = new SubscribedStudent[new StudentDataReader().readBachelorStudentData().length + new StudentDataReader().readMasterStudentData().length];
        this.teachingCourses = new CourseInstanceWithSubscribe[new CourseDataReader().readCourseInstanceWithSubscribe().length];
    }

    public void addAllTeachingCourses(CourseInstanceWithSubscribe[] allCourses){
        for (int i = 0; i < allCourses.length; i++) {
            if(allCourses[i].getInstructorId() == this.getId()){
                teachingCourses[amountTeachingCourses] = allCourses[i];
                amountTeachingCourses++;
            }
        }
    }

    public void addStudent(SubscribedStudent student){
        boolean alreadyHaveStudent = false;
        for (int i = 0; i < myStudents.length; i++)
            if(myStudents[i] == student)
                alreadyHaveStudent = true;
        if(!alreadyHaveStudent){
            myStudents[getAmountMyStudents()] = student;
        }
    }

    public SubscribedStudent[] getMyStudents() {
        return myStudents;
    }

    public void tryDeleteStudent(SubscribedStudent student){
        int currentStudentIndex = -1;

        for (int i = 0; i < getAmountMyStudents(); i++)
            if(myStudents[i].getId() == student.getId())
                currentStudentIndex = i;

        int counterOfCourses = 0;

        if(currentStudentIndex != -1){
            for (int i = 0; i < amountTeachingCourses; i++) {
                for (int j = 0; j < myStudents[currentStudentIndex].getAmountSubscribedCourses(); j++)
                    if(myStudents[currentStudentIndex].getSubscribedCourses()[j].getId() == teachingCourses[i].getId())
                        counterOfCourses++;
                if(counterOfCourses >= 1)
                    break;
            }
            if(counterOfCourses <= 1){
                deleteStudent(student);
            }
        }
    }

    public void deleteStudent(SubscribedStudent student) {
        boolean flag = false;
        for (int i = 0; i < myStudents.length - 1; i++) {
            if (!flag) {
                if (myStudents[i].getId() == student.getId()) {
                    flag = true;
                    myStudents[i] = myStudents[i + 1];
                }
            } else
                myStudents[i] = myStudents[i + 1];

            myStudents[myStudents.length - 1] = null;
        }
    }

    public int getAmountMyStudents() {
        int amount = 0;
        for (int i = 0; i < myStudents.length; i++)
            if(myStudents[i] != null)
                amount++;
        return amount;
    }
}

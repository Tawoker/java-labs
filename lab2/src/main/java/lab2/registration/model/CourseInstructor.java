package lab2.registration.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;
import java.io.IOException;

public class CourseInstructor extends Instructor{

    private List<CourseInstanceWithSubscribe> teachingCourses;

    private List<SubscribedStudent> myStudents;


    public CourseInstructor() throws IOException {
        this.myStudents = new ArrayList<SubscribedStudent>();
        this.teachingCourses = new ArrayList<CourseInstanceWithSubscribe>();
    }

    public void addAllTeachingCourses(List<CourseInstanceWithSubscribe> allCourses){
        allCourses.stream().
                filter(course -> course.getInstructorId() == this.getId()).
                forEach(course -> {
                    teachingCourses.add(course);
                });
    }

    public void addStudent(SubscribedStudent student){
        AtomicBoolean alreadyHaveStudent = new AtomicBoolean(false);
        myStudents.stream().
                filter(myStudent -> myStudent == student).
                forEach(myStudent -> {
                    alreadyHaveStudent.set(true);
                });
        if(!alreadyHaveStudent.get()) {
            myStudents.add(student);
        }
    }

    public List<SubscribedStudent> getMyStudents() {
        return myStudents;
    }

    public void tryDeleteStudent(SubscribedStudent student){
        int currentStudentIndex = -1;

        currentStudentIndex = IntStream.range(0, myStudents.size())
                .filter(i -> myStudents.get(i).getId() == student.getId()).
                findAny().
                getAsInt();

        int counterOfCourses = 0;

        SubscribedStudent curStudent= myStudents.get(currentStudentIndex);

        if(currentStudentIndex != -1) {
            for (int i = 0; i < teachingCourses.size(); i++) {
                long teachingCourseId = teachingCourses.get(i).getId();
                counterOfCourses = (int) IntStream.range(0, curStudent.getSubscribedCourses().size()).
                        filter(j -> curStudent.getSubscribedCourses().get(j).getId() == teachingCourseId).
                        count();
                if(counterOfCourses >= 1) {
                    break;
                }
            }

            if(counterOfCourses <= 1) {
                deleteStudent(student);
            }
        }
    }

    public void deleteStudent(SubscribedStudent student) {
        myStudents.removeIf(myStudent -> myStudent.getId() == student.getId());
    }
}

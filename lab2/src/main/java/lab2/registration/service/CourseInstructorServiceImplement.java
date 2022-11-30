package lab2.registration.service;

import lab2.registration.model.CourseInstructor;
import lab2.registration.model.SubscribedStudent;
import lab2.registration.model.CourseInstanceWithSubscribe;
import lab2.registration.model.CourseInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CourseInstructorServiceImplement implements CourseInstructorService {

    private List<CourseInfo> coursesInfo;

    private List<CourseInstanceWithSubscribe> coursesWithSubs;

    private List<SubscribedStudent> subscribedStudents;

    private List<CourseInstructor> courseInstructors;

    public CourseInstructorServiceImplement(List<CourseInfo> coursesInfo, List<CourseInstanceWithSubscribe> coursesWithSubs, List<SubscribedStudent> subscribedStudents, List<CourseInstructor> courseInstructors){
        this.coursesInfo = coursesInfo;
        this.coursesWithSubs = coursesWithSubs;
        this.subscribedStudents = subscribedStudents;
        this.courseInstructors = courseInstructors;
    }

    @Override
    public List<SubscribedStudent> findStudentsByCourseId(long courseId){
        int currentCourseInstanceIndex = -1;

        try {
            currentCourseInstanceIndex = IntStream.range(0, coursesWithSubs.size())
                    .filter(i -> coursesWithSubs.get(i).getId() == courseId).
                    findAny().
                    getAsInt();

            if (currentCourseInstanceIndex == -1) {
                throw new Exception("Несуществующий идентификатор курса!");
            }
        } catch (Exception e) {
            return null;
        }
        return coursesWithSubs.get(currentCourseInstanceIndex).getSubscribedStudents();
    }

        @Override
    public List<SubscribedStudent> findStudentsByInstructorId(long instructorId) {
        int currentInstructorIndex = -1;
        try
        {
            currentInstructorIndex = IntStream.range(0, courseInstructors.size())
                    .filter(i -> courseInstructors.get(i).getId() == instructorId).
                    findAny().
                    getAsInt();

            if (currentInstructorIndex == -1) {
                throw new Exception("Несуществующий идентификатор преподавателя!");
            }

        }catch (Exception e){
            return null;
        }
        return courseInstructors.get(currentInstructorIndex).getMyStudents();
    }

    @Override
    public List<CourseInstructor> findReplacement(long instructorId, long courseId) {
        int currentInstructorIndex = -1;
        int currentCourseIndex = -1;
        try
        {
            currentCourseIndex = IntStream.range(0, coursesInfo.size())
                    .filter(i -> coursesInfo.get(i).getId() == courseId).
                    findAny().
                    getAsInt();

            if(currentCourseIndex == -1) {
                throw new Exception("Несуществующий идентификатор курса!");
            }

            currentInstructorIndex = IntStream.range(0, courseInstructors.size())
                    .filter(i -> courseInstructors.get(i).getId() == instructorId).
                    findAny().
                    getAsInt();

            if (currentInstructorIndex == -1) {
                throw new Exception("Несуществующий идентификатор преподавателя!");
            }
        }catch (Exception e){
            return null;
        }

        List<CourseInstructor> canReplace = new ArrayList<CourseInstructor>();

        for (int i = 0; i < courseInstructors.size(); i++) {
            CourseInstructor courseCanTeach = courseInstructors.get(i);
            if (i != currentInstructorIndex) {
                int index = IntStream.range(0, courseCanTeach.getCanTeach().size())
                        .filter(j -> courseId == courseCanTeach.getCanTeach().get(j))
                        .findFirst()
                        .orElse(-1);

                if (index != -1) {
                    canReplace.add(courseInstructors.get(i));
                }
            }
        }

        return canReplace;
    }
}

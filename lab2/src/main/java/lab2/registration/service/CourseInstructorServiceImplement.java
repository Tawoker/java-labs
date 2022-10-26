package lab2.registration.service;

import lab2.registration.model.CourseInstructor;
import lab2.registration.model.Student;
import lab2.registration.model.SubscribedStudent;
import lab2.registration.model.CourseInstanceWithSubscribe;
import lab2.registration.model.CourseInfo;

public class CourseInstructorServiceImplement implements CourseInstructorService {

    private CourseInfo[] coursesInfo;

    private CourseInstanceWithSubscribe[] coursesWithSubs;

    private SubscribedStudent[] subscribedStudents;

    private CourseInstructor[] courseInstructors;

    public CourseInstructorServiceImplement(CourseInfo[] coursesInfo, CourseInstanceWithSubscribe[] coursesWithSubs, SubscribedStudent[] subscribedStudents, CourseInstructor[] courseInstructors){
        this.coursesInfo = coursesInfo;
        this.coursesWithSubs = coursesWithSubs;
        this.subscribedStudents = subscribedStudents;
        this.courseInstructors = courseInstructors;
    }

    @Override
    public SubscribedStudent[] findStudentsByCourseId(long courseId){
        int currentCourseInstanceIndex = -1;

        try {
            for (int i = 0; i < coursesWithSubs.length; i++)
                if (coursesWithSubs[i].getId() == courseId)
                    currentCourseInstanceIndex = i;

            if (currentCourseInstanceIndex == -1)
                throw new Exception("Несуществующий идентификатор курса!");
        } catch (Exception e) {
            return null;
        }
        return coursesWithSubs[currentCourseInstanceIndex].getSubscribedStudents();
    }

        @Override
    public SubscribedStudent[] findStudentsByInstructorId(long instructorId) {
        int currentInstructorIndex = -1;
        try
        {
            for (int i = 0; i < courseInstructors.length; i++) {
                if(courseInstructors[i].getId() == instructorId)
                    currentInstructorIndex = i;
            }

            if (currentInstructorIndex == -1)
                throw new Exception("Несуществующий идентификатор преподавателя!");

        }catch (Exception e){
            return null;
        }
        return courseInstructors[currentInstructorIndex].getMyStudents();
    }

    @Override
    public CourseInstructor[] findReplacement(long instructorId, long courseId) {
        int currentInstructorIndex = -1;
        int currentCourseIndex = -1;
        try
        {
            for (int i = 0; i < coursesInfo.length; i++)
                if(coursesInfo[i].getId() == courseId)
                    currentCourseIndex = i;

            if(currentCourseIndex == -1)
                throw new Exception("Несуществующий идентификатор курса!");

            for (int i = 0; i < courseInstructors.length; i++) {
                if(courseInstructors[i].getId() == instructorId)
                    currentInstructorIndex = i;
            }

            if (currentInstructorIndex == -1)
                throw new Exception("Несуществующий идентификатор преподавателя!");

        }catch (Exception e){
            return null;
        }

        CourseInstructor[] canReplace = new CourseInstructor[courseInstructors.length - 1];
        int counterCanReplace = 0;

        for (int i = 0; i < courseInstructors.length; i++) {
            if (i != currentInstructorIndex){
                for (int j = 0; j < courseInstructors[i].getCanTeach().length; j++) {
                    if(courseId == courseInstructors[i].getCanTeach()[j]){
                        canReplace[counterCanReplace] = courseInstructors[i];
                        counterCanReplace++;
                        break;
                    }
                }
            }
        }

        CourseInstructor[] result = new CourseInstructor[counterCanReplace];

        for (int i = 0; i < counterCanReplace; i++) {
            result[i] = canReplace[i];
        }

        return result;
    }
}

package lab2.registration.service;

import lab2.registration.model.*;

import java.time.LocalDate;

public class StudentServiceImplement implements StudentService{

    private CourseInfo[] coursesInfo;

    private CourseInstanceWithSubscribe[] coursesWithSubs;

    private SubscribedStudent[] subscribedStudents;

    private CourseInstructor[] courseInstructors;

    public StudentServiceImplement(CourseInfo[] coursesInfo, CourseInstanceWithSubscribe[] coursesWithSubs, SubscribedStudent[] subscribedStudents, CourseInstructor[] courseInstructors){
        this.coursesInfo = coursesInfo;
        this.coursesWithSubs = coursesWithSubs;
        this.subscribedStudents = subscribedStudents;
        this.courseInstructors = courseInstructors;
    }

    @Override
    public ActionStatus subscribe(long studentId, long courseId) {

        int currentCourseInstanceIndex = -1;
        int currentStudentIndex = -1;
        int currentInstructorIndex = -1;
        try {

            for (int i = 0; i < subscribedStudents.length; i++)
                if (subscribedStudents[i].getId() == studentId)
                    currentStudentIndex = i;

            if (currentStudentIndex == -1)
                throw new Exception("Несуществующий идентификатор студента!");

            for (int i = 0; i < coursesWithSubs.length; i++)
                if (coursesWithSubs[i].getId() == courseId)
                    currentCourseInstanceIndex = i;

            if (currentCourseInstanceIndex == -1)
                throw new Exception("Несуществующий идентификатор курса!");

            for (int i = 0; i < courseInstructors.length; i++) {
                if(coursesWithSubs[currentCourseInstanceIndex].getInstructorId() == courseInstructors[i].getId())
                    currentInstructorIndex = i;
            }

            if (currentInstructorIndex == -1)
                throw new Exception("Не найден инструктор курса!");

            if (!coursesWithSubs[currentCourseInstanceIndex].getStartDate().isAfter(LocalDate.now()))
                throw new Exception("Курс уже начался!");

            int currentCourseIndex = -1;

            for (int i = 0; i < coursesInfo.length; i++)
                if (coursesInfo[i].getId() == coursesWithSubs[currentCourseInstanceIndex].getCourseId())
                    currentCourseIndex = i;

            boolean correctStudentCategory = false;

            for (int i = 0; i < coursesInfo[currentCourseIndex].getStudentCategories().length; i++)
                if (coursesInfo[currentCourseIndex].getStudentCategories()[i] == subscribedStudents[currentStudentIndex].getStudentCategory()) {
                    correctStudentCategory = true;
                    break;
                }

            if (!correctStudentCategory)
                throw new Exception("Категория студента не соответствует требованиям курса!");

            if(coursesInfo[currentCourseIndex].getPrerequisites() != null) {

                int counterPrerequisites = 0;

                for (int i = 0; i < coursesInfo[currentCourseIndex].getPrerequisites().length; i++)
                    for (int j = 0; j < subscribedStudents[currentStudentIndex].getCompletedCourses().length; j++)
                        if (coursesInfo[currentCourseIndex].getPrerequisites()[i] == subscribedStudents[currentStudentIndex].getCompletedCourses()[j]) {
                            counterPrerequisites++;
                            break;
                        }

                if (counterPrerequisites != coursesInfo[currentCourseIndex].getPrerequisites().length)
                    throw new Exception("Для записи студентом не пройденны все необходимые курсы!");
            }

            if(coursesWithSubs[currentCourseInstanceIndex].getCapacity() != 0)
                if (coursesWithSubs[currentCourseInstanceIndex].getCapacity() == coursesWithSubs[currentCourseInstanceIndex].getAmountSubscribedStudents())
                    throw new Exception("На курсе нет мест!");

            for (int i = 0; i < subscribedStudents[currentStudentIndex].getAmountSubscribedCourses(); i++) {
                if (subscribedStudents[currentStudentIndex].getSubscribedCourses()[i].getId() == courseId)
                    throw new Exception("Студент уже записан на курс!");
            }
        } catch (Exception e) {
            return ActionStatus.NOK;
        }
        coursesWithSubs[currentCourseInstanceIndex].addStudent(subscribedStudents[currentStudentIndex]);
        subscribedStudents[currentStudentIndex].subscribeToCourse(coursesWithSubs[currentCourseInstanceIndex]);
        courseInstructors[currentInstructorIndex].addStudent(subscribedStudents[currentStudentIndex]);
        return ActionStatus.OK;
    }

    @Override
    public ActionStatus unsubscribe(long studentId, long courseId){
        int currentCourseInstanceIndex = -1;
        int currentStudentIndex = -1;
        int currentInstructorIndex = -1;

        try {
            for (int i = 0; i < subscribedStudents.length; i++)
                if (subscribedStudents[i].getId() == studentId)
                    currentStudentIndex = i;

            if (currentStudentIndex == -1)
                throw new Exception("Несуществующий идентификатор студента!");

            for (int i = 0; i < coursesWithSubs.length; i++)
                if (coursesWithSubs[i].getId() == courseId)
                    currentCourseInstanceIndex = i;

            if (currentCourseInstanceIndex == -1)
                throw new Exception("Несуществующий идентификатор курса!");

            for (int i = 0; i < courseInstructors.length; i++) {
                if(coursesWithSubs[currentCourseInstanceIndex].getInstructorId() == courseInstructors[i].getId())
                    currentInstructorIndex = i;
            }

            if (currentInstructorIndex == -1)
                throw new Exception("Не найден инструктор курса!");

            if (!coursesWithSubs[currentCourseInstanceIndex].getStartDate().isAfter(LocalDate.now()))
                throw new Exception("Курс уже начался!");

        }catch (Exception e){
            return ActionStatus.NOK;
        }
        for(int i = 0; i < coursesWithSubs[currentCourseInstanceIndex].getAmountSubscribedStudents(); i++){
            if(coursesWithSubs[currentCourseInstanceIndex].getSubscribedStudents()[i].getId() == studentId){
                courseInstructors[currentInstructorIndex].tryDeleteStudent(subscribedStudents[currentStudentIndex]);
                coursesWithSubs[currentCourseInstanceIndex].deleteStudentById(studentId);
                subscribedStudents[currentStudentIndex].unsubscribeByCourseInstanceId(courseId);
                return ActionStatus.OK;
            }
        }
        return ActionStatus.NOK;
    }

    @Override
    public CourseInstance[] findAllSubscriptionsByStudentId(long studentId){

        int currentStudentIndex = -1;

        try {
            for (int i = 0; i < subscribedStudents.length; i++)
                if (subscribedStudents[i].getId() == studentId)
                    currentStudentIndex = i;

            if (currentStudentIndex == -1)
                throw new Exception("Несуществующий идентификатор студента!");
        }catch (Exception e){
            return null;
        }



        return null;
    }
}

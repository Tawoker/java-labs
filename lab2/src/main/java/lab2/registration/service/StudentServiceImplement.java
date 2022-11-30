package lab2.registration.service;

import lab2.registration.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

public class StudentServiceImplement implements StudentService{

    private List<CourseInfo> coursesInfo;

    private List<CourseInstanceWithSubscribe> coursesWithSubs;

    private List<SubscribedStudent> subscribedStudents;

    private List<CourseInstructor> courseInstructors;

    public StudentServiceImplement(List<CourseInfo> coursesInfo, List<CourseInstanceWithSubscribe> coursesWithSubs, List<SubscribedStudent> subscribedStudents, List<CourseInstructor> courseInstructors){
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
            currentStudentIndex = IntStream.range(0, subscribedStudents.size())
                    .filter(i -> subscribedStudents.get(i).getId() == studentId).
                    findAny().
                    getAsInt();

            if (currentStudentIndex == -1) {
                throw new Exception("Несуществующий идентификатор студента!");
            }

            currentCourseInstanceIndex = IntStream.range(0, coursesWithSubs.size())
                    .filter(i -> coursesWithSubs.get(i).getId() == courseId).
                    findAny().
                    getAsInt();

            if (currentCourseInstanceIndex == -1) {
                throw new Exception("Несуществующий идентификатор курса!");
            }

            int curCourseIns = currentCourseInstanceIndex;
            currentInstructorIndex = IntStream.range(0, courseInstructors.size())
                    .filter(i -> coursesWithSubs.get(curCourseIns).getInstructorId() == courseInstructors.get(i).getId()).
                    findAny().
                    getAsInt();

            if (currentInstructorIndex == -1) {
                throw new Exception("Не найден инструктор курса!");
            }

            if (!coursesWithSubs.get(currentCourseInstanceIndex).getStartDate().isAfter(LocalDate.now())) {
                throw new Exception("Курс уже начался!");
            }

            int currentCourseIndex = -1;

            currentCourseIndex = IntStream.range(0, coursesInfo.size())
                    .filter(i -> coursesInfo.get(i).getId() == coursesWithSubs.get(curCourseIns).getCourseId()).
                    findAny().
                    getAsInt();

            boolean correctStudentCategory = false;

            int curStud = currentStudentIndex;
            int curCourse = currentCourseIndex;
            correctStudentCategory = IntStream.range(0, coursesInfo.get(currentCourseIndex).getStudentCategories().size()).
                    anyMatch(i -> coursesInfo.get(curCourse).getStudentCategories().get(i) == subscribedStudents.get(curStud).getStudentCategory());

            if (!correctStudentCategory) {
                throw new Exception("Категория студента не соответствует требованиям курса!");
            }

            if(coursesInfo.get(currentCourseIndex).getPrerequisites() != null) {

                int counterPrerequisites = 0;

                for (int i = 0; i < coursesInfo.get(currentCourseIndex).getPrerequisites().size(); i++) {
                    int curInd = i;
                    int index = IntStream.range(0, subscribedStudents.get(currentStudentIndex).getCompletedCourses().size())
                            .filter(j -> coursesInfo.get(curCourse).getPrerequisites().get(curInd) == subscribedStudents.get(curStud).getCompletedCourses().get(j))
                            .findFirst()
                            .orElse(-1);
                    if (index != -1) {
                        counterPrerequisites++;
                    }
                }

                if (counterPrerequisites != coursesInfo.get(currentCourseIndex).getPrerequisites().size()) {
                    throw new Exception("Для записи студентом не пройденны все необходимые курсы!");
                }
            }

            if(coursesWithSubs.get(currentCourseInstanceIndex).getCapacity() != 0)
                if (coursesWithSubs.get(currentCourseInstanceIndex).getCapacity() == coursesWithSubs.get(currentCourseInstanceIndex).getSubscribedStudents().size()) {
                    throw new Exception("На курсе нет мест!");
                }

            for (int i = 0; i < subscribedStudents.get(currentStudentIndex).getSubscribedCourses().size(); i++) {
                if (subscribedStudents.get(currentStudentIndex).getSubscribedCourses().get(i).getId() == courseId) {
                    throw new Exception("Студент уже записан на курс!");
                }
            }
        } catch (Exception e) {
            return ActionStatus.NOK;
        }
        coursesWithSubs.get(currentCourseInstanceIndex).addStudent(subscribedStudents.get(currentStudentIndex));
        subscribedStudents.get(currentStudentIndex).subscribeToCourse(coursesWithSubs.get(currentCourseInstanceIndex));
        courseInstructors.get(currentInstructorIndex).addStudent(subscribedStudents.get(currentStudentIndex));
        return ActionStatus.OK;
    }

    @Override
    public ActionStatus unsubscribe(long studentId, long courseId){
        int currentCourseInstanceIndex = -1;
        int currentStudentIndex = -1;
        int currentInstructorIndex = -1;

        try {
            currentStudentIndex = IntStream.range(0, subscribedStudents.size())
                    .filter(i -> subscribedStudents.get(i).getId() == studentId).
                    findAny().
                    getAsInt();

            if (currentStudentIndex == -1) {
                throw new Exception("Несуществующий идентификатор студента!");
            }

            currentCourseInstanceIndex = IntStream.range(0, coursesWithSubs.size())
                    .filter(i -> coursesWithSubs.get(i).getId() == courseId).
                    findAny().
                    getAsInt();

            if (currentCourseInstanceIndex == -1) {
                throw new Exception("Несуществующий идентификатор курса!");
            }

            int curCourseIns = currentCourseInstanceIndex;
            currentInstructorIndex = IntStream.range(0, courseInstructors.size())
                    .filter(i -> coursesWithSubs.get(curCourseIns).getInstructorId() == courseInstructors.get(i).getId()).
                    findAny().
                    getAsInt();

            if (currentInstructorIndex == -1) {
                throw new Exception("Не найден инструктор курса!");
            }

            if (!coursesWithSubs.get(currentCourseInstanceIndex).getStartDate().isAfter(LocalDate.now())) {
                throw new Exception("Курс уже начался!");
            }

        }catch (Exception e){
            return ActionStatus.NOK;
        }
        for(int i = 0; i < coursesWithSubs.get(currentCourseInstanceIndex).getSubscribedStudents().size(); i++){
            if(coursesWithSubs.get(currentCourseInstanceIndex).getSubscribedStudents().get(i).getId() == studentId){
                courseInstructors.get(currentInstructorIndex).tryDeleteStudent(subscribedStudents.get(currentStudentIndex));
                coursesWithSubs.get(currentCourseInstanceIndex).deleteStudentById(studentId);
                subscribedStudents.get(currentStudentIndex).unsubscribeByCourseInstanceId(courseId);
                return ActionStatus.OK;
            }
        }
        return ActionStatus.NOK;
    }

    @Override
    public List<CourseInstanceWithSubscribe> findAllSubscriptionsByStudentId(long studentId){

        int currentStudentIndex = -1;

        try {
            currentStudentIndex = IntStream.range(0, subscribedStudents.size())
                    .filter(i -> subscribedStudents.get(i).getId() == studentId).
                    findAny().
                    getAsInt();

            if (currentStudentIndex == -1) {
                throw new Exception("Несуществующий идентификатор студента!");
            }
        }catch (Exception e){
            return null;
        }

        return subscribedStudents.get(currentStudentIndex).getSubscribedCourses();
    }
}

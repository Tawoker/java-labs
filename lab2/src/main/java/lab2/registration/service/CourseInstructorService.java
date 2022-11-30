package lab2.registration.service;

import lab2.registration.model.*;

import java.util.List;

/**
 * Интерфейс сервиса для преподавателя
 */
public interface CourseInstructorService {
    
    /**
     * @param courseId идентификатор курса
     * @return список студентов, зарегистрированных на данный курс
     */
    List<SubscribedStudent> findStudentsByCourseId(long courseId) throws Exception;

    /**
     * @param instructorId идентификатор преподавателя
     * @return список студентов, посещающих один из курсов данного преподавателя
     */
    List<SubscribedStudent> findStudentsByInstructorId(long instructorId);

    /**
     * @param instructorId идентификатор преподавателя
     * @param courseId     идентификатор курса
     * @return список преподавателей, которые могут прочитать данный курс вместо данного преподавателя
     */
    List<CourseInstructor> findReplacement(long instructorId, long courseId);

}

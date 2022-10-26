package lab2.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.registration.model.CourseInstructor;
import lab2.registration.model.Instructor;
import lab2.registration.model.Student;

import java.io.File;
import java.io.IOException;

public class InstructorDataReader {
    private ObjectMapper objectMapper = new ObjectMapper();

    public CourseInstructor[] readCourseInstructorData() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/instructors.json"), CourseInstructor[].class);
    }

}

package lab2.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.registration.model.CourseInfo;
import lab2.registration.model.CourseInstance;
import lab2.registration.model.CourseInstanceWithSubscribe;

import java.io.File;
import java.io.IOException;

public class CourseDataReader {
    private ObjectMapper objectMapper = new ObjectMapper();

    public CourseDataReader(){
        objectMapper.findAndRegisterModules();
    }

    public CourseInfo[] readCourseInfoData() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/courseInfos.json"), CourseInfo[].class);
    }

    public CourseInstance[] readCourseInstance() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/courseInstances.json"), CourseInstance[].class);
    }

    public CourseInstanceWithSubscribe[] readCourseInstanceWithSubscribe() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/courseInstances.json"), CourseInstanceWithSubscribe[].class);
    }
}
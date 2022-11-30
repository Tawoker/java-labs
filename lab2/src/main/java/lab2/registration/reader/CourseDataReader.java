package lab2.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.registration.model.CourseInfo;
import lab2.registration.model.CourseInstance;
import lab2.registration.model.CourseInstanceWithSubscribe;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CourseDataReader {
    private ObjectMapper objectMapper = new ObjectMapper();

    public CourseDataReader(){
        objectMapper.findAndRegisterModules();
    }

    public List<CourseInfo> readCourseInfoData() throws IOException {
        return Arrays.asList(objectMapper.readValue(new File("src/main/resources/courseInfos.json"), CourseInfo[].class));
    }

    public List<CourseInstance> readCourseInstance() throws IOException {
        return Arrays.asList(objectMapper.readValue(new File("src/main/resources/courseInstances.json"), CourseInstance[].class));
    }

    public List<CourseInstanceWithSubscribe> readCourseInstanceWithSubscribe() throws IOException {
        return Arrays.asList(objectMapper.readValue(new File("src/main/resources/courseInstances.json"), CourseInstanceWithSubscribe[].class));
    }
}
package lab2.registration.reader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.registration.model.Student;
import lab2.registration.model.StudentCategory;
import lab2.registration.model.SubscribedStudent;

import java.io.File;
import java.io.IOException;

/**
 * Класс для чтения информации о студентах из файлов
 */
public class StudentDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @return список студентов-бакалавров
     */
    public SubscribedStudent[] readBachelorStudentData() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/bachelorStudents.json"), SubscribedStudent[].class);
    }

    /**
     * @return список студентов-магистров
     */
    public SubscribedStudent[] readMasterStudentData() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/masterStudents.json"), SubscribedStudent[].class);
    }

}

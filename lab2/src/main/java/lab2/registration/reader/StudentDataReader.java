package lab2.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.registration.model.SubscribedStudent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для чтения информации о студентах из файлов
 */
public class StudentDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @return список студентов-бакалавров
     */
    public List<SubscribedStudent> readBachelorStudentData() throws IOException {
        return Arrays.asList(objectMapper.readValue(new File("src/main/resources/bachelorStudents.json"), SubscribedStudent[].class));
    }

    /**
     * @return список студентов-магистров
     */
    public List<SubscribedStudent> readMasterStudentData() throws IOException {
        return Arrays.asList(objectMapper.readValue(new File("src/main/resources/masterStudents.json"), SubscribedStudent[].class));
    }

}

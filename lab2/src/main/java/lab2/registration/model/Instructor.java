package lab2.registration.model;

import java.util.List;

/**
 * Класс для информации о преподавателе
 */
public class Instructor extends lab2.registration.model.Person {

    /**
     * Идентификаторы курсов, которые может вести преподаватель
     */
    List<Integer> canTeach;

    public List<Integer> getCanTeach(){
        return canTeach;
    }

    public void setCanTeach(List<Integer> canTeach){
        this.canTeach = canTeach;
    }
    
}

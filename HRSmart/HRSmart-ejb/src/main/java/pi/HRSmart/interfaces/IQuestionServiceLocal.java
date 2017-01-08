package pi.HRSmart.interfaces;

import pi.HRSmart.persistence.Question;
import pi.HRSmart.persistence.Quiz;

import javax.ejb.Local;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alaa on 20/10/16.
 */

public interface IQuestionServiceLocal {
    Question add(Question question);
    Question update(Question question);
    List<Question> remove(Question question);
    Question get(int id);
    List<Question> all();
    List<Question> getBySkill(String skill);

}

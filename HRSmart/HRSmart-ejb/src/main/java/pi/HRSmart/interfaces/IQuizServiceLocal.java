package pi.HRSmart.interfaces;

import pi.HRSmart.persistence.Quiz;
import pi.HRSmart.persistence.Skill;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by alaa on 19/10/16.
 */
@Local
public interface IQuizServiceLocal {
    Quiz add(Quiz quiz);
    Quiz update(Quiz quiz);
    void remove(Quiz quiz);
    Quiz get(int id);
    List<Quiz> all();
    Quiz getWithRelations(int id);
    void addWithRelation(Quiz quiz);
    List<Quiz> getQuizBySkill(String skill);

}

package pi.HRSmart.interfaces;

import pi.HRSmart.persistence.Assessment;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by alaa on 01/11/16.
 */
@Local
public interface IAssessmentServiceLocal {

    void add(Assessment assessment);
    void update(Assessment assessment);
    void remove(Assessment assessment);
    List <Assessment> all();
    Assessment getUnique(int quiz_id, int postulation_id);
    List <Assessment> getByQuiz(int quiz_id) ;
    List <Assessment> getByPostulation(int post_id);
    void calculateResult(Assessment assessment,int[] ids);
}

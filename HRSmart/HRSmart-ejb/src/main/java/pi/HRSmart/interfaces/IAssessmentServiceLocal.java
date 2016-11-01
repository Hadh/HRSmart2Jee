package pi.HRSmart.interfaces;

import pi.HRSmart.persistence.Assessment;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by alaa on 01/11/16.
 */
@Local
public interface IAssessmentServiceLocal {

    List <Assessment> getByQuiz(int quiz_id) ;
    List <Assessment> getByPostulation(int post_id);
}

package pi.HRSmart.interfaces;

import pi.HRSmart.persistence.Choice;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by alaa on 20/10/16.
 */
@Local
public interface IChoiceServiceLocal {

    Choice add(Choice choice);
    Choice update(Choice choice);
    List<Choice> remove(Choice choice);
    Choice get(int id);
    List<Choice> all();
    List<Choice> getMultiple(int[] ids);
}

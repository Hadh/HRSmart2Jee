package pi.HRSmart.interfaces;

import java.util.List;
import javax.ejb.Local;
import pi.HRSmart.persistence.User;

/**
 * Created by hadhemi on 10/14/2016.
 */

@Local
public interface IUserLocal {
     void add(User user);
     void update(User user);
     void delete(User user);
     User getUser(int id);
     List<User> getAllUsers();
}

package pi.HRSmart.interfaces;



import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserBuisness;

@Remote
public interface UserServiceRemote {

    public User get(int id);
    public User getFull(int id);
    public void update(User user);
    public void delete(User user);
    public String authenticate(String Login,String password);
    public boolean checkConnectedUser(User userToVerify);
    public User getUserByEmail(String login);
    public String addUser(User user);
    public List<User> getUserByBuisness(int idBuisness);
    public List<User> getAll() ;
    public User TokenToUser(String token);
    public UserBuisness GetCurrentUserBusiness(User user) ;
    
}

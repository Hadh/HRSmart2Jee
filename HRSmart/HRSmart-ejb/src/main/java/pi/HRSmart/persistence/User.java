package pi.HRSmart.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {

	
	private int Id;
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private List<UserBuisness> userBuisness;

	private List <UserSkill> userSkills;
	private List<Notification> userNotifications;

	private List<Postulation> postulations;
	
	public User() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@OneToMany(mappedBy="user")
	public List<UserSkill> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}
	@OneToMany(mappedBy = "id.user")
	public List<UserBuisness> getUserBuisness() {
		return userBuisness;
	}
	public void setUserBuisness(List<UserBuisness> userBuisness) {
		this.userBuisness = userBuisness;
	}

	@OneToMany(mappedBy="user")
	public List<Notification> getUserNotifications() {
		return userNotifications;
	}

	public void setUserNotifications(List<Notification> userNotifications) {
		this.userNotifications = userNotifications;
	}

	@OneToMany(mappedBy = "postulant")
	public List<Postulation> getPostulations() {
		return postulations;
	}

	public void setPostulations(List<Postulation> postulations) {
		this.postulations = postulations;
	}
}

package pi.HRSmart.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

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
	private String login; /*email*/
	private String password;
	private String adresse;
	private String numTel;
	private Date dateInscription;
	private boolean active;
	private String facebook;
	private String linkedin;
	private String picture;
	private String twitter;
	private String skype;
	private String sexe;
	private int age;
	private List <UserSkill> userSkills;
	private List<Notification> userNotifications;


	public User() {
		super();
	}
	public User(int id) {
		super();
		this.Id=id;
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
	@OneToMany(mappedBy="id.user")
	public List<UserSkill> getUserSkills() {
		return userSkills;
	}
	
	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}
	@OneToMany(mappedBy="id.user")
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

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public User(int id, String firstName, String lastName, String login, String password, String adresse, String numTel, int age) {
		Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.adresse = adresse;
		this.numTel = numTel;
		this.age = age;
	}
	public Date getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	
	
}

package pi.HRSmart.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import pi.HRSmart.persistence.Certificat;
import pi.HRSmart.persistence.Skill;
import pi.HRSmart.persistence.User;

/**
 * Entity implementation class for Entity: UserSkill
 *
 */
@Entity

public class UserSkill implements Serializable {

	private int level;

	private int id;
	private static final long serialVersionUID = 1L;
	private User user;
	private List<Certificat> certificats;
	private Skill skill;

	public UserSkill() {
		super();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToMany(mappedBy = "userSkills")
	public List<Certificat> getCertificats() {
		return certificats;
	}
	public void setCertificats(List<Certificat> certificats) {
		this.certificats = certificats;
	}

	@ManyToOne
	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}

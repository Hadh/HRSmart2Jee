package pi.HRSmart.persistence;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
/**
 * @author yesmine
 *
 *
 */

@Entity

public class Certificat implements Serializable {

	   
	
	private int id;
	private String name;
	private static final long serialVersionUID = 1L;
	private Skill skill;
	private List<UserSkill> userSkills;
	private List<JobSkill> jobSkills;
	public Certificat() {
		super();
	} 
	
	

	public Certificat(int id) {
		super();
		this.id = id;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	@ManyToMany(mappedBy = "certificats")
	public List<UserSkill> getUserSkills() {
		return userSkills;
	}
	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}


	@ManyToMany(mappedBy = "certificats")
	public List<JobSkill> getJobSkills() {
		return jobSkills;
	}

	public void setJobSkills(List<JobSkill> jobSkills) {
		this.jobSkills = jobSkills;
	}
	
   
}

package pi.HRSmart.persistence;

import java.io.Serializable;
import javax.persistence.*;

import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.Skill;

/**
 * Entity implementation class for Entity: JobSkill
 *
 * @author Khaled Romdhane
 *
 */

@Entity

public class JobSkill implements Serializable {

	   
	
	private JobSkillPk id;
	private boolean hasQuiz;
	private int level;
	private static final long serialVersionUID = 1L;

	public JobSkill() {
		super();
	}  
	@EmbeddedId
	public JobSkillPk getId() {
		return id;
	}

	public void setId(JobSkillPk id) {
		this.id = id;
	}

	public boolean getHasQuiz() {
		return this.hasQuiz;
	}

	public void setHasQuiz(boolean hasQuiz) {
		this.hasQuiz = hasQuiz;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	@Transient
	public JobOffer getJobOffer() {
		return this.getId().getJobOffer();
	}
	public void setJobOffer(JobOffer jobOffer) {
		this.getId().setJobOffer(jobOffer);
	}
	@Transient
	public Skill getSkill() {
		return this.getId().getSkill();
	}
	public void setSkill(Skill skill) {
		this.getId().setSkill(skill);
	}
	
	
   
}

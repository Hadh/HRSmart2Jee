package pi.HRSmart.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.Stage;

/**
 * Entity implementation class for Entity: Rewards
 *
 * @author Khaled Romdhane
 *
 */

@Entity
@Table(name ="rewards")
@AssociationOverrides({
        @AssociationOverride(name ="id.stage",
                joinColumns = @JoinColumn(name ="stage_id",
                insertable = false, updatable = false
                )),
        @AssociationOverride(name ="id.jobOffer",
                joinColumns = @JoinColumn(name ="jobOffer_id",
                        insertable = false, updatable = false
                ))
})

public class Rewards implements Serializable {

	private RewardsPk id;
	private int value;
	private static final long serialVersionUID = 1L;

	private List<Postulation> postulations;
	private List <Report> reports = new ArrayList<>();
	public Rewards() {
		super();
	}

 
	@EmbeddedId
	public RewardsPk getId() {
		return id;
	}

	public void setId(RewardsPk id) {
		this.id = id;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	@Transient
	public JobOffer getJobOffer() {
		return this.getId().getJobOffer();
	}
	public void setJobOffer(JobOffer jobOffer) {
		this.getId().setJobOffer(jobOffer);
	}

	@Transient
	public Stage getStage() {
		return this.getId().getStage();
	}
	public void setStage(Stage stage) {
		this.getId().setStage(stage);
	}
	
	@OneToMany(mappedBy = "reward")
	public List<Postulation> getPostulations() {
		return postulations;
	}

	public void setPostulations(List<Postulation> postulations) {
		this.postulations = postulations;
	}

	@OneToMany(mappedBy = "rewards")
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((postulations == null) ? 0 : postulations.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rewards other = (Rewards) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (postulations == null) {
			if (other.postulations != null)
				return false;
		} else if (!postulations.equals(other.postulations))
			return false;
		if (value != other.value)
			return false;
		return true;
	}
	
}

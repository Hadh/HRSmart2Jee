/**
 * @author Khaled Romdhane
 *
 */
package pi.HRSmart.persistence;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * @author Khaled Romdhane
 *
 */

@Embeddable
public class RewardsPk implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private JobOffer jobOffer;
	private Stage stage;
	
	@ManyToOne
	public JobOffer getJobOffer() {
		return jobOffer;
	}
	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}
	@ManyToOne
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobOffer == null) ? 0 : jobOffer.hashCode());
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
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
		RewardsPk other = (RewardsPk) obj;
		if (jobOffer == null) {
			if (other.jobOffer != null)
				return false;
		} else if (!jobOffer.equals(other.jobOffer))
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		return true;
	}
	
	
	

}

package pi.HRSmart.persistence;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Buisness
 *
 */
@Entity

public class Buisness implements Serializable {

	   
	
	private int id;
	private String name;
	private List<JobOffer> jobOffers;
	private  List<UserBuisness> userBuisness;
	private static final long serialVersionUID = 1L;

	public Buisness() {
		super();
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
	@OneToMany(mappedBy="buisness")
	public List<UserBuisness> getUserBuisness() {
		return userBuisness;
	}
	public void setUserBuisness(List<UserBuisness> userBuisness) {
		this.userBuisness = userBuisness;
	}
	
	@OneToMany(mappedBy="buisness")
	public List<JobOffer> getJobOffers() {
		return jobOffers;
	}
	public void setJobOffers(List<JobOffer> jobOffers) {
		this.jobOffers = jobOffers;
	}
}

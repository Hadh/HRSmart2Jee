package pi.HRSmart.persistence;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Stage
 *
 * @author Khaled Romdhane
 *
 */

@Entity

public class Stage implements Serializable {

	   
	
	private int id;
	private String name;
	private Buisness buisness;
	public List<Rewards> rewards;
	private static final long serialVersionUID = 1L;
	
	public Stage() {
		super();
	}
	public Stage(int id)
	{
		super();
		this.id=id;
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
	public Buisness getBuisness() {
		return buisness;
	}
	public void setBuisness(Buisness buisness) {
		this.buisness = buisness;
	}
	
	@OneToMany(mappedBy="id.stage")
	public List<Rewards> getRewards() {
		return rewards;
	}
	public void setRewards(List<Rewards> rewards) {
		this.rewards = rewards;
	}
	
	
	
}

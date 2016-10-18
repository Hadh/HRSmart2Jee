package pi.HRSmart.persistence;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.User;

/**
 * @author yesmine
 *
 */
@Entity

public class UserBuisness implements Serializable {

	   
	
	private int id;
	private User user;
	private String role;
	private int salary;
	private Date hireDate;
	private static final long serialVersionUID = 1L;
	private Buisness buisness;
	

	public UserBuisness() {
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
	@ManyToOne
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}   
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}   
	public int getSalary() {
		return this.salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}   
	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	@ManyToOne
	public Buisness getBuisness() {
		return buisness;
	}
	public void setBuisness(Buisness buisness) {
		this.buisness = buisness;
	}
   
	
}

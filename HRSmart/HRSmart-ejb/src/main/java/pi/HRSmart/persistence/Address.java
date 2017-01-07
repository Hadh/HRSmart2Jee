package pi.HRSmart.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Address implements Serializable{

	private int id;
	private String localisation;
	private double x;
	private double y;
	private Buisness buisness;
	private static final long serialVersionUID = 1L;
	
	public Address() {
		super();
	}
	
	public Address(int id, String localisation, int x, int y) {
		super();
		this.id = id;
		this.localisation = localisation;
		this.x = x;
		this.y = y;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	@ManyToOne
	public Buisness getBuisness() {
		return buisness;
	}

	public void setBuisness(Buisness buisness) {
		this.buisness = buisness;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", localisation=" + localisation + ", x=" + x + ", y=" + y + ", buisness="
				+ buisness + "]";
	}
	
}
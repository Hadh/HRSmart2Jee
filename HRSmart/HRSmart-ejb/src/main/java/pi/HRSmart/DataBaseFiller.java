/**
 * 
 */
package pi.HRSmart;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pi.HRSmart.persistence.*;

/**
 * @author Khaled Romdhane
 *
 */

@Singleton
@Startup
public class DataBaseFiller {

	@PersistenceContext(unitName = "HRSmart-ejb")
	EntityManager em;

	// this method will be executed everytime the project is deployed
	// put all entities you want in the database here

	@PostConstruct
	public void init() {
		// Businesses
		Buisness buisness1 = new Buisness();
		buisness1.setName("Bob Corp");
		em.persist(buisness1);

		// Skills
		Skill s1 = new Skill();
		s1.setName("PHP");
		Skill s2 = new Skill();
		s2.setName("JAVA");
		Skill s3 = new Skill();
		s3.setName("HTML");
		Skill s4 = new Skill();
		s4.setName("JavaScript");

		em.persist(s1);
		em.persist(s2);
		em.persist(s3);
		em.persist(s4);

		// Jobs

		JobOffer job1 = new JobOffer();
		job1.setBuisness(buisness1);
		job1.setTitle("Web Dev");
		job1.setDescription("We require a web dev for PHP and HTML i'm stupid");
		
		em.persist(job1);

		// Job1 Skills
		JobSkill js1 = new JobSkill();
		js1.setJobOffer(job1);
		js1.setSkill(s1);
		js1.setLevel(10);

		JobSkill js2 = new JobSkill();
		js2.setJobOffer(job1);
		js2.setSkill(s3);
		js2.setLevel(8);
		
		em.persist(js1);
		em.persist(js2);

		// User
		User user1 = new User();
		user1.setFirstName("Bob");
		user1.setLastName("El Hechmi");
		
		em.persist(user1);

		// User 1 Skills
		UserSkill us1 = new UserSkill();
		us1.setSkill(s1);
		us1.setUser(user1);
		us1.setLevel(10);

		UserSkill us2 = new UserSkill();
		us2.setSkill(s3);
		us2.setUser(user1);
		us2.setLevel(8);
		
		em.persist(us1);
		em.persist(us2);

		// User 1 Buisness
		UserBuisness ub = new UserBuisness();
		ub.setBuisness(buisness1);
		ub.setSalary(1000);
		ub.setUser(user1);
		ub.setRole("HR");
		
		em.persist(ub);

		Rewards r = new Rewards();
		r.setJobOffer(job1);
		r.setValue(0);
		em.persist(r);

		// Postulation

		Postulation ps = new Postulation();
		ps.setPostulant(user1);
		ps.setReward(r);
		em.persist(ps);
		em.flush();
	}
}

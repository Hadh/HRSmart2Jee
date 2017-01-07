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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		buisness1.setValid(false);
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
		job1.setDescription("We require a web dev for PHP and JAVA");
		job1.setSalary(1000);
		job1.setCreationDate(new Date());
		job1.setActive(true);
		em.persist(job1);

		JobOffer job2 = new JobOffer();
		job2.setBuisness(buisness1);
		job2.setTitle("Angular Dev");
		job2.setDescription("We require a web dev for JAVA and Javascript");
		job2.setSalary(1200);
		job2.setCreationDate(new Date());
		job2.setActive(true);
		em.persist(job2);

		///////// Certificats

		Certificat c1 = new Certificat();
		c1.setName("certificat java");
		c1.setSkill(s2);
		em.persist(c1);

		// Job1 Skills
		JobSkill js1 = new JobSkill();
		JobSkillPk jspk1 = new JobSkillPk();
		jspk1.setJobOffer(em.merge(job1));
		jspk1.setSkill(em.merge(s1));
		js1.setId(jspk1);
		js1.setLevel(10);

		JobSkill js2 = new JobSkill();
		JobSkillPk jspk2 = new JobSkillPk();
		jspk2.setJobOffer(em.merge(job1));
		jspk2.setSkill(em.merge(s2));
		js2.setId(jspk2);
		js2.setLevel(8);

		em.persist(js1);
		em.persist(js2);

		JobSkill js21 = new JobSkill();
		JobSkillPk jspk21 = new JobSkillPk();
		jspk21.setJobOffer(em.merge(job2));
		jspk21.setSkill(em.merge(s4));
		js21.setId(jspk21);
		js21.setLevel(10);

		JobSkill js22 = new JobSkill();
		JobSkillPk jspk22 = new JobSkillPk();
		jspk22.setJobOffer(em.merge(job2));
		jspk22.setSkill(em.merge(s2));
		js22.setId(jspk22);
		js22.setLevel(8);

		em.persist(js21);
		em.persist(js22);

		// User
		User user1 = new User();
		user1.setFirstName("Bob");
		user1.setLastName("El Hechmi");
		user1.setLogin("123");
		user1.setPassword("123456789");
		user1.setDateInscription(new Date());

		em.persist(user1);
		// user2
		User user2 = new User();
		user2.setFirstName("yesmine");
		user2.setLastName("sayah");
		user2.setLogin("2345");
		user2.setPassword("yesmine");
		user2.setDateInscription(new Date());

		em.persist(user2);

		// user 3
		User user3 = new User();
		user3.setFirstName("khaled");
		user3.setLastName("romdhane");
		user3.setLogin("456");
		user3.setPassword("khaled");
		user3.setDateInscription(new Date());

		em.persist(user3);
		// User Buisness

		UserBuisness ub1 = new UserBuisness();
		UserBuisnessPK ubpk1 = new UserBuisnessPK();
		ubpk1.setBuisness(em.merge(buisness1));
		ubpk1.setUser(em.merge(user1));
		ub1.setId(ubpk1);
		ub1.setHireDate(new Date());
		em.persist(ub1);

		UserBuisness ub2 = new UserBuisness();
		UserBuisnessPK ubpk2 = new UserBuisnessPK();
		ubpk2.setBuisness(em.merge(buisness1));
		ubpk2.setUser(em.merge(user2));
		ub2.setHireDate(new Date());
		ub2.setId(ubpk2);

		em.persist(ub2);

		UserBuisness ub3 = new UserBuisness();
		UserBuisnessPK ubpk3 = new UserBuisnessPK();
		ubpk3.setBuisness(em.merge(buisness1));
		ubpk3.setUser(em.merge(user3));
		ub3.setHireDate(new Date());
		ub3.setId(ubpk3);

		em.persist(ub3);

		// User 1 Skills
		UserSkill us1 = new UserSkill();
		UserSkillPk uspk1 = new UserSkillPk();
		uspk1.setSkill(em.merge(s1));
		uspk1.setUser(em.merge(user1));
		us1.setId(uspk1);
		us1.setLevel(10);
		List<Certificat> listCert = new ArrayList<Certificat>();
		listCert.add(c1);
		us1.setCertificats(listCert);
		em.persist(us1);

		UserSkill us2 = new UserSkill();
		UserSkillPk uspk2 = new UserSkillPk();
		uspk2.setSkill(em.merge(s1));
		uspk2.setUser(em.merge(user2));
		us2.setId(uspk2);
		us2.setLevel(9);
		em.persist(us2);

		UserSkill us3 = new UserSkill();
		UserSkillPk uspk3 = new UserSkillPk();
		uspk3.setSkill(em.merge(s1));
		uspk3.setUser(em.merge(user3));
		us3.setId(uspk3);
		us3.setLevel(9);
		em.persist(us3);

		UserSkill us4 = new UserSkill();
		UserSkillPk uspk4 = new UserSkillPk();
		uspk4.setSkill(em.merge(s2));
		uspk4.setUser(em.merge(user3));
		us4.setId(uspk4);
		us4.setLevel(7);
		em.persist(us4);

		/*
		 * UserSkill us2 = new UserSkill(); us2.setSkill(s3);
		 * us2.setUser(user1); us2.setLevel(8);
		 * 
		 * 
		 * em.persist(us2); // User 1 Buisness UserBuisness ub = new
		 * UserBuisness(); ub.setBuisness(buisness1); ub.setSalary(1000);
		 * ub.setUser(user1); ub.setRole("HR");
		 * 
		 * em.persist(ub);
		 */
		// Adding Questions
		Question q = new Question();
		q.setBody("Question one");
		q.setSkill(s1);

		em.persist(q);

		// adding choices
		Choice choice = new Choice();
		choice.setBody("Choice one");

		choice.setMark(5);
		choice.setQuestion(em.merge(q));

		em.persist(choice);

		// adding quiz

		Quiz quiz = new Quiz();
		quiz.setDescription("blablabla");
		quiz.setDuration(30);
		quiz.setQuestions(new ArrayList<Question>() {
			{
				add(em.merge(q));
			}
		});

		em.persist(quiz);

		// Reward
		Stage st = new Stage();
		st.setBuisness(em.merge(buisness1));
		st.setName("Link Click");
		em.persist(st);
		em.merge(st);
		em.refresh(job1);
		Rewards re = new Rewards();
		RewardsPk pk = new RewardsPk();
		pk.setJobOffer(job1);
		pk.setStage(st);
		re.setValue(0);
		re.setId(pk);
		em.persist(re);

		// Reward 2
		Stage st2 = new Stage();
		st2.setBuisness(em.merge(buisness1));
		st2.setName("Application Accepted");
		em.persist(st2);
		em.merge(st2);
		em.refresh(job1);
		Rewards re2 = new Rewards();
		RewardsPk pk2 = new RewardsPk();
		pk2.setJobOffer(job1);
		pk2.setStage(st2);
		re2.setValue(100);
		re2.setId(pk2);
		em.persist(re2);

		// Postulation

		Postulation p = new Postulation();
		p.setPostulant(user1);
		p.setReward(em.merge(re));
		em.persist(p);

		// :3 hhhh
		Assessment ass = new Assessment();
		ass.setQuiz(em.merge(quiz));
		ass.setPostulation(em.merge(p));

		em.persist(ass);

		em.flush();

	}
}
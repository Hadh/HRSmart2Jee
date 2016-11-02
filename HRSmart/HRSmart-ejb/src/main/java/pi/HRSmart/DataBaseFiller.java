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
		job1.setDescription("We require a web dev for PHP and JAVA");
		job1.setSalary(1000);
		job1.setActive(true);
		em.persist(job1);
		
		JobOffer job2 = new JobOffer();
		job2.setBuisness(buisness1);
		job2.setTitle("Angular Dev");
		job2.setDescription("We require a web dev for JAVA and Javascript");
		job2.setSalary(1200);
		job2.setActive(true);
		em.persist(job2);
		
		/////////Certificats
	
		Certificat c1 =new Certificat();
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
		
		
		em.persist(user1);
		
		// User 1 Skills
		UserSkill us1 = new UserSkill();
		UserSkillPk uspk1 = new UserSkillPk();
		uspk1.setSkill(em.merge(s1));
		uspk1.setUser(em.merge(user1));
		us1.setId(uspk1);
		us1.setLevel(10);
		em.persist(us1);
		/*
		UserSkill us2 = new UserSkill();
		us2.setSkill(s3);
		us2.setUser(user1);
		us2.setLevel(8);
		
		
		em.persist(us2);

		// User 1 Buisness
		UserBuisness ub = new UserBuisness();
		ub.setBuisness(buisness1);
		ub.setSalary(1000);
		ub.setUser(user1);
		ub.setRole("HR");
		
		em.persist(ub);
*/
		//Adding Questions
		Question q = new Question();
		q.setBody("Question one");
		q.setSkill(s1);

		em.persist(q);

		//adding choices
		Choice choice = new Choice();
		choice.setBody("Choice one");
		choice.setCorrect(true);
		choice.setMark(5);
		choice.setQuestion(em.merge(q));

		em.persist(choice);

		//adding quiz

		Quiz quiz = new Quiz();
		quiz.setDescription("blablabla");
		quiz.setDuration(30);
		quiz.setQuestions(new ArrayList<Question>(){{
			add(em.merge(q));
		}});

		em.persist(quiz);

		//Reward
		Stage st = new Stage();
		st.setBuisness(em.merge(buisness1));
		st.setName("stage 1");
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

/**
 * @author Khaled Romdhane
 *
 */
package HRSmartRessources;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.JobName;
import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import pi.HRSmart.persistence.Address;
import pi.HRSmart.persistence.*;
import pi.HRSmart.persistence.Stage;
import pi.HRSmart.persistence.UserBuisness;

import pi.HRSmart.persistence.*;

/**
 * @author Khaled Romdhane
 *
 */
public class JsonConverter {

	public static String ConvertJobFull(JobOffer job) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = jobNode(job);
		main.put("buisness", buisnessNode(job.getBuisness()));

		ArrayNode rewards = mapper.createArrayNode();

		for (Rewards r : job.getRewards()) {
			rewards.add(rewardsNode(r));
		}
		main.put("rewards", rewards);

		ArrayNode jobSkills = mapper.createArrayNode();

		for (JobSkill js : job.getJobSkills()) {
			ObjectNode jobSkill = jobSkillNode(js);
			ObjectNode skill = skillNode(js.getSkill());
			jobSkill.put("skill", skill);
			jobSkills.add(jobSkill);
		}

		main.put("jobSkills", jobSkills);
		return main.toString();
	}

	public static String ConvertJobList(List<JobOffer> list) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode jobs = mapper.createArrayNode();
		for (JobOffer j : list) {
			ObjectNode job = jobNode(j);
			job.put("buisness", buisnessNode(j.getBuisness()));
			jobs.add(job);
		}
		main.put("jobs", jobs);
		return main.toString();
	}

	public static String ConvertUser(User user) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();

		main.put("id", user.getId());
		main.put("firstName", user.getFirstName());
		main.put("lastName", user.getLastName());
		main.put("adresse", user.getAdresse());
		main.put("numTel", user.getNumTel());
		main.put("login", user.getLogin());
		main.put("password", user.getPassword());
		main.put("age", user.getAge());
		main.put("facebook", user.getFacebook());
		main.put("linkedin", user.getLinkedin());
		main.put("skype", user.getSkype());
		main.put("tweeter", user.getTwitter());
		main.put("picture", user.getPicture());

		ArrayNode UserBuisnesses = mapper.createArrayNode();

		for (UserBuisness bs : user.getUserBuisness()) {

			ObjectNode userBusiness = mapper.createObjectNode();
			
			userBusiness.put("role", bs.getRole());
			userBusiness.put("salary", bs.getSalary());
			userBusiness.put("hiredate", bs.getHireDate().toString());

			ObjectNode business = mapper.createObjectNode();
			business.put("id", bs.getBuisness().getId());
			business.put("name", bs.getBuisness().getName());
			userBusiness.put("Business", business);
			UserBuisnesses.add(userBusiness);
		}

		main.put("UserBuisnesses", UserBuisnesses);
		return main.toString();
	}
	
	public static String ConvertUserProfile(User user) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();

		main.put("id", user.getId());
		main.put("firstName", user.getFirstName());
		main.put("lastName", user.getLastName());
		main.put("adresse", user.getAdresse());
		main.put("numTel", user.getNumTel());
		main.put("login", user.getLogin());
		main.put("password", user.getPassword());
		main.put("age", user.getAge());
		main.put("facebook", user.getFacebook());
		main.put("linkedin", user.getLinkedin());
		main.put("skype", user.getSkype());
		main.put("twitter", user.getTwitter());
		main.put("picture", user.getPicture());
		
		return main.toString();
	}

	public static String ConvertListUser(List<User> userList) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode users = mapper.createArrayNode();

		for (User u : userList) {
			ObjectNode user = mapper.createObjectNode();
			user.put("id", u.getId());
			user.put("firstName", u.getFirstName());
			user.put("lastName", u.getLastName());
			user.put("adresse", u.getAdresse());
			user.put("numTel", u.getNumTel());
			user.put("login", u.getLogin());
			user.put("password", u.getPassword());
			users.add(user);
		}
		main.put("users", users);
		return main.toString();
	}

	public static String ConvertListCertificat(List<Certificat> list) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode certificats = mapper.createArrayNode();
		for (Certificat c : list) {
			ObjectNode cert = mapper.createObjectNode();
			cert.put("id", c.getId());
			cert.put("name", c.getName());
			ObjectNode skill = mapper.createObjectNode();
			skill.put("id", c.getSkill().getId());
			skill.put("name", c.getSkill().getName());
			// list.addAll(s.getCertificats());
			cert.put("skill", skill);
			certificats.add(cert);
		}
		main.put("certificats", certificats);
		return main.toString();
	}

	public static String ConvertListBuisness(List<Buisness> list) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode buisnesses = mapper.createArrayNode();
		for (Buisness b : list) {
			ObjectNode buis = mapper.createObjectNode();
			buis.put("id", b.getId());
			buis.put("name", b.getName());
			buisnesses.add(buis);
		}
		main.put("buisnesses", buisnesses);
		return main.toString();
	}

	public static String ConvertListBuisnessFull(List<Buisness> listBuisness) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode Buisnessz = mapper.createArrayNode();

		for (Buisness buisness : listBuisness) {
			ObjectNode Buisness = mapper.createObjectNode();
			Buisness.put("id", buisness.getId());
			Buisness.put("name", buisness.getName());

			ArrayNode Stages = mapper.createArrayNode();
			for (Stage stage : buisness.getStages()) {
				ObjectNode Stage = mapper.createObjectNode();
				Stage.put("id", stage.getId());
				Stage.put("name", stage.getName());
				Stages.add(Stage);
			}
			Buisness.put("Stages", Stages);

			ArrayNode Address = mapper.createArrayNode();
			for (Address address : buisness.getAddress()) {
				ObjectNode Adresse = mapper.createObjectNode();
				Adresse.put("id", address.getId());
				Adresse.put("name", address.getLocalisation());
				Address.add(Adresse);
			}
			Buisness.put("Address", Address);

			ArrayNode Jobs = mapper.createArrayNode();
			for (JobOffer job : buisness.getJobOffers()) {
				ObjectNode Job = mapper.createObjectNode();
				Job.put("id", job.getId());
				Job.put("name", job.getTitle());
				Jobs.add(Job);
			}
			Buisness.put("Jobs", Jobs);
/*
			ArrayNode Users = mapper.createArrayNode();
			for (UserBuisness u : buisness.getUserBuisness()) {
				ObjectNode User = mapper.createObjectNode();
				User.put("id", u.getId().getUser().getId());
				User.put("name", u.getRole());
				Users.add(User);
			}
			
			Buisness.put("Users", Users);
*/
			Buisnessz.add(Buisness);
		}
		main.put("buisness", Buisnessz);
		return main.toString();
	}

	public static String ConvertBuisness(Buisness buisness) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();

		ObjectNode Buisness = mapper.createObjectNode();
		Buisness.put("id", buisness.getId());
		Buisness.put("name", buisness.getName());

		ArrayNode Stages = mapper.createArrayNode();
		for (Stage stage : buisness.getStages()) {
			ObjectNode Stage = mapper.createObjectNode();
			Stage.put("id", stage.getId());
			Stage.put("name", stage.getName());
			Stages.add(Stage);
		}
		Buisness.put("Stages", Stages);

		ArrayNode Address = mapper.createArrayNode();
		for (Address address : buisness.getAddress()) {
			ObjectNode Adresse = mapper.createObjectNode();
			Adresse.put("id", address.getId());
			Adresse.put("name", address.getLocalisation());
			Address.add(Adresse);
		}
		Buisness.put("Address", Address);

		ArrayNode Jobs = mapper.createArrayNode();
		for (JobOffer job : buisness.getJobOffers()) {
			ObjectNode Job = mapper.createObjectNode();
			Job.put("id", job.getId());
			Job.put("name", job.getTitle());
			Jobs.add(Job);
		}
		Buisness.put("Jobs", Jobs);

		ArrayNode Users = mapper.createArrayNode();
		for (UserBuisness u : buisness.getUserBuisness()) {
			ObjectNode User = mapper.createObjectNode();
			User.put("id", u.getId().getUser().getId());
			User.put("name", u.getRole());
			Users.add(User);
		}
		Buisness.put("Users", Users);

		main.put("buisness", Buisness);

		return main.toString();
	}

	public static String ConvertUserBusiness(UserBuisness usb) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode buisnesses = mapper.createArrayNode();
		ObjectNode buis = mapper.createObjectNode();
		buis.put("id", usb.getBuisness().getId());
		buis.put("name", usb.getBuisness().getName());
		buisnesses.add(buis);

		main.put("buisnesses", buisnesses);

		main.put("id", usb.getId().toString());
		main.put("role", usb.getRole());
		main.put("salary", usb.getSalary());
		main.put("hiredate", usb.getHireDate().toString());
		return main.toString();
	}

	public static String ConvertListUserSkill(List<UserSkill> list) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode userSkills = mapper.createArrayNode();
		for (UserSkill us : list) {
			ObjectNode userSkill = mapper.createObjectNode();

			ObjectNode skillnode = mapper.createObjectNode();
			skillnode.put("id", us.getSkill().getId());
			skillnode.put("name", us.getSkill().getName());
			ObjectNode id = mapper.createObjectNode();
			id.put("skill", skillnode);
			id.put("user", us.getUser().getId());
			userSkill.put("id", id);
			userSkill.put("level", us.getLevel());
			ArrayNode certifs = mapper.createArrayNode();
			for (Certificat c : us.getCertificats()) {
				ObjectNode cert = mapper.createObjectNode();
				cert.put("id", c.getId());
				cert.put("name", c.getName());
				certifs.add(cert);

			}
			userSkill.put("certificats", certifs);
			userSkills.add(userSkill);
		}
		main.put("userSkills", userSkills);
		return main.toString();
	}

	public static String convertListUsersByBuisness(List<User> listUser) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode users = mapper.createArrayNode();
		for (User u : listUser) {
			ObjectNode user = mapper.createObjectNode();
			user.put("id", u.getId());
			user.put("first name", u.getFirstName());
			user.put("last name", u.getLastName());
			users.add(user);
		}
		main.put("users", users);
		return main.toString();
	}

	public static String convertListStagesByBuisness(List<Stage> listStage) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode stages = mapper.createArrayNode();
		for (Stage s : listStage) {
			ObjectNode stage = mapper.createObjectNode();
			stage.put("id", s.getId());
			stage.put("name", s.getName());
			stages.add(stage);
		}
		main.put("stages", stages);
		return main.toString();
	}

	// return list of userbuisnesses (userBuisnesses/buisnesses)
	public static String ConvertUserBusinessList(List<UserBuisness> list) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode userbuisnesseses = mapper.createArrayNode();
		for (UserBuisness ub : list) {
			ObjectNode userBuisness = mapper.createObjectNode();
			ObjectNode buisness = mapper.createObjectNode();
			buisness.put("id", ub.getBuisness().getId());
			buisness.put("name", ub.getBuisness().getName());
			ObjectNode id = mapper.createObjectNode();
			id.put("buisnesses", buisness);
			id.put("user", ub.getUser().getId());
			userBuisness.put("id", id);
			userBuisness.put("role", ub.getRole());
			userBuisness.put("hireDate", ub.getHireDate().toString());
			userBuisness.put("salary", ub.getSalary());
			userbuisnesseses.add(userBuisness);
		}
		main.put("userBuisnesses", userbuisnesseses);
		return main.toString();
	}

	public static String convertListAddress(List<Address> listAddress) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode addressAll = mapper.createArrayNode();
		for (Address a : listAddress) {
			ObjectNode address = mapper.createObjectNode();
			address.put("id", a.getId());
			address.put("localisation", a.getLocalisation());
			address.put("x", a.getX());
			address.put("y", a.getY());
			addressAll.add(address);
		}
		main.put("address", addressAll);
		return main.toString();
	}

	public static ArrayNode convertQuestion(List<Question> questions) {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode qs = mapper.createArrayNode();

		for (Question q : questions) {
			ObjectNode question = mapper.createObjectNode();
			qs.add(convertQuestion(q));
		}
		return qs;
	}
	public static ObjectNode convertQuestion(Question question) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode qs = mapper.createObjectNode();
		qs.put("id", question.getId());
		qs.put("body", question.getBody());
		if(question.getSkill() != null)
			qs.put("skill", convertSkill(question.getSkill()));
		if(question.getChoices()!=null)
			qs.put("choices",convertChoices(question.getChoices()));
		return qs;
	}
	public static ObjectNode convertSkill(Skill skill){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode s = mapper.createObjectNode();
		s.put("id",skill.getId());
		s.put("name",skill.getName());

		return s;
	}

	public static ObjectNode convertChoice(Choice choice){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode ch = mapper.createObjectNode();
		ch.put("id",choice.getId());
		ch.put("body",choice.getBody());
		ch.put("mark",choice.getMark());

		return ch;
	}

	public static ArrayNode convertChoices(List <Choice> choices){
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode chs = mapper.createArrayNode();

		for(Choice choice: choices){
			chs.add(convertChoice(choice));
		}
		return chs;
	}
	public static ObjectNode ConvertAssessment(Assessment assessment){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode ass = mapper.createObjectNode();
		ass.put("result",assessment.getResult());
		ass.put("id_postulation",assessment.getPostulation().getIdPostulation());
		ass.put("id_quiz",assessment.getQuiz().getId());

		return ass;
	}

	public static ArrayNode ConvertAssessment(List <Assessment> assessments) {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode ass = mapper.createArrayNode();
		assessments.stream().forEach(assessment -> {
			ass.add(ConvertAssessment(assessment));
		});
		return ass;
	}
	public static ObjectNode mainNode(){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();

		return main;
	}


	public static ObjectNode jobNode(JobOffer job) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		main.put("id", job.getId());
		main.put("description", job.getDescription());
		main.put("salary", job.getSalary());
		main.put("title", job.getTitle());
		main.put("creationDate", job.getCreationDate().toString());
		return main;
	}

	public static ObjectNode buisnessNode(Buisness buisness) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		main.put("id", buisness.getId());
		main.put("name", buisness.getName());
		return main;
	}

	public static ObjectNode rewardsNode(Rewards reward) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ObjectNode id = mapper.createObjectNode();
		id.put("jobOffer", reward.getJobOffer().getId());
		ObjectNode stage = mapper.createObjectNode();
		stage.put("id", reward.getStage().getId());
		stage.put("name", reward.getStage().getName());
		id.put("stage", stage);
		main.put("id", id);
		main.put("value", reward.getValue());
		return main;
	}

	public static ObjectNode jobSkillNode(JobSkill jobSkill) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ObjectNode jobSkillId = mapper.createObjectNode();
		jobSkillId.put("jobOffer", jobSkill.getJobOffer().getId());
		jobSkillId.put("skill", jobSkill.getSkill().getId());
		main.put("id", jobSkillId);
		main.put("level", jobSkill.getLevel());
		ArrayNode certif = mapper.createArrayNode();
		for (Certificat c : jobSkill.getCertificats()) {
			ObjectNode cer = mapper.createObjectNode();
			cer.put("id", c.getId());
			cer.put("name", c.getName());
			certif.add(cer);
		}
		main.put("certificats", certif);

		return main;
	}

	public static ObjectNode skillNode(Skill skill) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		main.put("id", skill.getId());
		main.put("name", skill.getName());
		return main;
	}

	public static String ConvertJobUser(JobOffer job, float comp) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = jobNode(job);
		main.put("buisness", buisnessNode(job.getBuisness()));

		ArrayNode rewards = mapper.createArrayNode();

		for (Rewards r : job.getRewards()) {
			rewards.add(rewardsNode(r));
		}
		main.put("rewards", rewards);

		ArrayNode jobSkills = mapper.createArrayNode();

		for (JobSkill js : job.getJobSkills()) {
			ObjectNode jobSkill = jobSkillNode(js);
			ObjectNode skill = skillNode(js.getSkill());
			jobSkill.put("skill", skill);
			jobSkills.add(jobSkill);
		}

		main.put("jobSkills", jobSkills);
		main.put("compatibility", comp);

		return main.toString();
	}

	public static String ConvertJobUserList(List<JobOffer> jobs, List<Float> comps) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode grand = mapper.createObjectNode();
		ArrayNode JobsArray = mapper.createArrayNode();

		for (JobOffer job : jobs) {
			ObjectNode main = jobNode(job);
			main.put("buisness", buisnessNode(job.getBuisness()));

			main.put("compatibility", comps.get(jobs.indexOf(job)));
			JobsArray.add(main);
		}
		grand.put("jobs", JobsArray);
		return grand.toString();
	}

	public static String ConvertSkillSalaryList(List<Skill> list, List<Float> averages) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode grand = mapper.createObjectNode();
		ArrayNode SkillArray = mapper.createArrayNode();

		for (Skill js : list) {
			ObjectNode main = skillNode(js);
			main.put("salary", averages.get(list.indexOf(js)));
			SkillArray.add(main);
		}
		grand.put("skills", SkillArray);
		return grand.toString();
	}
    public static ArrayNode ConvertQuiz(List <Quiz> quizs){
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode qzs = mapper.createArrayNode();
        quizs.forEach(quiz -> {
            qzs.add(ConvertQuiz(quiz));
        });
        return qzs;
    }

    public static ObjectNode ConvertQuiz(Quiz quiz) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode quizz = mapper.createObjectNode();
        quizz.put("id", quiz.getId());
        quizz.put("duration", quiz.getDuration());
        quizz.put("description", quiz.getDescription());
		if(quiz.getQuestions() != null)
        	quizz.put("questions", convertQuestion(quiz.getQuestions()));
        return quizz;
    }
    
    public static ArrayNode convertSkillList(List<Skill> list){
    	
    	 ObjectMapper mapper = new ObjectMapper();
    	 ArrayNode skills = mapper.createArrayNode();
    	 for (Skill s : list) {
         ObjectNode skill = mapper.createObjectNode();
         skill.put("id", s.getId());
         skill.put("duration", s.getName());
         skills.add(skill);
    	 }
    	 return skills;
    }
    
}

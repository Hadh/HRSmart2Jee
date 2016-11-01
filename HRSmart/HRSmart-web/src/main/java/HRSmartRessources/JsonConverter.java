/**
 * @author Khaled Romdhane
 *
 */
package HRSmartRessources;

import java.util.List;

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

	public static String ConvertJobFull(JobOffer job)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();

		main.put("id", job.getId());
		main.put("description", job.getDescription());
		main.put("salary", job.getSalary());
		main.put("title", job.getTitle());
		
		ObjectNode buisness = mapper.createObjectNode();
		buisness.put("id", job.getBuisness().getId());
		buisness.put("name", job.getBuisness().getName());
		
		main.put("buisness", buisness);
		
		ArrayNode rewards = mapper.createArrayNode();

		for (Rewards r : job.getRewards()) {
			ObjectNode reward = mapper.createObjectNode();
			reward.put("jobOffer", r.getJobOffer().getId());
			reward.put("stage", r.getStage().getId());
			reward.put("value", r.getValue());
			rewards.add(reward);
		}
		main.put("rewards", rewards);
		
		ArrayNode jobSkills = mapper.createArrayNode();
		
		for (JobSkill js : job.getJobSkills()) {
			ObjectNode jobSkill = mapper.createObjectNode();
			ObjectNode jobSkillId = mapper.createObjectNode();
			jobSkillId.put("jobOffer", js.getJobOffer().getId());
			jobSkillId.put("skill", js.getSkill().getId());
			jobSkill.put("id", jobSkillId);
			jobSkill.put("value", js.getLevel());
			ObjectNode skill = mapper.createObjectNode();
			skill.put("id", js.getSkill().getId());
			skill.put("name", js.getSkill().getName());
			jobSkill.put("Skill",skill);
			jobSkills.add(jobSkill);
		}
		
		main.put("jobSkills", jobSkills);
		
		return main.toString();
	}
	
	public static String ConvertJobList(List<JobOffer>list)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode jobs = mapper.createArrayNode();
		for (JobOffer j : list) {
			ObjectNode job = mapper.createObjectNode();
			job.put("id", j.getId());
			job.put("description", j.getDescription());
			job.put("salary", j.getSalary());
			job.put("title", j.getTitle());
			ObjectNode buisness = mapper.createObjectNode();
			buisness.put("id", j.getBuisness().getId());
			buisness.put("name", j.getBuisness().getName());
			job.put("buisness", buisness);
			jobs.add(job);
		}
		main.put("jobs", jobs);
		return main.toString();
	}
	public static String ConvertUser(User user){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();

		main.put("id", user.getId());
		main.put("firstName", user.getFirstName());
		main.put("lastName", user.getLastName());
		main.put("adresse", user.getAdresse());
		main.put("numTel", user.getNumTel());
		main.put("login", user.getLogin());
		main.put("password", user.getPassword());

		ArrayNode UserBuisnesses = mapper.createArrayNode();

		for (UserBuisness bs : user.getUserBuisness()) {

			ObjectNode userBusiness = mapper.createObjectNode();
			userBusiness.put("id", bs.getId().toString());
			userBusiness.put("role", bs.getRole());
			userBusiness.put("salary", bs.getSalary());
			userBusiness.put("hiredate", bs.getHireDate().toString());

			ObjectNode business = mapper.createObjectNode();
			business.put("id", bs.getBuisness().getId());
			business.put("name", bs.getBuisness().getName());
			userBusiness.put("Business",business);
			UserBuisnesses.add(userBusiness);
		}

		main.put("UserBuisnesses", UserBuisnesses);
		return main.toString();
	}

	public static String ConvertListUser(List<User>userList) {
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
    
    public static String ConvertListBuisness(List<Buisness> list){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode main = mapper.createObjectNode();
        ArrayNode buisnesses = mapper.createArrayNode();
        for (Buisness b : list){
            ObjectNode buis = mapper.createObjectNode();
            buis.put("id", b.getId());
            buis.put("name", b.getName());
            buisnesses.add(buis);
        }
        main.put("buisnesses", buisnesses);
        return main.toString();
    }
	
	public static String ConvertListBuisnessFull(List<Buisness> listBuisness){
		
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
			
			ArrayNode Users = mapper.createArrayNode();
			for (UserBuisness u : buisness.getUserBuisness()) {
				ObjectNode User = mapper.createObjectNode();
				User.put("id", u.getId().getUser().getId());
				User.put("name", u.getRole());
				Users.add(User);
			}
			Buisness.put("Users", Users);
			
			Buisnessz.add(Buisness);
		}
		main.put("buisness", Buisnessz);
		return main.toString();
	}

	
		public static String ConvertBuisness(Buisness buisness){
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		//ArrayNode Buisnessz = mapper.createArrayNode();
		
		//for (Buisness buisness : listBuisness) {
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
			
			//Buisnessz.add(Buisness);
		//}
		
		main.put("buisness", Buisness);
		
		return main.toString();
	}

	public static String ConvertUserBusiness(UserBuisness usb){

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

	public static String ConvertQuiz(Quiz quiz){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode questions = mapper.createArrayNode();
		ObjectNode quizz = mapper.createObjectNode();

		quizz.put("id", quiz.getId());
		quizz.put("description",quiz.getDescription());
		quizz.put("duration",quiz.getDuration());

		quizz.put("questions",convertQuestion(quiz.getQuestions()));
		main.put("quiz",quizz);

		return main.toString();
	}
	public static ArrayNode convertQuestion(List <Question> questions){
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode qs = mapper.createArrayNode();

		for(Question q : questions){
			ObjectNode question = mapper.createObjectNode();
			qs.add(convertQuestion(q));
		}
		return qs;
	}
	public static ObjectNode convertQuestion(Question question) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode qs = mapper.createObjectNode();
		qs.put("id",question.getId());
		qs.put("body",question.getBody());

		return qs;
	}


}

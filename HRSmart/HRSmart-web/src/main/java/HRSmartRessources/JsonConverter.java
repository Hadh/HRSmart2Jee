/**
 * @author Khaled Romdhane
 *
 */
package HRSmartRessources;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.Certificat;
import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.JobSkill;
import pi.HRSmart.persistence.Rewards;

/**
 * @author Khaled Romdhane
 *
 */
public class JsonConverter {

	public static String ConvertFull(JobOffer job) {
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
			jobSkill.put("id", js.getId());
			jobSkill.put("value", js.getLevel());
			ObjectNode skill = mapper.createObjectNode();
			skill.put("id", js.getSkill().getId());
			skill.put("name", js.getSkill().getName());
			jobSkill.put("Skill", skill);
			jobSkills.add(jobSkill);
		}

		main.put("jobSkills", jobSkills);

		return main.toString();
	}

	public static String ConvertList(List<JobOffer> list) {
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

}

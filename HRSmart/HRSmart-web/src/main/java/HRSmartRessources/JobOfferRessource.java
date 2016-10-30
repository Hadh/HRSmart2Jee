/**
 * @author Khaled Romdhane
 *
 */
package HRSmartRessources;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.JobSkill;
import pi.HRSmart.persistence.Rewards;

/**
 * @author Khaled Romdhane
 *
 */
@Path("job")
@RequestScoped
public class JobOfferRessource {

	@EJB(beanName = "JobOfferService")
	JobOfferServiceLocal service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getFull(@PathParam("id") int id) {
		JobOffer job = service.getFull(id);

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
			reward.put("id", r.getId());
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
			jobSkill.put("Skill",skill);
			jobSkills.add(jobSkill);
		}
		
		main.put("jobSkills", jobSkills);

		return main.toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		List<JobOffer> list = service.getAll();

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

}

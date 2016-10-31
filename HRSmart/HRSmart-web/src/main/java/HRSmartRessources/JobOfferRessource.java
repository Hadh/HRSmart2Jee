/**
 * @author Khaled Romdhane
 *
 */
package HRSmartRessources;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.mail.iap.Response;

import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.interfaces.JobSkillServiceLocal;
import pi.HRSmart.interfaces.RewardServiceLocal;
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

	@EJB(beanName = "JobSkillService")
	JobSkillServiceLocal jobSkillService;

	@EJB(beanName = "RewardService")
	RewardServiceLocal rewardService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getFull(@PathParam("id") int id) {
		return JsonConverter.ConvertFull(service.getFull(id));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		return JsonConverter.ConvertList(service.getAll());
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addJob(JobOffer job) {
		service.add(job);

		if (job.getRewards() != null) {
			for (Rewards r : job.getRewards()) {
				rewardService.add(r);
			}
		}
		if (job.getJobSkills() != null) {
			for (JobSkill js : job.getJobSkills()) {
				jobSkillService.add(js);
			}
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatejob(JobOffer job) {
		service.update(job);

		if (job.getRewards() != null) {
			for (Rewards r : job.getRewards()) {
				rewardService.update(r);
			}
		}
		if (job.getJobSkills() != null) {
			for (JobSkill js : job.getJobSkills()) {
				jobSkillService.update(js);
			}
		}
	}
	

}

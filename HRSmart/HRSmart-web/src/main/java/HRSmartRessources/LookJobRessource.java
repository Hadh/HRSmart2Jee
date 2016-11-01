/**
 * @author Khaled Romdhane
 *
 */
package HRSmartRessources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.JobSkill;
import pi.HRSmart.persistence.Rewards;

/**
 * @author Khaled Romdhane
 *
 */

@Path("lookJob")
@RequestScoped
public class LookJobRessource {
	@EJB(beanName = "JobOfferService")
	JobOfferServiceLocal jobService;

	@EJB(beanName = "UserService")
	UserServiceLocal userService;
/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		List<JobOffer> list = jobService.getAll();
		List<Float> compJobUser = new ArrayList<Float>();
		for(JobOffer j : list)
		{
			compJobUser.add(jobService.compatibilityJobUser(user, job));
		}
		return JsonConverter.ConvertJobList(list);
	}
*/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getFull(@PathParam("id") int id) {
		return JsonConverter.ConvertJobFull(jobService.getFull(id));
	}
}

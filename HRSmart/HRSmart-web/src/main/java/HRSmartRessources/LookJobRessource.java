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
import pi.HRSmart.persistence.User;

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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{userId}")
	public String getAll(@PathParam("userId") int userId) {
		User user = userService.getFull(userId);
		List<JobOffer> list = jobService.getAllFull();
		List<Float> compJobUser = new ArrayList<Float>();
		for(JobOffer j : list)
		{
			compJobUser.add(jobService.compatibilityJobUser(user, j)*100);
		}
		return JsonConverter.ConvertJobUserList(list,compJobUser);
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{userId}/{id}")
	public String getFull(@PathParam("userId") int userId,@PathParam("id") int id) {
		JobOffer job = jobService.getFull(id);
		User user = userService.getFull(userId);
		float comp = jobService.compatibilityJobUser(user, job)*100;
		return JsonConverter.ConvertJobUser(job,comp);
	}
}

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.auth0.jwt.internal.com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.JobSkill;
import pi.HRSmart.persistence.Rewards;
import pi.HRSmart.persistence.User;
import pi.HRSmart.utilities.Jwt;

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
	public String getAll(@Context HttpHeaders hh) {
		String token = hh.getHeaderString(HttpHeaders.AUTHORIZATION);
		User user = null;
		if (token != null) {
			token = Jwt.decodeJWT(token);
			JsonNode jn = Jwt.stringToJson(token);
			user = userService.getUserByEmail(jn.get("user").asText());
			user = userService.getFull(user.getId());
		}
		List<JobOffer> list = jobService.getAllFull();
		List<Float> compJobUser = new ArrayList<Float>();
		for (JobOffer j : list) {
			if (user != null) {
				compJobUser.add(jobService.compatibilityJobUser(user, j) * 100);
			} else {
				compJobUser.add(0.0f);
			}
		}
		return JsonConverter.ConvertJobUserList(list, compJobUser);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getFull(@Context HttpHeaders hh, @PathParam("id") int id) {

		String token = hh.getHeaderString(HttpHeaders.AUTHORIZATION);
		User user = null;
		if (token != null) {
			token = Jwt.decodeJWT(token);
			JsonNode jn = Jwt.stringToJson(token);
			user = userService.getUserByEmail(jn.get("user").asText());
			user = userService.getFull(user.getId());
		}
		JobOffer job = jobService.getFull(id);
		float comp = 0.0f;
		if (user != null) {
			comp = jobService.compatibilityJobUser(user, job) * 100;
		}
		return JsonConverter.ConvertJobUser(job, comp);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("business/{id}")
	public String getFull(@PathParam("id") int id) {

		List<JobOffer> jobs = jobService.getAllByBuisness(id);
		
		return JsonConverter.ConvertJobList(jobs);
	}
}

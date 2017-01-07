/**
 * @author Khaled Romdhane
 *
 */
package HRSmartRessources;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.auth0.jwt.internal.com.fasterxml.jackson.databind.JsonNode;

import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.interfaces.JobSkillServiceLocal;
import pi.HRSmart.interfaces.RewardServiceLocal;
import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.JobSkill;
import pi.HRSmart.persistence.JobSkillPk;
import pi.HRSmart.persistence.Rewards;
import pi.HRSmart.persistence.RewardsPk;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserBuisness;
import pi.HRSmart.utilities.Jwt;

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

	@EJB(beanName = "UserService")
	UserServiceLocal userService;

	@EJB(beanName = "UserBuisnessService")
	UserBuisnessServiceLocal userBuisnessService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getFull(@PathParam("id") int id) {
		return JsonConverter.ConvertJobFull(service.getFull(id));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		return JsonConverter.ConvertJobList(service.getAll());
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addFullJob(JobOffer job) {
		service.addFull(job);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatejob(JobOffer job) {
		service.update(job);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("salary")
	public Response getAverageJobSalary(JobOffer job) {

		return Response.status(Response.Status.OK).entity(service.getJobSalary(job)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("rm")
	public String getRMJobs(@Context HttpHeaders hh) {
		String token = hh.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (token != null) {
			User user = userService.TokenToUser(token);
			UserBuisness ub = userService.GetCurrentUserBusiness(user);
			return JsonConverter.ConvertUserBusiness(ub);
		}

		return "n";
	}

}

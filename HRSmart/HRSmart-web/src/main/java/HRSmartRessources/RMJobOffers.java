
package HRSmartRessources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import pi.HRSmart.interfaces.BuisnessServiceLocal;
import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserBuisness;

/**
 * @author Khaled Romdhane
 *
 */
@Path("rm")
@RequestScoped
public class RMJobOffers {
	
	@EJB(beanName = "JobOfferService")
	JobOfferServiceLocal service;
	@EJB(beanName = "UserService")
	UserServiceLocal userService;
	@EJB(beanName = "BuisnessService")
	BuisnessServiceLocal buisnessService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRMJobs(@Context HttpHeaders hh) {
		String token = hh.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (token != null) {
			User user = userService.TokenToUser(token);
			UserBuisness ub = userService.GetCurrentUserBusiness(user);
			if(ub!= null){
				if(ub.getRole().equals("RM")){
					return JsonConverter.ConvertJobList(service.getAllByBuisness(ub.getBuisness().getId()));
				}
			}
		}

		return "{}";
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRMJob(@Context HttpHeaders hh,@PathParam("id") int id) {
		String token = hh.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (token != null) {
			User user = userService.TokenToUser(token);
			UserBuisness ub = userService.GetCurrentUserBusiness(user);
			if(ub!= null){
				if(ub.getRole().equals("RM")){
					return JsonConverter.ConvertJobFull(service.getFull(id));
				}
			}
		}

		return "{}";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getRMBusiness(@Context HttpHeaders hh) {
		String token = hh.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (token != null) {
			User user = userService.TokenToUser(token);
			UserBuisness ub = userService.GetCurrentUserBusiness(user);
			if(ub!= null){
				if(ub.getRole().equals("RM")){
					return JsonConverter.ConvertBuisness(buisnessService.get(ub.getBuisness().getId()));
				}
			}
		}
		return "{}";
	}
}

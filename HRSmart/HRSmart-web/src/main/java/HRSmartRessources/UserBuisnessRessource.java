/**
 * 
 */
package HRSmartRessources;

import java.util.Date;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.interfaces.UserSkillsServiceLocal;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserBuisness;
import pi.HRSmart.persistence.UserSkill;
import pi.HRSmart.services.UserBuisnessService;

/**
 * @author yesmine
 *
 */
@Path("userbuisnesses")
@RequestScoped
public class UserBuisnessRessource {

	@EJB(beanName = "UserBuisnessService")
	UserBuisnessServiceLocal userBuisnessService;
	
	@EJB(beanName = "UserService")
	UserServiceLocal userService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adduserBuisness(UserBuisness userbuisness) {
		userbuisness.setHireDate(new Date());
		userBuisnessService.add(userbuisness);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateuserBuisness(UserBuisness userbuisness) {
		userBuisnessService.update(userbuisness);
		return Response.status(Response.Status.OK).build();

	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteuserBuisness(UserBuisness userbuisness) {
		userBuisnessService.remove(userbuisness);
		return Response.status(Response.Status.OK).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getUserBuisnessByUser(@PathParam("id") int id) {
		return Response.status(Response.Status.OK)
				.entity(JsonConverter.ConvertUserBusinessList(userBuisnessService.getByUser(id))).build();
	}

	//get userBuisness connecte
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAlluserByBusiness(@Context HttpHeaders hh) {
		String token = hh.getHeaderString(HttpHeaders.AUTHORIZATION);
		UserBuisness userbuisness = null;
		User user = userService.TokenToUser(token);	
		List<UserBuisness> userBuisnesses = userBuisnessService.getByUser(user.getId());
		for (UserBuisness ub : userBuisnesses){
			if (ub.getRole().equals("HR")){
				userbuisness = ub;
				break;
			}
		}
		String result = "";
		if(userbuisness != null){
			List<UserBuisness> users = userBuisnessService.getByBuisness(userbuisness.getBuisness().getId());
			 
			result = JsonConverter.ConvertUserBusinessList(users);
			
		}
		
		return Response.status(Response.Status.OK).entity(result).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/bestEmployee")
	public Response getBestEmployee(@PathParam("id") int id) {
		return Response.status(Response.Status.OK)
				.entity(JsonConverter.ConvertUser(userBuisnessService.getBestEmployee(id))).build();
	}

}

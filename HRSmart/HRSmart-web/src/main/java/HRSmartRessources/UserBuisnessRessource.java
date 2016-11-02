/**
 * 
 */
package HRSmartRessources;

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
import javax.ws.rs.core.Response;

import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.interfaces.UserSkillsServiceLocal;
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adduserBuisness(UserBuisness userbuisness) {
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
		return Response.status(Response.Status.FOUND)
				.entity(JsonConverter.ConvertUserBusinessList(userBuisnessService.getByUser(id))).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.status(Response.Status.FOUND)
				.entity(JsonConverter.ConvertUserBusinessList(userBuisnessService.getAll())).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/bestEmployee")
	public Response getBestEmployee(@PathParam("id") int id) {
		return Response.status(Response.Status.FOUND)
				.entity(JsonConverter.ConvertUser(userBuisnessService.getBestEmployee(id))).build();
	}

}

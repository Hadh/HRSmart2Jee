package HRSmartRessources;


import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pi.HRSmart.interfaces.BuisnessServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserBuisness;


@Path("buisness")
@RequestScoped
public class BuisnessRessources {
	
	@EJB(beanName = "BuisnessService")
	BuisnessServiceLocal service;

	@EJB(beanName="UserBuisnessService")
	UserBuisnessServiceLocal userBuisnessServiceLocal;

	@EJB(beanName="UserService")
	UserServiceLocal  userServiceLocal;

	
	@EJB(beanName = "UserService")
	UserServiceLocal serviceUser;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuisnessById(@PathParam("id") int id){
		Buisness buisness = service.get(id);
		String result = JsonConverter.ConvertBuisness(buisness);
		return Response.status(Response.Status.OK).entity(result).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBuisness(){
		List<Buisness> bu = service.getAllBuisness();
		String result = JsonConverter.ConvertListBuisnessFull(bu);		
		return Response.status(Response.Status.OK).entity(result).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBuisness(Buisness buisness){
		service.add(buisness);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBuisness(Buisness buisness){
		service.update(buisness);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteBuisness(Buisness buisness){
		service.remove(buisness);
		return Response.status(Response.Status.CREATED).build();
	}
	
	
	//statistic
	@GET
	@Path("/users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsersByBuisness(@PathParam("id") int idBuisness){
		List<User> users = serviceUser.getUserByBuisness(idBuisness);
		String result = JsonConverter.convertListUsersByBuisness(users);
		return Response.status(Response.Status.OK).entity(result).build();
	}
	
	/* this service returns the role */
	@GET
	@Path("/{iduser}/{idbis}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserBuisness(@PathParam("iduser") int iduser,@PathParam("idbis") int idbis){
		String role = userBuisnessServiceLocal.getRoleByUser(iduser,idbis);
		return role;
	}


	/* this service returns the userbusiness based on his id and it has to be with role HR */
	@GET
	@QueryParam("{iduser}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserBusinessByUser(@QueryParam("iduser") int iduser){
		UserBuisness ubs = userBuisnessServiceLocal.getUserBusinessByUser(userServiceLocal.get(iduser));
		String result = JsonConverter.ConvertUserBusiness(ubs);
		return result;
	}
}

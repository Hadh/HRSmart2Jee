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
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pi.HRSmart.interfaces.BuisnessServiceLocal;
import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.Address;
import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.Stage;
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
	
	/*@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteBuisness(Buisness buisness){
		service.remove(buisness);
		return Response.status(Response.Status.CREATED).build();
	}*/
	
	@Path("/{id}")
    @DELETE
    public Response removeAnswer(@PathParam("id") int id) {

        service.remove(service.get(id));
     return Response.status(Response.Status.CREATED).build();
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
	@Path("{iduser}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBusinessByUser(@PathParam("iduser") int iduser){
		UserBuisness ubs = userBuisnessServiceLocal.getUserBusinessByUser(userServiceLocal.get(iduser));
		String result = JsonConverter.ConvertUserBusiness(ubs);
		return Response.status(Response.Status.OK).entity(result).build();
	}


}

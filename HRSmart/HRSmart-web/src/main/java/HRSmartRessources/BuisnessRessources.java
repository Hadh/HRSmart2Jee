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
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuisnessById(@PathParam("id") int id){
		Buisness buisness = service.getById(id);
		return Response.status(Response.Status.OK).entity(buisness).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBuisness(){
		
		List<Buisness> bu = service.getAllBuisness();
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode Buisnessz = mapper.createArrayNode();
		
		for (Buisness buisness : bu) {
			ObjectNode Buisness = mapper.createObjectNode();
			Buisness.put("id", buisness.getId());
			Buisness.put("name", buisness.getName());
			
			ArrayNode Stages = mapper.createArrayNode();
			for (Stage stage : buisness.getStages()) {
				ObjectNode Stage = mapper.createObjectNode();
				Stage.put("id", stage.getId());
				Stage.put("name", stage.getName());
				Stages.add(Stage);
			}
			Buisness.put("Stages", Stages);
			
			ArrayNode Address = mapper.createArrayNode();
			for (Address address : buisness.getAddress()) {
				ObjectNode Adresse = mapper.createObjectNode();
				Adresse.put("id", address.getId());
				Adresse.put("name", address.getLocalisation());
				Address.add(Adresse);
			}
			Buisness.put("Address", Address);
			
			ArrayNode Jobs = mapper.createArrayNode();
			for (JobOffer job : buisness.getJobOffers()) {
				ObjectNode Job = mapper.createObjectNode();
				Job.put("id", job.getId());
				Job.put("name", job.getTitle());
				Jobs.add(Job);
			}
			Buisness.put("Jobs", Jobs);
			
			/*ArrayNode Users = mapper.createArrayNode();
			for (UserBuisness u : buisness.getUserBuisness()) {
				ObjectNode User = mapper.createObjectNode();
				User.put("id", u.getId().getUser().getId());
				User.put("name", u.getRole());
				Users.add(User);
			}
			Buisness.put("Users", Users);*/
			
			Buisnessz.add(Buisness);
		}
		main.put("buisness", Buisnessz);
		
		return Response.status(Response.Status.OK).entity(main.toString()).build();
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
}

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

import pi.HRSmart.interfaces.StageServiceLocal;
import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.Stage;

@Path("stage")
@RequestScoped
public class StagesRessource {
	@EJB(beanName = "StageService")
	StageServiceLocal serviceStage;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStagesByBuisness(@PathParam("id") int idBuisness){
		List<Stage> stages = serviceStage.getAllByBuisness(idBuisness);
		String result = JsonConverter.convertListStagesByBuisness(stages);
		return Response.status(Response.Status.OK).entity(result).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addStage(Stage stage){
		serviceStage.add(stage);
		return Response.status(Response.Status.CREATED).entity("Success").build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStage(Stage stage){
		serviceStage.update(stage);
		return Response.status(Response.Status.CREATED).entity("Success").build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteStage(Stage stage){
		serviceStage.remove(stage);
		return Response.status(Response.Status.CREATED).entity("Success").build();
	}
}

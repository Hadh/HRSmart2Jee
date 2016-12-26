package HRSmartRessources;

import pi.HRSmart.interfaces.IChoiceServiceLocal;
import pi.HRSmart.persistence.Choice;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by alaa on 01/11/16.
 */
@Path("/Choice")
public class ChoiceRessource {

    @EJB(beanName = "ChoiceService")
    IChoiceServiceLocal ChoiceService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Choice choice){
        ChoiceService.add(choice);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Choice choice){
        ChoiceService.update(choice);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response remove(@PathParam("id") int id){
        ChoiceService.remove(ChoiceService.get(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response get(@PathParam("id") int id){
        Choice choice;
        choice = ChoiceService.get(id);
        return Response.status(Response.Status.FOUND).entity(choice).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(){
        return Response.status(Response.Status.OK)
                .entity(JsonConverter.mainNode()
                        .put("choices", JsonConverter.convertChoices(ChoiceService.all())).toString()).build();
    }
}

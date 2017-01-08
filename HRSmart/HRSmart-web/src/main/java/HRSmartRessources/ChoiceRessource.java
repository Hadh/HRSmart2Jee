package HRSmartRessources;

import pi.HRSmart.interfaces.IChoiceServiceLocal;
import pi.HRSmart.persistence.Choice;

import javax.ejb.EJB;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by alaa on 01/11/16.
 */
@Path("choice")
public class ChoiceRessource {

    @EJB(beanName = "ChoiceService")
    IChoiceServiceLocal ChoiceService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String add(Choice choice){
        return JsonConverter.convertChoice(ChoiceService.add(choice)).toString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(Choice choice){
        return JsonConverter.convertChoice(ChoiceService.update(choice)).toString();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String remove(@PathParam("id") int id){

        return JsonConverter.convertChoices(ChoiceService.remove(ChoiceService.get(id))).toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String get(@PathParam("id") int id){
        return JsonConverter.convertChoice(ChoiceService.get(id)).toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String index(){
        return JsonConverter.convertChoices(ChoiceService.all()).toString();
    }
}

package HRSmartRessources;

import pi.HRSmart.interfaces.PostulationServiceLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by BoB on 11/2/2016.
 */

@Path("track")
@RequestScoped
public class TrackUserRessource {
    @EJB(beanName = "PostulationService")
    PostulationServiceLocal service;

    @GET
    @Produces("application/json")
    @Path("{id}")
    public String track(@PathParam("id") int idUser){
        return JsonConverter.convertPostulation(service.getPostulationByUser(idUser));
    }
}

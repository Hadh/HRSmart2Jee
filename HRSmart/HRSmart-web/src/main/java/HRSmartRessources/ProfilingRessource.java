package HRSmartRessources;

import pi.HRSmart.interfaces.ProfilingServiceLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by BoB on 10/31/2016.
 */

@Path("Profiling")
@RequestScoped
public class ProfilingRessource {

    @EJB(beanName = "ProfilingService")
    ProfilingServiceLocal profilingService;

    @GET
    @Produces("application/json")
    @Path("{id}")
    public String getAll(@PathParam("id") int id){
        return JsonConverter.ConvertProfilingMap(profilingService.Profile(id)) ;
    }

}

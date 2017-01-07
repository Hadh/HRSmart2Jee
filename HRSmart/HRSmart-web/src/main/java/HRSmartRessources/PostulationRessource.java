package HRSmartRessources;

import pi.HRSmart.interfaces.IAssessmentServiceLocal;
import pi.HRSmart.interfaces.PostulationServiceLocal;
import pi.HRSmart.persistence.Assessment;
import pi.HRSmart.persistence.Postulation;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import java.util.Date;

/**
 * Created by BoB on 10/31/2016.
 */

@Path("postulation")
@RequestScoped
public class PostulationRessource {

    @EJB(beanName = "PostulationService")
    PostulationServiceLocal service;
    @EJB(beanName = "AssessmentService")
    IAssessmentServiceLocal assessmentServiceLocal;




    @GET
    @Produces("application/json")
    public String getall(){
        return JsonConverter.convertPostulationList(service.getAllPostulations()) ;
    }
    @GET
    @Produces("application/json")
    @Path("{id}")
    public String get(@PathParam("id") int id){
        return JsonConverter.convertPostulation(service.getPostulation(id));
    }

    @POST
    @Consumes("application/json")
    public void addPostulation(Postulation postulation){
        postulation.setDatePostulation(Date.from(postulation.getDatePostulation().toInstant()));
        service.add(postulation);

        if (postulation.getAssessments()!=null){
            for (Assessment a : postulation.getAssessments()){
                assessmentServiceLocal.add(a);
            }
        }

    }
    @POST
    @Consumes("application/json")
    @Path("/update")
    public void updatePostulation(Postulation postulation){
        service.update(postulation);
        if (postulation.getAssessments()!=null){
            for (Assessment a : postulation.getAssessments()){
                assessmentServiceLocal.update(a);
            }
        }
    }
}

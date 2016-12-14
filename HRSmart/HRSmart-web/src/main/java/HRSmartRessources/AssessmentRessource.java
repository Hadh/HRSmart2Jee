package HRSmartRessources;

import pi.HRSmart.interfaces.IAssessmentServiceLocal;
import pi.HRSmart.persistence.Assessment;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alaa on 02/11/16.
 */
@Path("assessment")
public class AssessmentRessource {

    @EJB(beanName = "AssessmentService")
    IAssessmentServiceLocal assessmentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id_quiz}/{id_postulation}")
    public Response get(@PathParam("id_quiz")int id_quiz,@PathParam("id_postulation")int id_post){
        Assessment assessment = assessmentService.getUnique(id_quiz,id_post);
        return Response.status(Response.Status.FOUND)
                .entity(
                        JsonConverter.mainNode()
                        .put("assessment",JsonConverter.ConvertAssessment(assessment))
                ).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index()
    {
        return Response.status(Response.Status.OK)
                .entity(
                        JsonConverter.mainNode()
                        .put("assessments",JsonConverter.ConvertAssessment(assessmentService.all()).toString())
                ).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Assessment assessment){
        assessmentService.add(assessment);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Assessment assessment){
        assessmentService.update(assessment);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{id_quiz}/{id_postulation}")
    public Response remove(@PathParam("id_quiz")int id_quiz,@PathParam("id_postulation")int id_post){
        assessmentService.remove(assessmentService.getUnique(id_quiz,id_post));
        return Response.status(Response.Status.OK).build();
    }

}

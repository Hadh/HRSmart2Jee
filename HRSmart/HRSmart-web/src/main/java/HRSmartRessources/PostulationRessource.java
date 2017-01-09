package HRSmartRessources;

import com.auth0.jwt.internal.com.fasterxml.jackson.databind.JsonNode;
import pi.HRSmart.interfaces.IAssessmentServiceLocal;
import pi.HRSmart.interfaces.PostulationServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.Assessment;
import pi.HRSmart.persistence.Postulation;
import pi.HRSmart.persistence.User;
import pi.HRSmart.utilities.Jwt;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
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
    @EJB(beanName = "UserService")
    UserServiceLocal userServiceLocal;




    @GET
    @Produces("application/json")
    public String getall(){
        return JsonConverter.convertPostulationList(service.getAllPostulations()) ;
    }
    @GET
    @Produces("application/json")
    @Path("{id}")
    public String get(@PathParam("id") int id){
        return JsonConverter.convertPostulation(service.getPostulation(id)) ;
    }

    @GET
    @Produces("application/json")
    @Path("user/{token}")
    public String getallByUser(@PathParam("token") String token){
        String decoded = Jwt.decodeJWT(token);
        JsonNode jn= Jwt.stringToJson(decoded);
        int id=userServiceLocal.getUserByEmail(jn.get("user").asText()).getId();
        return JsonConverter.convertPostulationList(service.getAllByUser(id));
    }

    @POST
    @Consumes("application/json")

    public void addPostulation(Postulation postulation,@Context HttpHeaders hh){
        String token = hh.getHeaderString(HttpHeaders.AUTHORIZATION);
        String decoded = Jwt.decodeJWT(token);
        JsonNode jn= Jwt.stringToJson(decoded);
        User id=userServiceLocal.getUserByEmail(jn.get("user").asText());
        postulation.setDatePostulation(new Date());
        postulation.setPostulant(id);
        service.add(postulation);

        if (postulation.getAssessments()!=null){
            for (Assessment a : postulation.getAssessments()){
                assessmentServiceLocal.add(a);
            }
        }

    }
    @PUT
    @Consumes("application/json")
    public void updatePostulation(Postulation postulation){
        service.update(postulation);
        if (postulation.getAssessments()!=null){
            for (Assessment a : postulation.getAssessments()){
                assessmentServiceLocal.update(a);
            }
        }
    }
    @DELETE
    @Consumes("application/json")
    public Response deletePostulation(Postulation postulation,@Context HttpHeaders hh){
        postulation.setDatePostulation(new Date());
        service.delete(postulation);
        return Response.status(Response.Status.OK).build();
    }
}

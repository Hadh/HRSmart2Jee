package HRSmartRessources;

import pi.HRSmart.interfaces.IQuizServiceLocal;
import pi.HRSmart.persistence.Quiz;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alaa on 31/10/16.
 */
@Path("/quiz")
@RequestScoped
public class QuizRessource {

    @EJB(beanName = "QuizService")
    IQuizServiceLocal quizService ;
    QuestionRessource questionRessource;

    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Quiz getQuizz(@PathParam(value = "id")int id){
        Quiz quiz = new Quiz();
        quiz =  quizService.get(id);
        /*return Response.status(Response.Status.FOUND)
                .entity(JsonConverter.mainNode()
                        .put("quiz",JsonConverter.ConvertQuiz(quiz)).toString()
                ).build();*/
        //return Response.ok(quiz.getDescription()).build();
        //return Response.status(Response.Status.OK).entity(quiz).build();
        return quiz;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(){
        return Response.status(Response.Status.FOUND)
                .entity(quizService.all()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Quiz quiz){
        quizService.add(quiz);
        if(quiz.getQuestions() != null){
            quiz.getQuestions().stream().forEach(question -> {
                questionRessource.add(question);
            });
        }
        return Response.status(Response.Status.OK).build();
    }

    /*@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Quiz){

    }*/
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id")int id){
        quizService.remove(quizService.get(id));
        return Response.status(Response.Status.OK).build();
    }

}

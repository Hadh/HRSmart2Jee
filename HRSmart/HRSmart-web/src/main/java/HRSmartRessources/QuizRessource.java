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
    public String getQuizz(@PathParam("id")int id){

        Quiz quiz =  quizService.get(id);
        return JsonConverter.ConvertQuiz(quiz).toString();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String index(){
        String quizs = JsonConverter.ConvertQuiz(quizService.all()).toString();
        return quizs;
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

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id")int id){
        quizService.remove(quizService.get(id));
        return Response.status(Response.Status.OK).build();
    }

}

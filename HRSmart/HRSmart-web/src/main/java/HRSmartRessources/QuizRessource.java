package HRSmartRessources;

import pi.HRSmart.interfaces.IQuizServiceLocal;
import pi.HRSmart.persistence.Quiz;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Quiz getQuizz(@PathParam(value = "id")int id){
        Quiz quiz = new Quiz();
        try{
            quiz =  quizService.getWithRelations(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return quiz;
    }
}

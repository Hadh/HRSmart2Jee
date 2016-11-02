package HRSmartRessources;

import pi.HRSmart.interfaces.IChoiceServiceLocal;
import pi.HRSmart.interfaces.IQuestionServiceLocal;
import pi.HRSmart.interfaces.SkillServiceLocal;
import pi.HRSmart.persistence.Choice;
import pi.HRSmart.persistence.Question;
import pi.HRSmart.persistence.Skill;
import pi.HRSmart.services.SkillService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alaa on 01/11/16.
 */
@Path("question")
public class QuestionRessource {

    @EJB(beanName = "QuestionService")
    IQuestionServiceLocal questionService;
    @EJB(beanName = "ChoiceService")
    IChoiceServiceLocal ChoiceService;
    @EJB(beanName = "SkillService")
    SkillServiceLocal SkillService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(){
        return Response.status(Response.Status.OK)
                .entity(
                        JsonConverter.mainNode()
                        .put("questions", JsonConverter.convertQuestion(questionService.all()))
                        .toString()
                ).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response get(@PathParam("id") int id){
        return Response.status(Response.Status.FOUND)
                .entity(
                        JsonConverter.mainNode()
                        .put("question",JsonConverter.convertQuestion(questionService.get(id))).toString()
                ).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Question question){
        questionService.add(question);
        if(question.getChoices() != null){
            for (Choice choice: question.getChoices()){
                ChoiceService.add(choice);
            }
        }
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Question question){
        questionService.update(question);
        if(question.getChoices() != null){
            for (Choice choice: question.getChoices()){
                ChoiceService.update(choice);
            }
        }
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        questionService.remove(questionService.get(id));
        return Response.status(Response.Status.OK).build();
    }

}

package HRSmartRessources;

import pi.HRSmart.interfaces.IChoiceServiceLocal;
import pi.HRSmart.interfaces.IQuestionServiceLocal;
import pi.HRSmart.interfaces.SkillServiceLocal;
import pi.HRSmart.persistence.Choice;
import pi.HRSmart.persistence.Question;
import pi.HRSmart.persistence.Skill;
import pi.HRSmart.services.SkillService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alaa on 01/11/16.
 */
@Path("question")
@RequestScoped
public class QuestionRessource {

    @EJB(beanName = "QuestionService")
    IQuestionServiceLocal questionService;
    @EJB(beanName = "ChoiceService")
    IChoiceServiceLocal ChoiceService;
    @EJB(beanName = "SkillService")
    SkillServiceLocal SkillService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String index(){
        return JsonConverter.convertQuestion(questionService.all()).toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String get(@PathParam(value = "id") int id){
        return JsonConverter.convertQuestion(questionService.get(id)).toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Question question){
        questionService.add(question);
        questionService.update(question);
        try{
            if(question.getChoices() != null){
                for (Choice choice: question.getChoices()){
                    ChoiceService.add(choice);
                }
            }
            return Response.status(Response.Status.OK).build();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

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

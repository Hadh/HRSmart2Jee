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
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    @Path("{id}")
    public String get(@PathParam(value = "id") int id){
        return JsonConverter.convertQuestion(questionService.get(id)).toString();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public String get(@QueryParam(value = "skill") String skill){
        List<Question> questions;
        if(skill !=null)
            return JsonConverter.convertQuestion(questionService.getBySkill(skill)).toString();
        return JsonConverter.convertQuestion(questionService.all()).toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String add(Question question){
        Question q = questionService.add(question);

        return JsonConverter.convertQuestion(q).toString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(Question question){
        Question q = questionService.update(question);

        return JsonConverter.convertChoices(q.getChoices()).toString();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id") int id){
        List<Question> q = questionService.remove(questionService.get(id));
        return JsonConverter.convertQuestion(q).toString();
    }


}

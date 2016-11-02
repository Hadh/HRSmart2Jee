/**
 * @author Khaled Romdhane
 *
 */
package HRSmartRessources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pi.HRSmart.interfaces.JobSkillServiceLocal;
import pi.HRSmart.interfaces.SkillServiceLocal;
import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.JobSkill;
import pi.HRSmart.persistence.Skill;

/**
 * @author Khaled Romdhane
 *
 */
@Path("jobSkill")
@RequestScoped
public class JobSkillRessources {

	@EJB(beanName="JobSkillService")
	JobSkillServiceLocal jobSkillService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSkillWithAverageSalary(){
		
		List<JobSkill> list = jobSkillService.getAll();
		List<Skill> skills =new ArrayList<Skill>();
		List<Float> avrages = new ArrayList<Float>();
		for(JobSkill js : list)
		{
			if(skills.indexOf(js.getSkill())!=-1)
			{
			skills.add(js.getSkill());
			avrages.add(jobSkillService.getSkillAverageSalary(js.getSkill()));
			}
		}	
		
		return Response.status(Response.Status.FOUND).entity(JsonConverter.ConvertSkillSalaryList(skills,avrages)).build();
	}
	
}

package HRSmartRessources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.interfaces.SkillServiceLocal;

/**
 * @author Khaled Romdhane
 *
 */
@Path("skills")
@RequestScoped
public class SkillsResource {

	@EJB(beanName = "SkillService")
	SkillServiceLocal service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		return JsonConverter.convertSkillList(service.getAll()).toString();
	}
}

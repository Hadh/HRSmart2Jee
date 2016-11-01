/**
 * 
 */
package HRSmartRessources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pi.HRSmart.interfaces.UserSkillsServiceLocal;
import pi.HRSmart.persistence.UserBuisness;
import pi.HRSmart.persistence.UserSkill;
import pi.HRSmart.services.UserBuisnessService;

/**
 * @author yesmine
 *
 */
@Path("userbuisness")
@RequestScoped
public class UserBuisnessRessource {

	@EJB(beanName = "UserBuisnessService")
	UserBuisnessService userBuisnessService;
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSkill(UserBuisness userbuisness){
		userBuisnessService.update(userbuisness);
		return Response.status(Response.Status.OK).build();
	}
	
	
	
}

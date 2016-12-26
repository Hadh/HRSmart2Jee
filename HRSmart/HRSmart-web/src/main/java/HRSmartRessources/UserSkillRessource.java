/**
 * 
 */
package HRSmartRessources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pi.HRSmart.interfaces.CertificatServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.interfaces.UserSkillsServiceLocal;
import pi.HRSmart.persistence.Certificat;
import pi.HRSmart.persistence.Rewards;
import pi.HRSmart.persistence.Skill;
import pi.HRSmart.persistence.UserSkill;

/**
 * @author yesmine
 *
 */
@Path("userskills")
@RequestScoped

public class UserSkillRessource {
	@EJB(beanName = "UserSkillsService")
	UserSkillsServiceLocal userSkillsService;

	@EJB(beanName = "CertificatService")
	CertificatServiceLocal serviceCertificat;

	@EJB(beanName = "UserService")
	UserServiceLocal userService;
	// addUserSkill Done

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adduserSkill(UserSkill userskill) {
		userSkillsService.add(userskill);
		return Response.status(Response.Status.CREATED).build();
	}

	// getCertificatByuserSkill Done
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("certificats/{skill}")
	public Response getBySkill(@PathParam("skill") int skill) {

		return Response.status(Response.Status.OK)
				.entity(JsonConverter.ConvertListCertificat(serviceCertificat.getBySkill(skill))).build();

	}

	// updateUserSkill and their certificats done
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateuserSkill(UserSkill userSkill) {
		userSkillsService.update(userSkill);
		return Response.status(Response.Status.OK).build();

	}

	// deleteUSerSkill Done
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteuserSkill(UserSkill userSkill) {
		userSkillsService.remove(userSkill);
		return Response.status(Response.Status.OK).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {

		return Response.status(Response.Status.OK).entity(JsonConverter.ConvertListUserSkill(userSkillsService.getAll())).build();
	}
	
	//getUSerSkillByUser
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("{id}")
		public Response getSkillByUser(@PathParam("id")int id){
			
			return Response.status(Response.Status.OK).entity(JsonConverter.ConvertListUserSkill(userSkillsService.getByUser(id))).build();
		}
}

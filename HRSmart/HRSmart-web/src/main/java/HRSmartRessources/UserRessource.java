/**
 * 
 */
package HRSmartRessources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pi.HRSmart.interfaces.CertificatServiceLocal;
import pi.HRSmart.interfaces.SkillServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.interfaces.UserSkillsServiceLocal;
import pi.HRSmart.persistence.Certificat;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserSkill;

/**
 * @author yesmine
 *
 */

@Path("user")
@RequestScoped
public class UserRessource {
	
	@EJB(beanName = "UserService")
	UserServiceLocal service;
	
	@EJB(beanName = "UserSkillsService")
	UserSkillsServiceLocal userSkillService;
	
	@EJB(beanName = "CertificatService")
	CertificatServiceLocal serviceCertificat;
    
	//Certificat
	
		//addCErtificat
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("certificat")
	public void add(Certificat certificat){
		 serviceCertificat.add(certificat);
	}
		//getCertificatBySkill
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("certificat/{skill}")
	public List<Certificat> getBySkill(@PathParam("skill") int skill) {
		return serviceCertificat.getBySkill(skill);
	}
		//updateCertificat
	@PUT
	@Path("certificat")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Certificat certificat){
		serviceCertificat.update(certificat);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("certificats/{id}")
	public List<Certificat> getCertificatByUser(@PathParam("id") int id) {
		List<Certificat> list = new ArrayList<Certificat>();
				
				for(UserSkill s : userSkillService.getByUser(id))
				{
					list.addAll(s.getCertificats());
				}
				
				
				return list;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public User getFull(@PathParam("id")int id){
		return service.getFull(id);
	}

}

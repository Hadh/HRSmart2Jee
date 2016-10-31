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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
	
		//addCErtificatDone
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("certificat")
	public void add(Certificat certificat){
		 serviceCertificat.add(certificat);
	}
		//getCertificatBySkillDone
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("certificat/{skill}")
	public List<Certificat> getBySkill(@PathParam("skill") int skill) {
		return serviceCertificat.getBySkill(skill);
	}
		//updateCertificatDone
	@PUT
	@Path("certificat")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Certificat certificat){
		serviceCertificat.update(certificat);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("certificats/{id}")
	public String getCertificatByUser(@PathParam("id") int id) {
		List<Certificat> list = new ArrayList<Certificat>();
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		ArrayNode certificats = mapper.createArrayNode();
				for(UserSkill us : userSkillService.getByUser(id))
				{
					for(Certificat c : us.getCertificats())
					{
					ObjectNode cert = mapper.createObjectNode();
					cert.put("id", c.getId());
					cert.put("name", c.getName());
					ObjectNode skill = mapper.createObjectNode();	
					skill.put("id",c.getSkill().getId());
					skill.put("name",c.getSkill().getName());
					//list.addAll(s.getCertificats());
					cert.put("skill", skill);
					certificats.add(cert);
					}
				
				}
				
				main.put("certificats", certificats);
				return main.toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public User getFull(@PathParam("id")int id){
		return service.getFull(id);
	}

}

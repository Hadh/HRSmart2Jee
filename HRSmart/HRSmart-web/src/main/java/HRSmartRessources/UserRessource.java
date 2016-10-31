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
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserBuisness;
import pi.HRSmart.interfaces.CertificatServiceLocal;
import pi.HRSmart.interfaces.UserSkillsServiceLocal;
import pi.HRSmart.persistence.Certificat;
import pi.HRSmart.persistence.UserSkill;

/**
 * Created by hadhemi on 10/30/2016.
 */
@Path("user")
@RequestScoped
public class UserRessource {
	@EJB(beanName="UserService")
	UserServiceLocal userServiceLocal;

	@EJB(beanName = "UserSkillsService")
	UserSkillsServiceLocal userSkillService;

	@EJB(beanName = "CertificatService")
	CertificatServiceLocal serviceCertificat;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getFull(@PathParam("id") int id) {
		User user = userServiceLocal.getFull(id);


		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();

		main.put("id", user.getId());
		main.put("firstName", user.getFirstName());
		main.put("lastName", user.getLastName());
		main.put("adresse", user.getAdresse());
		main.put("numTel", user.getNumTel());
		main.put("login", user.getLogin());
		main.put("password", user.getPassword());

		ArrayNode UserBuisnesses = mapper.createArrayNode();

		for (UserBuisness bs : user.getUserBuisness()) {

			ObjectNode userBusiness = mapper.createObjectNode();
			userBusiness.put("id", bs.getId().toString());
			userBusiness.put("role", bs.getRole());
			userBusiness.put("salary", bs.getSalary());
			userBusiness.put("hiredate", bs.getHireDate().toString());

			ObjectNode business = mapper.createObjectNode();
			business.put("id", bs.getBuisness().getId());
			business.put("name", bs.getBuisness().getName());
			userBusiness.put("Business",business);
			UserBuisnesses.add(userBusiness);
		}

		main.put("UserBuisnesses", UserBuisnesses);
		return main.toString();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("users")
	public void addUser(User user){
		userServiceLocal.addUser(user);
	}
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
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{user}/{password}")
	public String authenticate(@PathParam("user") String user,@PathParam("password")String password){
		return userServiceLocal.authenticate(user,password);
	}



}

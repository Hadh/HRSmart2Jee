package HRSmartRessources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import pi.HRSmart.interfaces.CertificatServiceLocal;
import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.interfaces.UserSkillsServiceLocal;
import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.Certificat;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserBuisness;
import pi.HRSmart.persistence.UserSkill;
import pi.HRSmart.utilities.Secured;

/**
 * Created by hadhemi on 10/30/2016.
 */
@Path("users")
@RequestScoped
public class UserRessource {

	@EJB(beanName="UserService")
	UserServiceLocal userServiceLocal;

	@EJB(beanName = "UserSkillsService")
	UserSkillsServiceLocal userSkillService;

	@EJB(beanName = "CertificatService")
	CertificatServiceLocal serviceCertificat;

	@EJB(beanName = "UserBuisnessService")
	UserBuisnessServiceLocal userBuisnessService;

	// Certificat

	// addCErtificatDone

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getFull(@PathParam("id") int id) {
		User user = userServiceLocal.getFull(id);
		return JsonConverter.ConvertUser(user);
	}

	@DELETE
	@Path("{id}")
	public void deleteUser(@PathParam("id") int id){
		userServiceLocal.delete(userServiceLocal.get(id));
	}


	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateUser(@PathParam("id") int idUser){
		userServiceLocal.update(userServiceLocal.get(idUser));
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addUser(User user){
		return userServiceLocal.addUser(user);
	}

	//Certificat
	
		//addCErtificat
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("certificat")
	public void add(Certificat certificat) {
		serviceCertificat.add(certificat);
	}

	// getCertificatBySkillDone
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("certificat/{skill}")
	public String getBySkill(@PathParam("skill") int skill) {
		return JsonConverter.ConvertListCertificat(serviceCertificat.getBySkill(skill));
	}

	// updateCertificatDone
	@PUT
	@Path("certificat")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Certificat certificat) {
		serviceCertificat.update(certificat);
	}

	// getCertifByUser Done
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("certificats/{id}")
	public String getCertificatByUser(@PathParam("id") int id) {
		List<Certificat> list = new ArrayList<Certificat>();

		for (UserSkill us : userSkillService.getByUser(id)) {

			list.addAll(us.getCertificats());
		}
		return JsonConverter.ConvertListCertificat(list);

	}
	@Secured
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{user}/{password}")
	public String authenticate(@PathParam("user") String user,@PathParam("password")String password){
		return userServiceLocal.authenticate(user,password);
	}

	// Buisness
	// getBuisnessByUser

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("buisnesses/{id}")
	public String getByUser(@PathParam("id")int id) {
		List<Buisness> list = new ArrayList<Buisness>();
		for (UserBuisness ub : userBuisnessService.getByUser(id)) {

			list.add(ub.getBuisness());
		}
		return JsonConverter.ConvertListBuisness(list);

	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUsers(){
		return JsonConverter.ConvertListUser(userServiceLocal.getAll());
	}

}

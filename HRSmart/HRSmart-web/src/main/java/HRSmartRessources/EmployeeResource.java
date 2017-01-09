package HRSmartRessources;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pi.HRSmart.interfaces.BuisnessServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserBuisness;
import pi.HRSmart.persistence.UserBuisnessPK;
import pi.HRSmart.services.UserBuisnessService;

@Path("employeemanage")
@RequestScoped
public class EmployeeResource {

	@EJB(beanName = "BuisnessService")
	BuisnessServiceLocal service;

	@EJB(beanName = "UserBuisnessService")
	UserBuisnessServiceLocal userBuisnessServiceLocal;

	@EJB(beanName = "UserService")
	UserServiceLocal userServiceLocal;

	@EJB(beanName = "UserBuisnessService")
	UserBuisnessServiceLocal serviceBuisnessUser;

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addBuisness(Buisness buisness, @Context HttpHeaders hh) {
		String token = hh.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (token != null) {
			User user = userServiceLocal.TokenToUser(token);
			UserBuisness userBuisness = new UserBuisness();
			UserBuisnessPK pk = new UserBuisnessPK();
			pk.setBuisness(buisness);
			pk.setUser(user);
			userBuisness.setId(pk);
			userBuisness.setHireDate(new Date());
			userBuisness.setRole("HR");
			userBuisnessServiceLocal.add(userBuisness);
		}

		return null;
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public String GetNonEmployed() {
		return JsonConverter.ConvertListUser(userServiceLocal.getNonEmployed());
		
	}
	
	@GET
	@Path("myBusiness")
	public String getMyBuisiness(@Context HttpHeaders hh){
		String token = hh.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (token != null) {
			User user = userServiceLocal.TokenToUser(token);
			List<UserBuisness> list = serviceBuisnessUser.getByUser(user.getId());
			for(UserBuisness u : list){
				if(u.getRole().equals("HR")){
					return JsonConverter.ConvertBuisnessMin(u.getBuisness());
				}
			}
		}
		return null;
	}
}

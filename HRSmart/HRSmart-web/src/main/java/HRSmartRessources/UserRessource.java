package HRSmartRessources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserBuisness;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by hadhe on 10/30/2016.
 */
@Path("user")
@RequestScoped
public class UserRessource {
    @EJB(beanName="UserService")
    UserServiceLocal userServiceLocal;

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
        main.put("email", user.getLogin());

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

}

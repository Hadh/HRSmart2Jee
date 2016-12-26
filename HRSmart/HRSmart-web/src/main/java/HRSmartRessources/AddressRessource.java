package HRSmartRessources;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pi.HRSmart.interfaces.AddressServiceLocal;
import pi.HRSmart.interfaces.BuisnessServiceLocal;
import pi.HRSmart.persistence.Address;
import pi.HRSmart.persistence.Buisness;

@Path("address")
@RequestScoped
public class AddressRessource {
	
	
	@EJB(beanName = "AddressService")
	AddressServiceLocal addressService;
	
	//GoogleMap
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocalisationByBuisness(@PathParam("id") int idBuisness){
		List<Address> address = addressService.getAllByBuisness(idBuisness);
		String result = JsonConverter.convertListAddress(address);
		return Response.status(Response.Status.OK).entity(result).build();
	}
	
	

}

/**
 * 
 */
package HRSmartRessources;

import javax.enterprise.context.RequestScoped;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.auth0.jwt.internal.com.fasterxml.jackson.databind.JsonNode;

import pi.HRSmart.utilities.Jwt;

/**
 * @author yesmine
 *
 */
@Path("profile")
@RequestScoped
public class ProfileRessource {
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getProfile (@Context HttpHeaders hh){
		String token =
                hh.getHeaderString(HttpHeaders.AUTHORIZATION);
		token = Jwt.decodeJWT(token);
		JsonNode jn = Jwt.stringToJson(token);
		return jn.get("user").toString();
	
	}

}

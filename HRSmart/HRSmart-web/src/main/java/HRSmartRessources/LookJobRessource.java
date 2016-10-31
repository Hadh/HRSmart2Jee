/**
 * @author Khaled Romdhane
 *
 */
package HRSmartRessources;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.JobSkill;
import pi.HRSmart.persistence.Rewards;

/**
 * @author Khaled Romdhane
 *
 */

@Path("lookJob")
@RequestScoped
public class LookJobRessource {
	@EJB(beanName = "JobOfferService")
	JobOfferServiceLocal jobService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		return JsonConverter.ConvertList(jobService.getAll());
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getFull(@PathParam("id") int id) {
		return JsonConverter.ConvertFull(jobService.getFull(id));
	}
}

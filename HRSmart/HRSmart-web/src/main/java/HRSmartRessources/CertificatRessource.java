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
import pi.HRSmart.interfaces.UserSkillsServiceLocal;
import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.Certificat;
import pi.HRSmart.persistence.UserSkill;

/**
 * @author yesmine
 *
 */
@Path("certificats")
@RequestScoped
public class CertificatRessource {
	@EJB(beanName = "CertificatService")
	CertificatServiceLocal serviceCertificat;

	// Certificat

	// addCErtificat Done
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Certificat certificat) {
		serviceCertificat.add(certificat);
		return Response.status(Response.Status.CREATED).build();
	}

	// updateCertificat Done
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Certificat certificat) {
		serviceCertificat.update(certificat);
		return Response.status(Response.Status.OK).build();

	}

	// remove certificat Done
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response remove(Certificat certificat) {
		serviceCertificat.remove(certificat);
		return Response.status(Response.Status.OK).build();
	}

	// getCertificat Done

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCertificatByid(@PathParam("id") int id) {
		Certificat certificat = serviceCertificat.get(id);
		return Response.status(Response.Status.FOUND).entity(serviceCertificat.get(id)).build();
	}

	// getAllCertificats Done
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCertificats() {
		return Response.status(Response.Status.FOUND)
				.entity(JsonConverter.ConvertListCertificat(serviceCertificat.getAll())).build();

	}

}

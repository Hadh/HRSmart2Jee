
@Path("job")
public class JobOffer implements Serializable {

	@EJB(beanname = jobOfferService)
	JobOfferServiceLocal x;

	@Get
	@Produces("applicationjson")
	public getJobById(@Queyparam id)
	{
		x.getJob(id);
	}
	
   
}

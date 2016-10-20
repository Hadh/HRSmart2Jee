package pi.HRSmart.services;

import pi.HRSmart.interfaces.PostulationServiceLocal;
import pi.HRSmart.interfaces.ProfilingServiceLocal;
import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.Postulation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by BoB on 10/18/2016.
 */
@Stateless
public class ProfilingService implements ProfilingServiceLocal {

    @PersistenceContext(unitName="HRSmart-ejb")
    EntityManager em;

    @EJB(beanName = "PostulationService")
    PostulationServiceLocal postulationServiceLocal;


    @Override
    public void Profile(JobOffer jobOffer) {

    }
}

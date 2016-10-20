package pi.HRSmart.services;

import pi.HRSmart.interfaces.PostulationServiceLocal;
import pi.HRSmart.interfaces.ProfilingServiceLocal;
import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.persistence.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by BoB on 10/18/2016.
 */
@Stateless
public class ProfilingService implements ProfilingServiceLocal {

    @PersistenceContext(unitName="HRSmart-ejb")
    EntityManager em;





    @Override
    public void Profile(JobOffer jobOffer) {


//
//        List<JobSkill> jobSkills = jobOffer.getJobSkills();
//        List<Rewards> rewards = jobOffer.getRewards();
//        List<Skill> skillSet = jobSkills.stream().map(e -> e.getSkill()).collect(Collectors.toList());
////        List<User> ps = rewards.stream().forEach(e->e.getPostulations().forEach(j->j.getPostulant()));



    }
}

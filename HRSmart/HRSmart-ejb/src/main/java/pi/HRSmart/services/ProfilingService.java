package pi.HRSmart.services;

import pi.HRSmart.interfaces.*;
import pi.HRSmart.persistence.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by BoB on 10/18/2016.
 */
@Stateless

public class ProfilingService implements ProfilingServiceLocal {

    @PersistenceContext(unitName="HRSmart-ejb")
    EntityManager em;

    @EJB(beanName = "RewardService")
    RewardServiceLocal rewardServiceLocal;
    @EJB(beanName = "UserService")
    UserServiceLocal userServiceLocal;
    @EJB(beanName = "JobOfferService")
    JobOfferServiceLocal jobOfferServiceLocal;




    @Override
    public Map<User,Integer> Profile(int id) {

        JobOffer jobOffer = jobOfferServiceLocal.getFull(id);

        List<JobSkill> jobSkills = jobOffer.getJobSkills();
        List<Skill> skillSet = jobSkills.stream().map(e -> e.getSkill()).collect(Collectors.toList());
        List<Postulation> ps = rewardServiceLocal.getCVStage(jobOffer.getId()).getPostulations();
        List<User> postulants = ps.stream().map(e->
                userServiceLocal.getFull(e.getPostulant().getId())).collect(Collectors.toList());




        Map<User,Integer> DataStream = new HashMap<>();
        postulants.forEach(e->DataStream.put(e,0));

        Map<User,Integer> ScoredList = new HashMap<>();

        ScoredList = Score(DataStream,skillSet);


        return  ScoredList;
    }

    @Override
    public Map<User,Integer> Score(Map<User,Integer> scored,List<Skill> skillset) {

        Integer index = 0;
        Map<User,Integer> FinalScore = new HashMap<>();

        for(Skill s :skillset){
            for (User u:scored.keySet()){
                for (UserSkill userskill: u.getUserSkills()){
                    if(userskill.getSkill().getName().equals(s.getName())){
                        index=userskill.getLevel()/10;
                        FinalScore.merge(u,index,(integer, integer2) -> integer+=integer2);
                    }
//                    for (Certificat cert:userskill.getCertificats()){
//                        for (Certificat certset:s.getCertificats()){
//                            if (cert.getName().equals(certset.getName())){
//
//                                FinalScore.merge(u,1,(integer, integer2) -> integer+=integer2);
//                            }
//                        }
//                    }
                }

            }
        }



        return FinalScore;
    }
}

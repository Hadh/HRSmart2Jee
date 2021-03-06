package pi.HRSmart.services;

import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.interfaces.PostulationServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by BoB on 10/18/2016.
 */
@Stateless
public class PostulationService implements PostulationServiceLocal {

    @PersistenceContext(unitName = "HRSmart-ejb")
    EntityManager em;
    @EJB(beanName = "UserService")
    UserServiceLocal userServiceLocal;

    @EJB(beanName = "JobOfferService")
    JobOfferServiceLocal jobOfferServiceLocal;

    public PostulationService() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void add(Postulation postulation) {
        em.persist(postulation);
    }

    @Override
    public void update(Postulation postulation) {
        em.merge(postulation);
    }

    @Override
    public void delete(Postulation postulation) {
        em.remove(em.merge(postulation));
    }

    @Override
    public Postulation getPostulation(int id) {
        return em.find(Postulation.class,id);
    }

    @Override
    public List<Postulation> getAllPostulations() {
        Query query =  em.createQuery("select p from Postulation p ");
        return (List<Postulation>) query.getResultList();
    }

    @Override
    public List<Postulation> filterPostulationsByQuizResult(int threshold, Skill sk) {
        Query query = em.createQuery("SELECT p " +
                "FROM Postulation p " +
                "join p.assessments a " +
                "join a.pk.quiz qui  " +
                "join qui.questions qq " +
                "WHERE a.result > :threshold and qq.skill = :skill")
                .setParameter("threshold", threshold)
                .setParameter("skill", sk).setMaxResults(5);
        return (List<Postulation>) query.getResultList();
    }

    @Override
    public List<Assessment> getPostulationResults(Postulation ps) {
        Query query = em.createQuery(
                "select a from Assessment a left join a.pk.postulation where a.pk.postulation = :ps"
        ).setParameter("ps", ps.getIdPostulation());

        return (ArrayList<Assessment>) query.getResultList();
    }

    @Override
    public Postulation getPostulationByUser(int idUser) {
        Query query =  em.createQuery("select p from Postulation p where p.postulant.id=:id");
        query.setParameter("id",idUser);
        return (Postulation) query.getSingleResult();
    }

    @Override
    public List<Postulation> getAllByUser(int idUser) {
        Query query =  em.createQuery("select p from Postulation p where p.postulant.id=:id");
        query.setParameter("id",idUser);
        User u = userServiceLocal.getFull(idUser);


        List<Postulation> ps= (List<Postulation>) query.getResultList();
        for(Postulation p : ps){
            p.setPostulant(u);
            int jobofferid= p.getReward().getJobOffer().getId();
            JobOffer jo = jobOfferServiceLocal.getFull(jobofferid);
            Rewards r = p.getReward();
            r.setJobOffer(jo);
            p.setReward(r);

        }


        return ps;
    }


}

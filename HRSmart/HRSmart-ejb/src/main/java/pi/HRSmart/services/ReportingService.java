package pi.HRSmart.services;

import pi.HRSmart.interfaces.IReportingServiceLocal;
import pi.HRSmart.persistence.Postulation;
import pi.HRSmart.persistence.Report;
import pi.HRSmart.persistence.Rewards;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by alaa on 01/11/16.
 */
@Stateless
public class ReportingService implements IReportingServiceLocal {

    @PersistenceContext(unitName = "HRSmart-ejb")
    EntityManager em;

    @Override
    public void add(Report r) {
        em.persist(r);
    }

    @Override
    public void update(Report r) {
        em.merge(r);
    }

    @Override
    public void delete(Report r) {
        em.remove(r);
    }

    @Override
    public Report get(int id) {
        return em.find(Report.class, id);
    }


    @Override
    public List<Report> getAll() {
        Query query = em.createQuery(
                "select r from Report r"
        );
        return (List <Report>) query.getResultList();
    }

    @Override
    public List<Report> getByPostulation(Postulation postulation) {
        Query query = em.createQuery(
                "select r from Report r left join r.postulation p where p.idPostulation = :id"
        ).setParameter("id",postulation.getIdPostulation());
        return (List <Report>) query.getResultList();
    }

    @Override
    public List<Report> getByReward(Rewards rewards) {
        Query query = em.createQuery(
                "select r from Report r left join r.rewards p where p.id = :id"
        ).setParameter("id", rewards.getId());
        return (List <Report>) query.getResultList();
    }
}

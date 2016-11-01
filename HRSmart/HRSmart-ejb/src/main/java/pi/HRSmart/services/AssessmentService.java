package pi.HRSmart.services;

import pi.HRSmart.interfaces.IAssessmentServiceLocal;
import pi.HRSmart.persistence.Assessment;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by alaa on 01/11/16.
 */
@Stateless
public class AssessmentService implements IAssessmentServiceLocal {
    @PersistenceContext(unitName = "HRSmart-ejb")
    EntityManager em;

    @Override
    public List<Assessment> getByQuiz(int quiz_id) {
        Query query = em.createQuery("select a from Assessment a where a.pk.quiz.id = :quiz_id ")
                .setParameter("quiz_id",quiz_id);

        return (List <Assessment>) query.getResultList();
    }

    @Override
    public List <Assessment> getByPostulation(int post_id) {
        Query query = em.createQuery("select a from Assessment a where a.pk.postulation.idPostulation = :post_id ")
                .setParameter("post_id",post_id);

        return (List <Assessment>) query.getResultList();
    }
}

package pi.HRSmart.services;

import pi.HRSmart.interfaces.IAssessmentServiceLocal;
import pi.HRSmart.interfaces.IChoiceServiceLocal;
import pi.HRSmart.persistence.Assessment;
import pi.HRSmart.persistence.Choice;

import javax.ejb.EJB;
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
    @EJB(beanName = "ChoiceService")
    IChoiceServiceLocal ChoiceService;

    @PersistenceContext(unitName = "HRSmart-ejb")
    EntityManager em;

    @Override
    public void add(Assessment assessment) {
        em.persist(assessment);
    }

    @Override
    public void remove(Assessment assessment) {
        em.remove(assessment);
    }

    @Override
    public Assessment getUnique(int quiz_id, int postulation_id) {
        Query query = em.createQuery("select a from Assessment a where a.pk.quiz.id = :quiz_id and a.pk.postulation.idPostulation = :id_post")
                .setParameter("quiz_id",quiz_id)
                .setParameter("id_post", postulation_id);
        return (Assessment)query.getSingleResult() ;
    }

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

    @Override
    public void calculateResult(Assessment assessment, int[] ids) {
        List <Choice> choices = ChoiceService.getMultiple(ids);
        int result = 0;
        for(Choice choice:choices){
            result+= choice.getMark();
        }
        assessment.setResult(result);
        em.merge(assessment);
    }
}

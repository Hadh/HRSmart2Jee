package pi.HRSmart.services;

import pi.HRSmart.interfaces.IAssessmentServiceLocal;
import pi.HRSmart.interfaces.IQuestionServiceLocal;
import pi.HRSmart.interfaces.IQuizServiceLocal;
import pi.HRSmart.persistence.Assessment;
import pi.HRSmart.persistence.Question;
import pi.HRSmart.persistence.Quiz;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alaa on 19/10/16.
 */
@Stateless
public class QuizService implements IQuizServiceLocal{

    @EJB(beanName = "AssessmentService")
    IAssessmentServiceLocal AssessmentService;

    @PersistenceContext(unitName = "HRSmart-ejb")
    EntityManager em;


    @Override
    public void add(Quiz quiz) {
        em.persist(quiz);
    }

    @Override
    public void update(Quiz quiz) {
        em.merge(quiz);
    }

    @Override
    public void remove(Quiz quiz) {
        em.remove(em.merge(quiz));
    }

    @Override
    public Quiz get(int id) {
        return em.find(Quiz.class, id);
    }

    @Override
    public List<Quiz> all() {
        Query query = em.createQuery("select q from Quiz q");
        return (ArrayList<Quiz>)query.getResultList();
    }
    @Override
    public Quiz getWithRelations(int id){
        Quiz quiz;
        Query query = em.createQuery("select q from Quiz q left join fetch q.questions where q.id = :id").setParameter("id",id);
        quiz = (Quiz) query.getSingleResult();

        return quiz;
    }
}

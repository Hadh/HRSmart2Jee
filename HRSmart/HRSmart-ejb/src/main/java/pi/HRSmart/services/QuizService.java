package pi.HRSmart.services;

import pi.HRSmart.interfaces.IAssessmentServiceLocal;
import pi.HRSmart.interfaces.IQuestionServiceLocal;
import pi.HRSmart.interfaces.IQuizServiceLocal;
import pi.HRSmart.persistence.Assessment;
import pi.HRSmart.persistence.Question;
import pi.HRSmart.persistence.Quiz;
import pi.HRSmart.persistence.Skill;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alaa on 19/10/16.
 */
@Stateful
public class QuizService implements IQuizServiceLocal{

    @EJB(beanName = "AssessmentService")
    IAssessmentServiceLocal AssessmentService;

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager em;


    @Override
    public Quiz add(Quiz quiz) {
        em.persist(quiz);
        em.refresh(quiz);
        return quiz;
    }

    @Override
    public Quiz update(Quiz quiz) {
       return em.merge(quiz);
    }

    @Override
    public void remove(Quiz quiz) {

        em.remove(em.merge(quiz));
        all();
    }

    @Override
    public Quiz get(int id) {
        Quiz quiz = em.find(Quiz.class,id);


        return quiz;
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

    @Override
    public void addWithRelation(Quiz quiz) {

    }

    @Override
    public List<Quiz> getQuizBySkill(String skill) {

        Query q = em.createQuery("select q from Quiz q join q.questions qu join qu.skill s where s.name=:skillname")
                .setParameter("skillname", skill);

        return (ArrayList<Quiz>) q.getResultList();
    }


}

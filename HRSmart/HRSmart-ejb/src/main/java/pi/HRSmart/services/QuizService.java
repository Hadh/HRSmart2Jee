package pi.HRSmart.services;

import pi.HRSmart.interfaces.IAssessmentServiceLocal;
import pi.HRSmart.interfaces.IQuestionServiceLocal;
import pi.HRSmart.interfaces.IQuizServiceLocal;
import pi.HRSmart.persistence.Assessment;
import pi.HRSmart.persistence.Question;
import pi.HRSmart.persistence.Quiz;

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
        Quiz quiz = em.find(Quiz.class,id);
        /*Query query = em.createQuery("select q from Question q join q.quizs qu where qu.id=:id")
                .setParameter("id",quiz.getId());
        List<Question> questions = query.getResultList();
        quiz.setQuestions(questions);
        System.out.println("size "+ quiz.getQuestions().size());*/

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
}

package pi.HRSmart.services;

import pi.HRSmart.interfaces.IChoiceServiceLocal;
import pi.HRSmart.interfaces.IQuestionServiceLocal;
import pi.HRSmart.persistence.Choice;
import pi.HRSmart.persistence.Question;
import pi.HRSmart.persistence.Quiz;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by alaa on 20/10/16.
 */
@Stateless
public class QuestionService implements IQuestionServiceLocal {

    @PersistenceContext(unitName = "HRSmart-ejb")
    EntityManager em;
    @EJB
    IChoiceServiceLocal serviceChoice;

    @Override
    public Question add(Question question) {
        List<Choice> posted = new ArrayList<>();
        posted = question.getChoices();
        em.persist(question);
        em.refresh(question);

        if(posted != null){
            List<Choice> choices = new ArrayList<>();
            for (Choice choice: posted) {
                choice.setQuestion(question);
                choices.add(serviceChoice.add(choice));
            }
        }
        em.refresh(question);
        return question;
    }

    @Override
    public Question update(Question question) {
        return em.merge(question);
    }

    @Override
    public List<Question> remove(Question question) {
        em.remove(em.merge(question));
        return all();
    }

    @Override
    public Question get(int id) {
        return em.find(Question.class, id);
    }

    @Override
    public ArrayList<Question> all() {
        Query query = em.createQuery("select q from Question q");

        return (ArrayList<Question>) query.getResultList();
    }

    @Override
    public List<Question> getBySkill(String skill) {
        Query q = em.createQuery("select q from Question q join q.skill s where s.name=:skill")
                .setParameter("skill",skill);
        return (ArrayList<Question>) q.getResultList();
    }

}

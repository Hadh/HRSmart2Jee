package pi.HRSmart.services;

import pi.HRSmart.interfaces.IChoiceServiceLocal;
import pi.HRSmart.persistence.Choice;

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
public class ChoiceService implements IChoiceServiceLocal{

    @PersistenceContext(unitName = "HRSmart-ejb")
    EntityManager em;


    @Override
    public Choice add(Choice choice) {
        em.persist(choice);
        em.refresh(choice);
        return choice;
    }

    @Override
    public Choice update(Choice choice) {
        return em.merge(choice);
    }

    @Override
    public List<Choice> remove(Choice choice) {
        em.remove(em.merge(choice));
        return all();
    }

    @Override
    public Choice get(int id) {
        return em.find(Choice.class, id);
    }

    @Override
    public List<Choice> all() {
        Query query = em.createQuery("select c from Choice c");
        return (List<Choice>) query.getResultList();
    }

    @Override
    public List<Choice> getMultiple(int[] ids) {
        List <Choice> choices = new ArrayList<>();
        for(int i: ids){
            choices.add(this.get(i));
        }
        return choices;
    }
}

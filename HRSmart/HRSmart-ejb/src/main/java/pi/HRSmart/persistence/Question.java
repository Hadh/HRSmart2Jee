package pi.HRSmart.persistence;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alaa on 17/10/16.
 */

@Entity
public class Question {

    private int id;
    private String body;
    private Skill skill;
    private List<Choice> choices;

    @OneToMany
    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    @ManyToOne
    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}

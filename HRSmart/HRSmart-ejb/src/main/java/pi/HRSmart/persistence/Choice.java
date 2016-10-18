package pi.HRSmart.persistence;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alaa on 17/10/16.
 */
@Entity
public class Choice implements Serializable{

    private int id;
    private int body;
    private boolean isCorrect;
    private Question question;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @ManyToOne
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

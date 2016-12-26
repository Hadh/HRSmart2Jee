package pi.HRSmart.persistence;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alaa on 18/10/16.
 */
@Entity
public class Quiz implements Serializable{

    private int id;
    private int duration;
    private float result;
    private String description;
    private List<Question> questions = new ArrayList<>();
    private List<Assessment> assessments = new ArrayList<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    @OneToMany(mappedBy = "pk.quiz")
    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String descrption) {
        this.description = descrption;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}

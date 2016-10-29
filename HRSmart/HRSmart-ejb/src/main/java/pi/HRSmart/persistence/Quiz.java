package pi.HRSmart.persistence;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alaa on 18/10/16.
 */
@Entity
public class Quiz {

    private int id;
    private int duration;
    private float result;
    private String descrption;

    private List<Assessment> assessments = new ArrayList<>();
    private List<Question> questions = new ArrayList<>();

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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "pk.quiz", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    @ManyToMany
    @Column(name = "question_id")
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}

package pi.HRSmart.persistence;

import org.hibernate.annotations.Cascade;
import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * Created by BoB on 10/18/2016.
 */

@Entity

public class Postulation  implements Serializable{

    private Integer idPostulation;
    private Date datePostulation;
    private User Postulant;
    private Rewards Reward;
    private List<Assessment> assessments;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    public Integer getIdPostulation() {
        return idPostulation;
    }

    public void setIdPostulation(Integer idPostulation) {
        this.idPostulation = idPostulation;
    }

    public Date getDatePostulation() {
        return datePostulation;
    }

    public void setDatePostulation(Date datePostulation) {
        this.datePostulation = datePostulation;
    }

    @ManyToOne
    public User getPostulant() {
        return Postulant;
    }

    public void setPostulant(User postulant) {
        Postulant = postulant;
    }

    @ManyToOne
    public Rewards getReward() {
        return Reward;
    }

    public void setReward(Rewards reward) {
        Reward = reward;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.postulation", cascade = {
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
}
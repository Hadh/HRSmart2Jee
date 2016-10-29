package pi.HRSmart.persistence;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alaa on 18/10/16.
 */
@Entity
@Table(name ="assessment")
@AssociationOverrides({
        @AssociationOverride(name ="pk.quiz",
                joinColumns = @JoinColumn(name ="quiz_id",
                insertable = false, updatable = false
                )),
        @AssociationOverride(name ="pk.postulation",
                joinColumns = @JoinColumn(name ="postulation_id",
                        insertable = false, updatable = false
                ))
})
public class Assessment implements Serializable {

	private AssessmentPK pk = new AssessmentPK();
    private int result;


    @EmbeddedId
    public AssessmentPK getPk() {
        return pk;
    }

    public void setPk(AssessmentPK pk) {
        this.pk = pk;
    }

    @Transient
    public Quiz getQuiz(){
        return getPk().getQuiz();
    }

    public void setQuiz(Quiz q){
        getPk().setQuiz(q);
    }

    @Transient
    public Postulation getPostulation(){
        return getPk().getPostulation();
    }

    public void setPostulation(Postulation p){
        getPk().setPostulation(p);
    }


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assessment that = (Assessment) o;

        return pk.equals(that.pk);
    }

    @Override
    public int hashCode() {
        return pk.hashCode();
    }


}

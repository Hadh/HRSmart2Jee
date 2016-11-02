package pi.HRSmart.interfaces;

import pi.HRSmart.persistence.Postulation;
import pi.HRSmart.persistence.Report;
import pi.HRSmart.persistence.Rewards;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by alaa on 01/11/16.
 */
@Local
public interface IReportingServiceLocal {

    void add(Report r);
    void update(Report r);
    void delete(Report r);
    Report get(int id);
    List <Report> getAll();
    List <Report> getByPostulation(Postulation postulation);
    List <Report> getByReward(Rewards rewards);


}

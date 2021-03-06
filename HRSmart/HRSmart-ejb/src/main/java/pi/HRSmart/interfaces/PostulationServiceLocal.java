package pi.HRSmart.interfaces;


import pi.HRSmart.persistence.Postulation;
import pi.HRSmart.persistence.*;

import java.util.List;

/**
 * Created by BoB on 10/18/2016.
 */
public interface PostulationServiceLocal {

    void add(Postulation postulation);
    void update(Postulation postulation);
    void delete(Postulation postulation);
    Postulation getPostulation(int id);
    List<Postulation> getAllPostulations();
    List<Postulation> filterPostulationsByQuizResult(int threshold, Skill sk);
    List<Assessment> getPostulationResults(Postulation ps);
    Postulation getPostulationByUser(int idUser);
    List<Postulation> getAllByUser(int idUser);

}

package pi.HRSmart.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import pi.HRSmart.persistence.Certificat;
import pi.HRSmart.persistence.Skill;
import pi.HRSmart.persistence.UserSkill;

@Local
public interface CertificatServiceLocal {

	void add(Certificat certificat);
  	void update(Certificat certificat);
	void remove(Certificat certificat);
  	Certificat get(int id);
  	List<Certificat> getAll();
  	List<Certificat> getBySkill(int skill);


}

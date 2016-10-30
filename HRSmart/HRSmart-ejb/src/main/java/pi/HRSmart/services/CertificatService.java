package pi.HRSmart.services;

import java.security.AllPermission;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pi.HRSmart.interfaces.CertificatServiceLocal;
import pi.HRSmart.interfaces.UserSkillsServiceLocal;
import pi.HRSmart.persistence.Certificat;
import pi.HRSmart.persistence.Skill;
import pi.HRSmart.persistence.UserSkill;
import pi.HRSmart.persistence.UserSkillPk;


/**
 * Session Bean implementation class CertificatService
 */
@Stateless

public class CertificatService implements CertificatServiceLocal {

	/**
	 * @author yesmine
	 *
	 */

	@PersistenceContext(unitName = "HRSmart-ejb")
	EntityManager em;
	@EJB(beanName = "UserSkillsService")
	UserSkillsServiceLocal userSkillServiceLocal;

	public CertificatService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(Certificat certificat) {
		em.persist(certificat);
	}

	@Override
	public void update(Certificat certificat) {
		em.merge(certificat);

	}

	@Override
	public void remove(Certificat certificat) {
		em.remove(em.merge(certificat));

	}

	@Override
	public Certificat get(int id) {

		return em.find(Certificat.class, id);
	}

	@Override
	public List<Certificat> getAll() {

		Query query = em.createQuery("SELECT c FROM Certificat c");
	    return (List<Certificat>) query.getResultList();
	}

	
	@Override
	public List<Certificat> getBySkill(int skill) {
		Query query = em.createQuery("SELECT c from Certificat c where c.skill= skill");
		 return (List<Certificat>) query.getResultList();
	}

	
	
	

}

package pi.HRSmart.interfaces;
import java.util.List;
import javax.ejb.Remote;
import pi.HRSmart.persistence.Address;
import pi.HRSmart.persistence.Buisness;

@Remote
public interface AddressServiceRemote {
	
	void add(Address address);
  	void update(Address address);
	void remove(Address address);
	Address get(int id);
  	List<Address> getAll();
  	List<Address> getAllByBuisness(int idBuisness);
  	void setBuisnessToAddress(Address address, Buisness buisness);
}

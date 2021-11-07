 import java.io.Serializable;
import java.util.Date;

/**
 * A class making a Guest object
 * @author mohamed Sheikh Ismail, Tor Jacobsen & Faizan Yousaf
 * @version 1.0
 *
 */
public class Guest implements Serializable{
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private Date birthday;
	private String nationality;
	
	
	/**
	 * 6-argument constructor initializing the guest.
	 * 
	 * @param firstName String that receives firstName of the guest
	 * @param lastName String that receives lastName of the guest
	 * @param address String that receives address of the guest
	 * @param phone String that receives phone of the guest
	 * @param birthday String that receives birthday of the guest
	 * @param nationality String that receives nationality of the guest
	 */
	public Guest(String firstName, String lastName, String address, String phone, Date birthday, String nationality) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.birthday = birthday;
		this.nationality = nationality;
	}
	/**
	 * Gets the firstName String and returns it.
	 * @return firstName String of the guest
	 */

	public String getFirstName() {
		return firstName;
	}
	/**
	 * Replaces the firstName String with a new firstName.
	 * @param firstName String to replace firstName with
	 */

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Gets the lastName String and returns it.
	 * @return lastName String of the guest
	 */

	public String getLastName() {
		return lastName;
	}
	/**
	 * Replaces the lastName String with a new lasttName.
	 * @param lastName String replace lastName with
	 */

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Gets the address String and returns it..
	 * @return address String of the guest
	 */

	public String getAddress() {
		return address;
	}
	/**
	 * Replaces the address String with a new address.
	 * @param adress String replace address with
	 */

	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Gets the phone String and returns it..
	 * @return phone String of the guest
	 */

	public String getPhone() {
		return phone;
	}
	/**
	 * Replaces the phone String with a new phone String.
	 * @param phone String replace phone with
	 */

	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * Gets the birthday String and returns it.
	 * @return birthday String of the guest
	 */

	public Date getBirthday() {
		return birthday;
	}
	/**
	 * Replaces the birthday String with a new birthday.
	 * @param birthday String replace birthday with
	 */

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * Gets the nationality String and returns it..
	 * @return nationality String of the guest
	 */

	public String getNationality() {
		return nationality;
	}
	/**
	 * Replaces the nationality String with a new nationality.
	 * @param nationality String replace nationality with
	 */

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * method that returns the guests object as a String
	 */
	
	public String toString() {
		return firstName + " " + lastName;
	}
}

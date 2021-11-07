import java.io.Serializable;
import java.util.ArrayList;

/**
 *  A class containing a list of Reservation objects. 
 * @author Tor, Faizan & Mohamed
 * @version 1.0
 */
public class ReservationList implements Serializable{

	private ArrayList<Reservation> reservations;
	
	/**
	 * No-argument constructor initializing the ReservationList. 
	 */
	public ReservationList(){
		reservations = new ArrayList<Reservation>();
	}
	
	/**
	 * Gets how many Reservation object are inside the list as an int.
	 * @return the number of reservation object inside the list.
	 */
	public int size(){
		return reservations.size();
	}
	
	/**
	 * Adds a Reservation to the list.
	 * @param reservation the reservation to add to the list
	 */
	public void addReservation(Reservation reservation){
		reservations.add(reservation);
	}
	
	/**
	 * Add an reservationList to another reservationList.
	 * @param the reservationlist that is being added to the other reservationList.
	 * @return The reservation with both reservationlist that has been added together. 
	 */
	public ReservationList addReservaionList(ReservationList list){
		for(int i=0; i<reservations.size(); i++){
			list.addReservation(reservations.get(i));
		}
		return list;
	}
	
	/**
	 * Get Reservation object from position index from the list.
	 * @param index the position in the list of the reservation object
	 * @return the Reservation at the given index.
	 */
	public Reservation getReservation(int index){
		return reservations.get(index);
	}
	
	/**
	 * Remove the given Reservation from the list. 
	 * @param reservation the reservation that should be removed.
	 */
	public void removeReservation(Reservation reservation){
		reservations.remove(reservation);
	}
	
	/**
	 *  Method that returns String 
	 */
	public String toString(){
		String temp = "";
		for(int i=0; i<size(); i++){
			temp += reservations.get(i) + "\n\n";
		}
		return temp;
	}

	
}
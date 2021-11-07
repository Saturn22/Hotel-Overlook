import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A class making a Reservation object.
 * @author Mohamed Guudow, Tor Jacobsen & Faizan Yousaf
 * @version 1.0
 */
public class Reservation implements Serializable{
	
	private Date arrival;
	private Date departure;
	private ArrayList<Guest> guests;
	private Room room;
	private boolean extraBed;
	private int expectedGuests;
	private boolean lateArrival;
	private double discount;
	
	/**
	 * 8-argument constructor initializing the reservation.
	 * 
	 * @param arrival Date object that receives an arrival date.
	 * @param departure Date object that receives a departure date.
	 * @param guests Guest object that receives a guest.
	 * @param room Room object that receives a room.
	 * @param extrabed If it's true an extra bed is needed otherwise not.
	 * @param expectedGuests Get the value of expected guests.
	 * @param lateArrival If it's true the guests will arrive after 6PM.
	 * @param discount The amount subtracted from the total price in %.
	 */
	public Reservation(Date arrival, Date departure, Guest guest, Room room, boolean extraBed, int  expectedGuests, boolean lateArrival, double discount) {
		
		this.arrival = arrival;
		this.departure = departure;
		this.guests = new ArrayList<Guest>();
		guests.add(guest);
		this.room = room;
		this.extraBed = extraBed;
		this.expectedGuests = expectedGuests;
		this.lateArrival = lateArrival;
		this.discount = discount;
	}
	
	/**
	 * Gets the arrival date and returns it as a Date object.
	 * @return arrival date of the reservation.
	 */
	public Date getArrival() {
		return arrival;
	}
	
	/**
	 * Gets the departure date and returns it as a Date object.
	 * @return departure of the date
	 */
	public Date getDeparture() {
		return departure;
	}
	
	/**
	 * Replaces the departure date with a new departure date.
	 * @param departure Date object to replace departure with
	 */
	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	/**
	 * Returns the Guest object of the guest who made the reservation.
	 * @return the first Guest object from the guests ArrayList
	 */
	public Guest getGuest(){
		return guests.get(0);
	}
	/**
	 * Returns an Array of all Guest objects inside the ArrayList from the reservation.
	 * @return array of all Guest objects from the reservation
	 */
	public Guest[] getAllGuests() {
		Guest[] guests = (Guest[]) this.guests.toArray(new Guest[this.guests.size()]);
		return guests;
	}
	/**
	 * Removes the Guest object from ArrayList at the given index. 
	 * @param index the position in List that will be removed
	 */
	public void removeGuest(int index){
		guests.remove(index);
	}
	
	/**
	 * Add a Guest to the list.
	 * @param guest the guest to the list
	 */
	public void addGuest(Guest guest){
		guests.add(guest);
	}

	/**
	 * Gets Room object of the reservation
	 * @return Room object 
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * Gets the boolean value rather an extra bed is needed.
	 * @return true if extra bed is needed or false if not
	 */
	public boolean getExtrabed() {
		return extraBed;
	}

	/**
	 * Replaces the extrabed boolean with the given boolean value. 
	 * @param extrabed the boolean value to replace with. 
	 */
	public void setExtrabed(boolean extraBed) {
		this.extraBed = extraBed;
	}

	/**
	 * Gets an Integer with the number of expected guest.
	 * @return number of expected guests.
	 */
	public int getExpectedGuests() {
		return expectedGuests;
	}

	/**
	 * Replaces the number of expected guests with the given Integer value.
	 * @param expectedGuests the Integer value to replace it with.
	 */
	public void setExpectedGuests(int expectedGuests) {
		this.expectedGuests = expectedGuests;
	}
	
	/**
	 * Gets a true boolean if the guests arrive later than 6PM, otherwise false.  
	 * @return boolean value.
	 */
	public boolean getLateArrival() {
		return lateArrival;
	}

	/**
	 * Replaces the lateArrival boolean with the given boolean value. 
	 * @param lateArrival boolean value to replace it with.
	 */
	public void setLateArrival(boolean lateArrival) {
		this.lateArrival = lateArrival;
	}
	
	/**
	 * Gets a double value 
	 * @return double value.
	 */
	public double getDiscount() {
		return discount;
	}
	
	/**
	 * Replaces the discount double with the given double value.
	 * @param discount double value to replace it with.
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	/**
	 * Gets how many days the guests stayed.
	 * @return An int with the number of days stayed.
	 */
	public int getNumberOfDaysStayed(){
		
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
			String date1 = sdf.format(arrival);
			String date2 = sdf.format(departure);
			
			try {
			Date arrival = sdf.parse(date1);
			Date departure = sdf.parse(date2);
			
			int count = 0;
			for(int i=0; i<1000; i++){
				if(arrival.equals(departure)){
					return count;
				}else{
					arrival.setDate(arrival.getDate()+1);
					count++;
				}
			}
			}catch (ParseException e) {
				e.printStackTrace();
			}
		return -1;
	}
	
	/**
	 * Get the price of the stay.
	 * @return A double with the price of the stay.
	 */
	public double getPrice(){
		
		if(getDiscount() != 0){
			return (room.getPrice() * getNumberOfDaysStayed()) - (getDiscount()*((room.getPrice() * getNumberOfDaysStayed()) / 100));
		}else{
			return room.getPrice() * getNumberOfDaysStayed();
		}
	}
	/**
	 * Method that returns the reservation as a String firstName
	 */
	
	public String toString(){
		
		return "Arrival date: " + arrival + "\n" + "Departure date: " + departure + "\nGuests: " + guests.toString() + 
				"\nRoom: " + room + "\nExtraBed: " + extraBed + "\nExpectedguest: " + expectedGuests + "\nLateArrival: " + lateArrival;	
	}
	
	

	
}

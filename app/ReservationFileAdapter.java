import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class to get and save a reservationlist inside a bin file.
 * @author Tor, Faizan & Mohamed
 * @version 1.0
 */

public class ReservationFileAdapter {

	private FileIO mfio;
	private String fileName;
		
	
	/**
	 * 1-argument constructor initializing the fileName.
	 * @param fileName Name of the file where the reservationlist should be saved.
	 */
	public ReservationFileAdapter(String fileName){
	      mfio = new FileIO();
	      this.fileName = fileName;
	}		  
		
	/**
	 * Get the saved ReservationList from the file.
	 * @return ReservationList from the file.
	 */
	public ReservationList getAllReservations(){ 
		ReservationList reservations = new ReservationList();

	    try{
	    	reservations = (ReservationList)mfio.readObjectFromFile(fileName);
	    }catch (FileNotFoundException e){
	    	System.out.println("File not found");
	    }catch (IOException e){
	    	System.out.println("IO Error reading file !!!");
	    	e.printStackTrace();
	    }catch (ClassNotFoundException e){
	         System.out.println("Class Not Found");
	    }
	     return reservations;
	}
	
	/**
	 * Adds the given reservation to the resrevationList and save it in the file
	 * @param reservation Reservation that should be added to the file.
	 */
	public void addReservation(Reservation reservation){
		
		ReservationList reservations = getAllReservations();
		reservations.addReservation(reservation);
		try{
			mfio.writeToFile(fileName, reservations);
		}catch (FileNotFoundException e){
		    System.out.println("File not found");
		}catch (IOException e){
		    System.out.println("IO Error reading file");
		}   
	}
	
	/**
	 * Save the given ReservationList in the file.
	 * @param reservations The reservationList that is being saved in the file.
	 */
	public void saveReservations(ReservationList reservations){
		 
		try{
			mfio.writeToFile(fileName, reservations);
		}catch (FileNotFoundException e){
		    System.out.println("File not found");
		}catch (IOException e){
			System.out.println("IO Error writing to file");
		}
	}
	
	/**
	 * Get the reservation who arrival date is the same date as today.
	 * @return ReservationList with the arrival of today.
	 */
	public ReservationList getArrivalsFromToday(){
		
		ReservationList temp = new ReservationList();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String today = sdf.format(new Date());
		
		ReservationList list = getAllReservations();
		
		for(int i=0; i<list.size(); i++){
		String arrival = sdf.format(list.getReservation(i).getArrival());
			if(arrival.equals(today)){
				temp.addReservation(list.getReservation(i));
			}
		}
		return temp;
	}
	
	/**
	 * Get the reservation who departure date is the same date as today.
	 * @return ReservationList with the departure of today.
	 */
	public ReservationList getDeparturesFromToday(){
		Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
		String today = sdf.format(date);
		
		ReservationList list = getAllReservations();

		ReservationList temp = new ReservationList();
		
		for(int i=0; i<list.size(); i++){
		String departure = sdf.format(list.getReservation(i).getDeparture()); 
			if(departure.equals(today)){
				temp.addReservation(list.getReservation(i));
			}
			
		}
		return temp;
	}	
	
	/**
	 * Get all the reservation in the file where the guests last name is equal to the given name.
	 * @param name The reservation with this guest name should be returned.
	 * @return A ReservationList with reservation where the guest name is equal to the given name.
	 */
	public ReservationList getAllReservationsWithLastname(String name){
		ReservationList list = getAllReservations();
		ReservationList temp = new ReservationList();
		
		for(int i=0; i<list.size(); i++){
			if(list.getReservation(i).getGuest().getLastName().equals(name)){
				temp.addReservation(list.getReservation(i));
			}
		}
		return temp;
	}
	 
	/**
	 * Removes a reservation from the file with the given firstname, lastname and roomnumber.
	 * @param firstName
	 * @param lastName
	 * @param roomNumber
	 */
	public void removeReservation(String firstName, String lastName, int roomNumber){
		
		ReservationList list = getAllReservations(); 
		
		for(int i=0; i<list.size(); i++){
			if(list.getReservation(i).getGuest().getFirstName().equals(firstName) &&
			list.getReservation(i).getGuest().getLastName().equals(lastName) &&	
			list.getReservation(i).getRoom().getRoomNumber() == roomNumber){
				list.removeReservation(list.getReservation(i));
				break;
			}
		}
		saveReservations(list);
	}
	
	/**
	 * Gets a reservation from file the with the given firstname, lastname and roomnumber.
	 * @param firstName the firstname from the guest who booked the reservation
	 * @param lastNamethe lastname from the guest who booked the reservation
	 * @param roomNumber the room number of the reservation
	 * @return
	 */
	public Reservation getReservation(String firstName, String lastName, int roomNumber){
		
		ReservationList list = getAllReservations();
		
		for(int i=0; i<list.size(); i++){
			if(list.getReservation(i).getGuest().getFirstName().equals(firstName) && 
					list.getReservation(i).getGuest().getLastName().equals(lastName) && 
					list.getReservation(i).getRoom().getRoomNumber() == roomNumber){
				return list.getReservation(i);
			}
		}
		 return null;
	}
	
	/**
	 * Removes the given reservation from the file.
	 * @param reservation the reservation that should be removed from the file.
	 */
	public void removeReservation(Reservation reservation){
		removeReservation(reservation.getGuest().getFirstName(), reservation.getGuest().getLastName(), reservation.getRoom().getRoomNumber());
	}
	
	/**
	 * Checks in a reservation by remove it from one file to another.
	 * @param reservation the reservation that is being checked out.
	 */
	
	public void checkInReservation(Reservation reservation){
		ReservationFileAdapter checkInAdapter = new ReservationFileAdapter("data/checkIns.bin");
		ReservationFileAdapter adapter = new ReservationFileAdapter("data/reservations.bin");
		
		checkInAdapter.addReservation(reservation);
		adapter.removeReservation(reservation);
	}
	/**
	 * Checks out a reservation by remove it from one file to another.
	 * @param reservation the reservation that is being checked out.
	 */
	public void checkOutReservation(Reservation reservation){
		ReservationFileAdapter checkInAdapter = new ReservationFileAdapter("data/checkIns.bin");
		ReservationFileAdapter checkOutAdapter = new ReservationFileAdapter("data/checkOuts.bin");
		
		checkOutAdapter.addReservation(reservation);
		checkInAdapter.removeReservation(reservation);
	}
	/**
	 * A discount is being added to the reservation
	 * @param reservation the reservation were it should be added
	 * @param discount the amount as a double value
	 */
	public void addDiscount(Reservation reservation, double discount){
		removeReservation(reservation);
		reservation.setDiscount(discount);
		addReservation(reservation);
	}
	
}

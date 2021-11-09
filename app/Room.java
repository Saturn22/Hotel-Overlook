import java.io.Serializable;

/**
 * A Class to make Room objects
 * @author Mohamed Guudow, Tor Jacobsen & Faizan Yousaf
 * @version 1.0
 */
public class Room implements Serializable{
	
	private double price;
	private String roomType;
	private int roomNumber;
	private boolean smokingAllowed;	
	
	/**
	 * 4-Arguments constructor Initializing the Room
	 * @param price the price per night
	 * @param roomType the type of the Room
	 * @param roomNumber the number of the Room, that is being booked
	 * @param smokingAllowed the boolean value if smoking is allowed in the Room
	 */
	public Room(double price, String roomType, int roomNumber, boolean smokingAllowed) {
		
		this.price = price;
		this.roomType = roomType;
		this.roomNumber = roomNumber;
		this.smokingAllowed = smokingAllowed;
	}

	/**
	 * Gets the price of the Room.
	 * @return the price of the Room.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Sets a new price for the Room.
	 * @param price the price to replace the old one with
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the Room's Type .
	 * @return the Room's Type as a String 
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * Sets new Room type for the Room.
	 * @param roomType the room type to replace the old one
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	/**
	 * Gets the boolean value of if smoking is allowed or otherwise.
	 * @return true if smoking is allowed or else false
	 */
	public boolean isSmokingAllowed() {
		return smokingAllowed;
	}

	/**
	 * Gets the Room number
	 * @return the Room number as an int.
	 */
	public int getRoomNumber() {
		return roomNumber;
	}
	
	/**
	 * 
	 */
	public String toString() {
		return "Room [price=" + price + ", roomType=" + roomType + ", smokingAllowed=" + smokingAllowed +  ", roomNumber=" + roomNumber + "]";
	}
	
	/**
	 * Gets a copy of the Room object
	 * @return A copy of the Room object
	 */
	public Room copy(){
		return new Room(price, roomType, roomNumber, smokingAllowed) ;
	}
	
	public boolean equals(Object obj){
		
		if(!(obj instanceof Room)){
			return false;
		}
		Room other =(Room)obj;
		
		return price == (other.price) && roomType.equals(other.roomType) 
				&& smokingAllowed == (other.smokingAllowed)
				&& roomNumber == (other.roomNumber);
				
	}
}
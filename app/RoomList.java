import java.io.Serializable;
import java.util.ArrayList;

	/**
	 * A Class containing a list of Room objects
	 * @author Mohamed Guudow, Tor Jacobsen & Faizan Yousaf
	 * @version 1.0
	 */


public class RoomList implements Serializable{
	
	private ArrayList<Room> rooms;
	
	/**
	 * No-Argument constructor to initializing the RoomList
	 */
	public RoomList(){
		rooms = new ArrayList<Room>();
	}
	
	/**
	 * Gets the size of the list, which is total number of Room objects it contains
	 * @return the size of the list
	 */
	public int size() {
		return rooms.size();
	}
	
	/**
	 * Add a Room object to the List.
	 * @param room the Room object to add
	 */
	public void addRoom(Room room){
		rooms.add(room);
	}
	
	/**
	 * Sets new price of the Room depending on the room type.
	 * @param roomType the room type of Room object as a String
	 * @param price the price as a double to change with
	 */
	public void setPrice(String roomType, double price){
		for(int i=0; i<size(); i++){
			if(roomType.equals(rooms.get(i).getRoomType())){
				rooms.get(i).setPrice(price);
			}
		}
	}
	
	/**
	 * Replaces the old room type with new room type.
	 * @param oldRoomType the old room type
	 * @param newRoomType the new room type to replace with
	 */
	public void setRoomType(String oldRoomType, String newRoomType){
		for(int i=0; i<size(); i++){
			if(oldRoomType.equals(rooms.get(i).getRoomType())){
				rooms.get(i).setRoomType(newRoomType);
			}
		}	
	}
	
	/**
	 * Remove a Room object from the list
	 * @param room the Room object to remove
	 */
	public void removeRoom(Room room){
		for(int i=0; i<size(); i++){
			if(rooms.get(i).equals(room)){
				rooms.remove(i);
			}
		}
	}
	
	/**
	 * Gets a Room object from position index from the list 
	 * @param index the position of the Room object in the list 
	 * @return a room object at index
	 */
	public Room getRoom(int index){
		return rooms.get(index);
	}
	
	/**
	 * Gets an Array of Room objects with the given room type from the list.
	 * @param roomType the type of the Room object
	 * @return an Array of Room objects of given room type
	 */
	public Room[] getRooms(String roomType){
		
		ArrayList<Room> temp = new ArrayList<Room>();
		
		for(int i=0; i<size(); i++){
			if(roomType.equals(rooms.get(i).getRoomType())){
				temp.add(rooms.get(i));
			}
		}
		return temp.toArray(new Room[temp.size()]);	
	}
	
	/**
	 * Gets the number of Room objects with the given room type from the list.
	 * @param roomType the type of the Room object
	 * @return number of the given type of Room objects
	 */
	public int getNumberOfRooms(String roomType){
		int count=0;
		for(int i=0; i<size(); i++){
			if (rooms.get(i).getRoomType().equals(roomType)){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Gets an Array of Room objects room number with the given room type from the list
	 * @param roomType the type of the Room object
	 * @return an Array of the Room objects room number
	 */
	public int[] getAllNumbersFromRoomType(String roomType){
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i=0; i<size(); i++){
			if (rooms.get(i).getRoomType().equals(roomType)){
				temp.add(rooms.get(i).getRoomNumber());
			}
		}
		int[] other = new int[temp.size()];
		for(int i=0; i<other.length; i++){
			other[i] = temp.get(i);
		}
		return other;
	}
	
	/**
	 * Gets the price of the Room object with the given room type from the list
	 * @param the type the Room Object
	 * @return the price per night of the Room or -1 if there is any error
	 */
	public double getPriceFromRoomType(String roomType){
		for(int i=0; i<size(); i++){
			if (rooms.get(i).getRoomType().equals(roomType)){
				return rooms.get(i).getPrice();
			}
		}
		return -1;
	}
	
	/**
	 * Gets a String value(yes if smoking allowed, no if smoking is forbidden
	 *  or error) of the Room with the given type of the Room object from the list 
	 * @param roomType the type of the Room object
	 * @return the String value (yes, no , error)
	 */
	public String smokingAllowed(String roomType){
		
		for(int i=0; i<size(); i++){
			if(rooms.get(i).getRoomType().equals(roomType)){
				if(rooms.get(i).isSmokingAllowed()){
					return "Yes";
				}else{
					return "No";
				}
			}
				
		}
		return "Error";
		
	}
	
	/**
	 * Gets an Array of the all types of the Room objects
	 * @return an Array of the all types of the Room objects
	 */
	public String[] getAllRoomTypes(){
		ArrayList<String> temp = new ArrayList<String>();
		//temp.add(rooms.get(0).getRoomType());
		boolean b;
		for(int i=0; i<rooms.size(); i++){
			b = true;
			for(int j=0; j<temp.size(); j++){
				if(temp.get(j).equals(rooms.get(i).getRoomType())){
					b = false;
					break;
				}
			}
			if(b){
				temp.add(rooms.get(i).getRoomType());
			}	
		}
		return temp.toArray(new String[temp.size()]);
	}

}
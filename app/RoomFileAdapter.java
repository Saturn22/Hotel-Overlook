import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Class responsible for reading and writing RoomList objects
 * 
 * @author Mohamed Guudow, Tor Jacobsen & Faizan Yousaf
 */
public class RoomFileAdapter
{
   private MyFileIO mfio;
   private String fileName;

   /**
    * 1-argument constructor initializing RoomFileAdapter with the fileName.
    * 
    * @param fileName
    *           Name of the file where the Roomlist should be saved.
    */
   public RoomFileAdapter(String fileName)
   {
      mfio = new MyFileIO();
      this.fileName = fileName;
   }

   /**
    * Get the saved RoomList from the file.
    * 
    * @return RoomList from the file.
    */
   public RoomList getAllRooms()
   {

      RoomList rooms = new RoomList();

      try
      {
         rooms = (RoomList) mfio.readObjectFromFile(fileName);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      catch (IOException e)
      {
         System.out.println("IO Error reading file");
         e.printStackTrace();
      }
      catch (ClassNotFoundException e)
      {
         System.out.println("Class Not Found");
      }
      return rooms;
   }

   /**
    * Save the given RoomList in the file.
    * 
    * @param roomList
    *           the RoomList that is being saved in the file.
    */
   public void saveRooms(RoomList roomList)
   {

      try
      {
         mfio.writeToFile(fileName,roomList);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      catch (IOException e)
      {
         System.out.println("IO Error writing to file");
      }
   }

   /**
    * Gets RoomList object containing all the available Room objects between
    * arrival and departure Date objects
    * @param arrival the arrival date 
    * @param departure the departure date
    * @return RoomList object with available Room objects
    */
   public RoomList getAvailableRooms(Date arrival, Date departure)
   {
      RoomList roomList = getAllRooms();
      ReservationList list = new ReservationFileAdapter("data/reservations.bin").getAllReservations();
      ReservationList list2 = new ReservationFileAdapter("data/checkIns.bin").getAllReservations();

      list = list2.addReservaionList(list);

      // Check which rooms are available if not available it is being deleted
      // from roomList.
      for(int i=0; i<list.size(); i++){
		  if((list.getReservation(i).getDeparture().after(arrival) && !list.getReservation(i).getArrival().after(arrival)) ||
		((list.getReservation(i).getDeparture().after(arrival) && list.getReservation(i).getArrival().before(departure)))){
			 roomList.removeRoom(list.getReservation(i).getRoom());
	} 
}
      return roomList;
   }

   /**
    * Change the type of Room object from given room type from the RoomList with new one and save the changes
    * @param roomType the type of the Room Object
    * @param newRoomType the new type of the Room Object
    */
   public void changeRoomType(String roomType, String newRoomType)
   {
      RoomList list = getAllRooms();

      for (int i = 0; i < list.size(); i++)
      {
         if (list.getRoom(i).getRoomType().equals(roomType))
         {
            list.getRoom(i).setRoomType(newRoomType);
         }
      }
      saveRooms(list);
   }

   /**
    * Change the price of Room object from given room type from the RoomList
    * with a new one and save the cahnges
    * @param roomType the type of the Room Object
    * @param newPricethe new price of the Room Object
    *
    */
   public void changeRoomPrice(String roomType, double newPrice)
   {
      RoomList list = getAllRooms();

      for (int i = 0; i < list.size(); i++)
      {
         if (list.getRoom(i).getRoomType().equals(roomType))
         {
            list.getRoom(i).setPrice(newPrice);
         }
      }
      saveRooms(list);

   }

}

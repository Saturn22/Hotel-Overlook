
public class Exceptions extends RuntimeException{
	
	public Exceptions(String msg){
		super(msg);
	}
	
	
	public static void InvalidNumberDiscountException() {
		throw new Exceptions("Discount can't be less than zero");
	}
	
	public static void InvalidNumberExpectedGuestException() {
		throw new Exceptions("Number of expected guest can't be less than zero");
	}
	
	public static void InvalidDateException() {
		throw new Exceptions("Arrival date must be smaller than departure date.");
	}
	
	public static void DateBeforeTodaysDateException() {
		throw new Exceptions("Arrival date must be equal or larger than todays date.");
	}
	
	public static void InvalidStringException(){
		
		throw new Exceptions("All Feilds must be filled out.");
	}
	
	public static void InvalidRoomExceptiom(){
		throw new Exceptions("A room must be choosen to make a reservation.");
	}
	
	public static void InvalidChangeArrivalDateException(){
		throw new Exceptions("Arrival date can't be change. You will have to delete this booking and make a new one.");
	}

	public static void InvalidChangeDepartureDateException(){
		throw new Exceptions("Departure date can only be change to a smaller one. You will have to delete this booking and make a new one.");
	}
	
	public static void InvalidDeleteGuestExcpetion(){
		throw new Exceptions("You must pick a guest in the combobox to delete it.");
	}
	
	public static void InvalidRoomPriceException(){
		throw new Exceptions("Price must be bigger than zero.");
	}
	
	
	
}

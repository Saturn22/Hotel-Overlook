
public class MyExceptions extends RuntimeException{
	
	public MyExceptions(String msg){
		super(msg);
	}
	
	
	public static void InvalidNumberDiscountException() {
		throw new MyExceptions("Discount can't be less than zero");
	}
	
	public static void InvalidNumberExpectedGuestException() {
		throw new MyExceptions("Number of expected guest can't be less than zero");
	}
	
	public static void InvalidDateException(){
		throw new MyExceptions("Arrival date must be smaller than departure date.");
	}
	
	public static void DateBeforeTodaysDateException(){
		throw new MyExceptions("Arrival date must be equal or larger than todays date.");
	}
	
	public static void InvalidStringException(){
		
		throw new MyExceptions("All Feilds must be filled out.");
	}
	
	public static void InvalidRoomExceptiom(){
		throw new MyExceptions("A room must be choosen to make a reservation.");
	}
	
	public static void InvalidChangeArrivalDateException(){
		throw new MyExceptions("Arrival date can't be change. You will have to delete this booking and make a new one.");
	}

	public static void InvalidChangeDepartureDateException(){
		throw new MyExceptions("Departure date can only be change to a smaller one. You will have to delete this booking and make a new one.");
	}
	
	public static void InvalidDeleteGuestExcpetion(){
		throw new MyExceptions("You must pick a guest in the combobox to delete it.");
	}
	
	public static void InvalidRoomPriceException(){
		throw new MyExceptions("Price must be bigger than zero.");
	}
	
	
	
}

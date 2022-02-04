import java.util.ArrayList;

public class Restaurant 
{
	private static ArrayList<Reservation> myReservations = new ArrayList<Reservation>();
	private static final int numTables = 4;
	private static final int numChairs = 16;
	//MAYBE
	private static final int numChairsByTable = 4;
	
	public static void initRestaurant() 
	{
		RestaurantService.init();
	}
	
	public static ArrayList<Reservation> getMyReservations() 
	{
		return myReservations;
	}

	public static int getNumtables() {
		return numTables;
	}

	public static int getNumchairs() {
		return numChairs;
	}

	public static int getNumchairsbytable() {
		return numChairsByTable;
	}
	
	
}
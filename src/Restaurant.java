import java.util.ArrayList;

public class Restaurant 
{
	private static ArrayList<Reservation> myReservations = new ArrayList<>();
	private static final int numTables = 4;
	private static final int numChairs = 16;
	//MAYBE
	private static final int numChairsByTable = 4;
	private static final int openTime1 = 13;
	private static final int closeTime1 = 15;
	private static final int openTime2 = 19;
	private static final int closeTime2 = 22;
	
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
	
	public static int getOpenTime1()
	{
		return openTime1;
	}

	public static int getOpenTime2()
	{
		return openTime2;
	}

	public static int getCloseTime1()
	{
		return closeTime1;
	}

	public static int getCloseTime2()
	{
		return closeTime2;
	}
}
import java.util.ArrayList;

public class Restaurant 
{
	private static ArrayList<Reservation> myReservations = new ArrayList<>();
	private static final int numTables = 4;
	private static final int numChairs = 16;
	//MAYBE
	private static final int numChairsByTable = 4;
	private static int openTime1 = 13;
	private static int closeTime1 = 15;
	private static int openTime2 = 19;
	private static int closeTime2 = 22;
	
	public static void initRestaurant() 
	{
		RestaurantService.init();
	}
	
	public static ArrayList<Reservation> getMyReservations() 
	{
		return myReservations;
	}

	public static void setMyReservations(ArrayList<Reservation> myNewList)
	{
		myReservations=myNewList;
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

	public static void setOpenTime1(int openTime1) {
		Restaurant.openTime1 = openTime1;
	}

	public static void setCloseTime1(int closeTime1) {
		Restaurant.closeTime1 = closeTime1;
	}

	public static void setOpenTime2(int openTime2) {
		Restaurant.openTime2 = openTime2;
	}

	public static void setCloseTime2(int closeTime2) {
		Restaurant.closeTime2 = closeTime2;
	}
}
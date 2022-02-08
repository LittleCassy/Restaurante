import java.io.*;
import java.util.ArrayList;

public class Restaurant
{
	private ArrayList<Reservation> myReservations = new ArrayList<>();
	private final int numTables = 4;
	private final int numChairs = 16;
	//MAYBE
	private final int numChairsByTable = 4;
	private int openTime1 = 13;
	private int closeTime1 = 15;
	private int openTime2 = 19;
	private int closeTime2 = 22;

	//region Getter Setter
	
	public ArrayList<Reservation> getMyReservations()
	{
		return myReservations;
	}

	public void setMyReservations(ArrayList<Reservation> myNewList)
	{
		myReservations=myNewList;
	}

	public int getNumtables() {
		return numTables;
	}

	public int getNumchairs() {
		return numChairs;
	}

	public int getNumchairsbytable() {
		return numChairsByTable;
	}
	
	public int getOpenTime1()
	{
		return openTime1;
	}

	public int getOpenTime2()
	{
		return openTime2;
	}

	public int getCloseTime1()
	{
		return closeTime1;
	}

	public int getCloseTime2()
	{
		return closeTime2;
	}

	public void setOpenTime1(int openTime1) {
		this.openTime1 = openTime1;
	}

	public void setCloseTime1(int closeTime1) {
		this.closeTime1 = closeTime1;
	}

	public void setOpenTime2(int openTime2) {
		this.openTime2 = openTime2;
	}

	public void setCloseTime2(int closeTime2) {
		this.closeTime2 = closeTime2;
	}

	//endregion
}
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantService implements Serializable
{
	static Scanner input = new Scanner(System.in);
	static Restaurant myRestaurant = new Restaurant();
	
	public static void init() {
		FileShenanigans.createFile();
		printMenu();
	}
	
	public static void printMenu()
	{
		System.out.println("///////////////////////////////////////");
		System.out.println("//// Welcome to Cassy FazCat's Pizza");
		System.out.println("///////////////////////////////////////");
		System.out.println("//// What can we do for you?");
		System.out.println("////");
		System.out.println("//// 1- New reservation");
		System.out.println("////");
		System.out.println("//// 2- Check reservation");
		System.out.println("////");
		System.out.println("//// 3- Change restaurant's schedule");
		System.out.println("////");
		System.out.println("///////////////////////////////////////");
		
		catchData();
	}

	//region Crear reserva

	public static void catchData() 
	{
			try 
			{
				System.out.println("////\n//// What would you like to do?");
				int tempChoice = input.nextInt();
				switch (tempChoice) {
					case 1 -> newReservation();
					case 2 -> checkSpecificReservation();
					case 3 -> changeSchedule();
					default -> {
						System.out.println("\n////\n//// Input one of the numbers in the menu. Please, try again.");
						catchData();
					}
				}
			}catch(Exception e) 
			{
				System.out.println("\n////\n//// Input one of the numbers in the menu. Please, try again.");
				catchData();
			}
	}

	public static void newReservation() 
	{
		try{
			int aux_Year = RestaurantInputManager.askYear();
			int aux_Month = RestaurantInputManager.askMonth();
			int aux_Day = RestaurantInputManager.askDay(aux_Month, aux_Year);
			int aux_Hour = RestaurantInputManager.askHour();
			int aux_NumTable = RestaurantInputManager.customerNumber();

			LocalDateTime reservoirDate = LocalDateTime.of(aux_Year, aux_Month, aux_Day, aux_Hour, 0); //TODO Minutos

			if(checkOccupancy(reservoirDate, aux_NumTable)){
				createReservoir(reservoirDate, aux_NumTable);
			}else{
				System.out.println("\n////\n//// Ow, Apologize. We are full at that time \n//// Try another date, or kill some of your friends.");
			}
		}catch (Exception e){
			System.out.println("\n////\n//// Ooopsies! Something went wrong. \\n//// Please, try again later.");
		}

	}
	
	public static void createReservoir(LocalDateTime reservoirDate, int tablesToOccupy) 
	{
		System.out.println("\n////\n//// May we know your name? So we can refer properly to you.");
		String tempName= RestaurantInputManager.askName();
		System.out.println("\n////\n//// Any phone number? Just in case of any totally unlikely and unexpected incident.\n//// No need for prefixes!");
		int tempPhone= RestaurantInputManager.askPhone();

		Reservation newReservation= new Reservation(tempName, tempPhone, tablesToOccupy, reservoirDate);
		addReservoir(newReservation);
	}
	
	public static void addReservoir(Reservation newReservoir) 
	{
		myRestaurant.getMyReservations().add(newReservoir);
		System.out.println("\n////\n//// Your reservation has been completed. We'll be waiting for you!");
		FileShenanigans.writeFile(myRestaurant.getMyReservations());
		printMenu();
	}

	//endregion

	//region Checks

	public static void checkSpecificReservation()
	{
		System.out.println("\n////\n//// Give us the name used in the reservation process.");
		String tempName= RestaurantInputManager.askName();
		System.out.println("\n////\n//// And now, the phone used in the same reservation.");
		int tempPhone= RestaurantInputManager.askPhone();

		Reservation aux = checkList(tempName,tempPhone);
		if(aux==null){
			System.out.println("\n////\n//// Oh-Oh. We can not find that reservation. Please, check the data.");
		}else{
			System.out.println(aux);
		}

		printMenu();
	}

	public static Reservation checkList(String name, int phone)
	{
		Reservation aux = null;

		for (Reservation res : myRestaurant.getMyReservations()) {
			if(res.getReservoirName().equals(name) && res.getPhoneNumber() == phone)
			{
				aux = res;
			}
		}

		return aux;
	}
	
	public static boolean checkOccupancy(LocalDateTime checkReservoir, int tablesNeeded)
	{
		int tempHourOccupancy = 0;

		for (Reservation res : myRestaurant.getMyReservations()) {
			if(res.getReservoirDate().equals(checkReservoir)) 
			{
				tempHourOccupancy += res.getNumberOfReservedTables();
			}
		}

		return tempHourOccupancy + tablesNeeded <= myRestaurant.getNumtables();
	}

	//endregion

	//region Schedule

	public static void changeSchedule()
	{
		System.out.println("///////////////////////////////////////");
		System.out.println("//// Management department");
		System.out.println("///////////////////////////////////////");
		System.out.println("//// What can we do for you?");
		System.out.println("////");
		System.out.println("//// 1- Change morning schedule");
		System.out.println("////");
		System.out.println("//// 2- Change afternoon schedule");
		System.out.println("////");
		System.out.println("//// 3- Go back");
		System.out.println("////");
		System.out.println("///////////////////////////////////////");

		try{
			int tempChoice = input.nextInt();

			switch (tempChoice)
			{
				case 1:
					changeMorningSchedule();
					break;
				case 2:
					changeAfternoonSchedule();
					break;
				case 3:
					printMenu();
					break;
				default:
					System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers in the MENU. \n//// Please, try again.");
					changeSchedule();
					break;
			}
		}catch (Exception e){
			System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
			changeSchedule();
		}

	}

	public static void changeMorningSchedule()
	{
		int aux_Open1 = RestaurantInputManager.newSchedule();
		int aux_Close1 = RestaurantInputManager.newSchedule();

		if(aux_Open1>=myRestaurant.getOpenTime2()){
			System.out.println("\n////\n//// Oh-Oh. We are already open at that time. Please, check the data.");
			changeSchedule();
		}else if(myRestaurant.getCloseTime2()<aux_Close1){
			System.out.println("\n////\n//// Oh-Oh. We weren't even closed at that time! Please, check the data.");
			changeSchedule();
		}else{
			myRestaurant.setOpenTime1(aux_Open1);
			myRestaurant.setCloseTime1(aux_Close1);
			printSchedule();
			changeSchedule();
		}
	}

	public static void changeAfternoonSchedule()
	{
		int aux_Open2 = RestaurantInputManager.newSchedule();
		int aux_Close2 = RestaurantInputManager.newSchedule();

		if(aux_Open2>=myRestaurant.getOpenTime1()){
			System.out.println("\n////\n//// Oh-Oh. We are already open at that time. Please, check the data.");
			changeSchedule();
		}else if(myRestaurant.getCloseTime1()>aux_Close2){
			System.out.println("\n////\n//// Oh-Oh. We weren't even closed at that time! Please, check the data.");
			changeSchedule();
		}else{
			myRestaurant.setOpenTime2(aux_Open2);
			myRestaurant.setCloseTime2(aux_Close2);
			printSchedule();
			changeSchedule();
		}
	}

	public static void printSchedule()
	{
		System.out.println(myRestaurant.getOpenTime1() + " - " + myRestaurant.getCloseTime1() + "   " + myRestaurant.getOpenTime2()+" - " + myRestaurant.getCloseTime2());
	}

	//endregion

}
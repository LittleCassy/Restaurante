import java.time.LocalDateTime;
import java.util.Scanner;

public class RestaurantService
{
	static Scanner input = new Scanner(System.in);
	
	public static void init() 
	{
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
		System.out.println("//// 3- Check all reservations");
		System.out.println("////");
		System.out.println("///////////////////////////////////////");
		
		catchData();
	}
	
	public static void catchData() 
	{
			try 
			{
				System.out.println("////\n//// What would you like to do?");
				int tempChoice = input.nextInt();
				switch (tempChoice) {
				case 1:
					newReservation();
					break;
				case 2:
					checkSpecificReservation();
					break;
				case 3:
					checkReservationList();
					break;

				default:
					System.out.println("\n////\n//// Input one of the numbers in the menu. Please, try again.");
					catchData();
					break;
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
			int aux_Month = RestaurantInputManager.askMonth();
			int aux_Day = RestaurantInputManager.askDay(aux_Month);
			int aux_Hour = RestaurantInputManager.askHour();
			int aux_NumTable = RestaurantInputManager.customerNumber();

			LocalDateTime reservoirDate = LocalDateTime.of(LocalDateTime.now().getYear(), aux_Month, aux_Day, aux_Hour, 0); //TODO Minutos

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
		Restaurant.getMyReservations().add(newReservoir);
		System.out.println("\n////\n//// Your reservation has been completed. We'll be waiting for you!");
		printMenu();
	}

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

		for (Reservation res : Restaurant.getMyReservations()) {
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

		for (Reservation res : Restaurant.getMyReservations()) {
			if(res.getReservoirDate().equals(checkReservoir)) 
			{
				tempHourOccupancy += res.getNumberOfReserverdTables();
			}
		}

		return tempHourOccupancy + tablesNeeded <= Restaurant.getNumtables();
	}

	public static void checkReservationList()
	{
		System.out.println(Restaurant.getMyReservations());
		printMenu();
	}


}
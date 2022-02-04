import java.time.LocalDateTime;
import java.util.Scanner;

public class RestaurantService
{
	static Scanner input = new Scanner(System.in);
	
	public static void init() 
	{
		printMenu();
		//newReservation();
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
		System.out.println("//// 3- Save reservoir's register");
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
					//TODO Revisar lista
					break;
				case 3:
					//TODO Guardar registro de reservas
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
		String tempName=null;
		int tempPhone=0;
		

		Reservation newReservation= new Reservation(tempName, tempPhone, tablesToOccupy, reservoirDate);
		addReservoir(newReservation);



	}
	
	public static void addReservoir(Reservation newReservoir) 
	{
		Restaurant.getMyReservations().add(newReservoir);
		System.out.println("\n////\n//// Your reservation has been completed. We'll be waiting for you!");
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
}
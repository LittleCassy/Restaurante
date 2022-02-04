import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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
		
			LocalDateTime reservoirDate = null;
			
			try 
			{
				System.out.println("\n////\n//// When do you want the reservation? Input in the form: HH DD MM");
				int tempHour = input.nextInt();
				int tempDay = input.nextInt();
				int tempMonth = input.nextInt();
				
				reservoirDate = LocalDateTime.of(LocalDateTime.now().getYear(), tempMonth, tempDay, tempHour, 00);
				
			}catch(Exception e) 
			{
				System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage \n//// integers in the form: HH DD MM. Please, try again.");
				newReservation();
			}
			
			try 
			{
				System.out.println("\n////\n//// And... How many are you?");
				int tempCustomers = input.nextInt();
				
				if(checkOcupancy(reservoirDate, (int)tempCustomers/4)) 
				{
					createReservoir(reservoirDate, (int)tempCustomers/4);
				}else 
				{
					System.out.println("\n////\n//// Ow, Apologize. We are full at that time \n//// Do you want another date?");
				}
				
			}catch(Exception e) 
			{
				System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage \n//// integers. Please, try again.");
				newReservation();
			}
	}
	
	public static void createReservoir(LocalDateTime reservoirDate, int tablesToOccupy) 
	{
		String tempName=null;
		int tempPhone=0;
		
		try 
		{
			System.out.println("\n////\n//// Congrats! We have a nice place for you. \\n//// How should we call you?");
			tempName = input.next();
			
		}catch(Exception e) 
		{
			System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage \n//// text in UTF-8 encode.");
			createReservoir(reservoirDate, tablesToOccupy);
		}
		
		try 
		{
			System.out.println("\n////\n//// And last, but not least, a phone number.");
			tempPhone = input.nextInt();
			
		}catch(Exception e) 
		{
			System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage \n//// integers. Please, try again.");
			createReservoir(reservoirDate, tablesToOccupy);
		}
		
		Reservation newReservation= new Reservation(tempName, tempPhone, tablesToOccupy, reservoirDate);
		addReservoir(newReservation);
	}
	
	public static void addReservoir(Reservation newReservoir) 
	{
		Restaurant.getMyReservations().add(newReservoir);
		System.out.println("\n////\n//// Your reservation has been completed. We'll be waiting for you!");
	}
	
	public static boolean checkOcupancy(LocalDateTime checkReservoir, int tablesNeeded) 
	{
		int tempHourOcupancy = 0;
		
		for (Reservation res : Restaurant.getMyReservations()) {
			if(res.getReservoirDate().equals(checkReservoir)) 
			{
				tempHourOcupancy += res.getNumberOfReserverdTables();
			}
		}
 		
		if((tempHourOcupancy+tablesNeeded) > Restaurant.getNumtables()) 
		{
			return false;
		}else 
		{
			return true;
		}
	}
}
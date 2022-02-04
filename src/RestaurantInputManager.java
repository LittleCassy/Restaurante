import java.time.LocalDateTime;
import java.util.Scanner;

public class RestaurantInputManager
{
    static Scanner input = new Scanner(System.in);

    public static int askHour(){
        int hour=0;
        do{
            try{
                System.out.println("\n////\n//// When do you want the reservation? Our schedule is from 13-15 and 19-22");
                hour = input.nextInt();

                if((hour < Restaurant.getOpenTime1() && hour >= Restaurant.getCloseTime1()) || (hour < Restaurant.getOpenTime2() && hour >= Restaurant.getCloseTime2())){
                    System.out.println("Comply");
                    break;
                }else{
                    System.out.println("\n////\n//// Oh, sorry. Our schedule is from 13-15 and 19-22. \n//// Please, try again.");
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while (!(hour < Restaurant.getOpenTime1() && hour >= Restaurant.getCloseTime1()) && !(hour < Restaurant.getOpenTime2() && hour >= Restaurant.getCloseTime2()));
        return hour;
    }

    public static int askMonth(){
        int month=0;
        do{
            try{
                System.out.println("\n////\n//// Which month are we talking about? Please, only use Integers");
                month = input.nextInt();
                if(month<=0||month>12)
                {
                    System.out.println("\n////\n//// Ow, sorry. Months go from 01 to 12. \n//// Please, go to EGB.");
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while (month<0 || month>12);
        return month;
    }

    public static int askDay(int month) {
        int day=0;

        do{
            try{
                System.out.println("\n////\n//// Which day would it be? Please, only use Integers");
                day = input.nextInt();
                if(day<0 || day>31)
                {
                    System.out.println("\n////\n//// Ow, sorry. Days, at it's best, goes from 01 to 31. \n//// Please, go to EGB.");
                }else if(!checkDayOfMonth(day,month)){
                    System.out.println("\n////\n//// Ouch! Unluckily, that month doesn't have that many days. \n//// Please, go try again.");
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while ((day<0 || day>31) || !checkDayOfMonth(day,month));
        return month;
    }

    public static boolean checkDayOfMonth(int day, int month){
        try{
            LocalDateTime testDate = LocalDateTime.of(LocalDateTime.now().getYear(), month, day, 0, 0);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static int customerNumber() {
        int customers=0;

        do{
            try{
                System.out.println("\n////\n//// How many are you, guys? Please, only use Integers");
                customers = input.nextInt();
                if(customers<=0 || customers>Restaurant.getNumchairs())
                {
                    System.out.println("\n////\n//// Ow, sorry. It seems we don't have space for all of you \n//// Please, try again.");
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while (customers<=0 || customers>Restaurant.getNumchairs());
        return customers/Restaurant.getNumchairsbytable();
    }
}

    /*
			try
			{
				System.out.println("\n////\n//// And... How many are you?");
				int tempCustomers = input.nextInt();

				if(tempCustomers/4 <= Restaurant.getNumtables())
				{
					if(checkOccupancy(reservoirDate, tempCustomers/4))
					{
						createReservoir(reservoirDate, tempCustomers/4);
					}else
					{
						System.out.println("\n////\n//// Ow, Apologize. We are full at that time \n//// Do you want another date?");
					}
				}else{
					System.out.println("\n////\n//// Ow, sorry. That exceeds our maximum occupancy \n//// Sorry!");
					printMenu();
				}
			}catch(Exception e)
			{
				System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage \n//// integers. Please, try again.");
				newReservation();
			}
	*/

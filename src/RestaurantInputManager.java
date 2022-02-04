import java.time.LocalDateTime;
import java.util.Scanner;

public class RestaurantInputManager
{
    static Scanner input = new Scanner(System.in);

    public static int askHour(){
        int hour=0;
        boolean sentinel=false;
        do{
            try{
                hour=0;
                System.out.println("\n////\n//// When do you want the reservation? Our schedule is from 13-15 and 19-22");
                hour = input.nextInt();

                if((hour >= Restaurant.getOpenTime1() && hour < Restaurant.getCloseTime1()) || (hour >= Restaurant.getOpenTime2() && hour < Restaurant.getCloseTime2())){
                    sentinel=true;
                }else{
                    System.out.println("\n////\n//// Oh, sorry. Our schedule is from 13-15 and 19-22. \n//// Please, try again.");
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while (!sentinel);
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

    public static String askName(){
        System.out.println("\n////\n//// May we know your name? So we can refer properly to you.");
        return input.next();
    }

    public static int askPhone() {
        int phone=0;

        do{
            try{
                System.out.println("\n////\n//// Any phone number? Just in case of any totally unlikely and unexpected incident.\n//// No need for prefixes!");
                phone = input.nextInt();
                if(phone<100000000)
                {
                    System.out.println("\n////\n//// Ow, sorry. We don't recognize the number.\n//// Please, input a 9 digit long number.");
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while (phone<100000000);
        return phone;
    }
}

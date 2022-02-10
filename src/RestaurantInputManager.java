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
                System.out.println("\n////\n//// When do you want the reservation? Our schedule is from " + RestaurantService.myRestaurant.getOpenTime1()+ "-" + RestaurantService.myRestaurant.getCloseTime1() + " and " + RestaurantService.myRestaurant.getOpenTime2() + "-" + RestaurantService.myRestaurant.getCloseTime1() + "\n////  " + RestaurantService.myRestaurant.getCloseTime1() + " and " + RestaurantService.myRestaurant.getCloseTime2() + " are not included");
                hour = input.nextInt();

                if((hour >= RestaurantService.myRestaurant.getOpenTime1() && hour < RestaurantService.myRestaurant.getCloseTime1()) || (hour >= RestaurantService.myRestaurant.getOpenTime2() && hour < RestaurantService.myRestaurant.getCloseTime2())){
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

    public static int checkHour(){
        int hour=0;
        boolean sentinel=false;
        do{
            try{
                hour=0;
                System.out.println("\n////\n//// When was your reservation?");

                if(hour<0 || hour>24)
                {
                    System.out.println("\n////\n//// Ow, sorry. The day doesn't have that many hours.\n//// Please, make you employees relax for some time at least.");
                }else{
                    sentinel=true;
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while (!sentinel);
        return hour;
    }

    public static int askYear(){
        int year=0;
        boolean sentinel=false;
        do{
            try{
                System.out.println("\n////\n//// Which year are we talking about? Please, only use Integers");
                year = input.nextInt();
                if(year<LocalDateTime.now().getYear())
                {
                    System.out.println("\n////\n//// Ow, sorry. That year has already went by. \n//// Please, get a watch.");
                }else{
                    sentinel=true;
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while (!sentinel);
        return year;
    }

    public static int askMonth(){
        int month=0;
        boolean sentinel=false;
        do{
            try{
                System.out.println("\n////\n//// Which month are we talking about? Please, only use Integers");
                month = input.nextInt();
                LocalDateTime testReservoir = LocalDateTime.of(LocalDateTime.now().getYear(), month, LocalDateTime.now().getDayOfMonth(), 12,0);

                if(testReservoir.isBefore(LocalDateTime.now())){
                    System.out.println("\n////\n//// Ow, sorry. This month has already passed \n//// Please, check your watches.");
                }else{
                    sentinel=true;
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while (!sentinel);
        return month;
    }

    public static int askDay(int month, int year) {
        int day=0;
        boolean sentinel=false;
        do{
            try{
                System.out.println("\n////\n//// Which day would it be? Please, only use Integers");
                day = input.nextInt();
                if(day<0 || day>31)
                {
                    System.out.println("\n////\n//// Ow, sorry. Days, at it's best, goes from 01 to 31. \n//// Please, go to EGB.");
                }else if(!checkDayOfMonth(day,month, year)){
                    System.out.println("\n////\n//// Ouch! Unluckily, that month doesn't have that many days. \n//// Please, go try again.");
                }else{
                    sentinel=true;
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while (!sentinel);
        return day;
    }

    public static boolean checkDayOfMonth(int day, int month, int year){
        try{
            LocalDateTime testDate = LocalDateTime.of(year, month, day, 0, 0);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static int customerNumber() {
        int customers=0;
        boolean sentinel=false;
        do{
            try{
                System.out.println("\n////\n//// How many are you, guys? Please, only use Integers");
                customers = input.nextInt();
                if(customers<=0 || customers>RestaurantService.myRestaurant.getNumchairs())
                {
                    System.out.println("\n////\n//// Ow, sorry. It seems we don't have space for all of you \n//// Please, try again.");
                }else{
                    sentinel=true;
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while (!sentinel);

        if(customers%RestaurantService.myRestaurant.getNumchairsbytable()!=0){
            return (customers/RestaurantService.myRestaurant.getNumchairsbytable())+1;
        }else{
            return customers/RestaurantService.myRestaurant.getNumchairsbytable();
        }
    }

    public static String askName(){
        return input.next();
    }

    public static int askPhone() {
        int phone=0;
        boolean sentinel=false;
        do{
            try{
                phone = input.nextInt();
                if(phone<100000000)
                {
                    System.out.println("\n////\n//// Ow, sorry. We don't recognize the number.\n//// Please, input a 9 digit long number.");
                }else{
                    sentinel=true;
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while (!sentinel);
        return phone;
    }

    public static int newSchedule()
    {
       int tempSchedule=-1;
       boolean sentinel=false;

        do{
            try{
                System.out.println("\n////\n//// What is the new time?\n//// Remember to use the 24h format!");
                tempSchedule = input.nextInt();
                if(tempSchedule<0 || tempSchedule>24)
                {
                    System.out.println("\n////\n//// Ow, sorry. The day doesn't have that many hours.\n//// Please, make you employees relax for some time at least.");
                }else{
                    sentinel=true;
                }
            }catch (Exception e)
            {
                System.out.println("\n////\n//// Ow, sorry. Our automated system can only manage integers. \n//// Please, try again.");
            }
        }while (!sentinel);

        return tempSchedule;
    }
}

import java.io.*;
import java.util.ArrayList;

public abstract class FileShenanigans implements Serializable
{
    static File myObj = new File("listaReservas.ser");

    public static void createFile(){
        try {
            if (myObj.createNewFile()) {
            } else {
                readFile(RestaurantService.myRestaurant.getMyReservations());
            }
        } catch (Exception e) {
            System.out.println("File: An error occurred.");
        }
    }

    public static void writeFile(ArrayList<Reservation> myReservations)
    {
        try {
            FileOutputStream fos = new FileOutputStream(myObj);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(myReservations);
            fos.close();
            oos.close();
        }catch (Exception e) {
            System.out.println("File: An error occurred." + e);
        }
    }

    public static void readFile(ArrayList<Reservation> myReservations)
    {
        try {
            FileInputStream fis = new FileInputStream(myObj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            RestaurantService.myRestaurant.setMyReservations((ArrayList<Reservation>) ois.readObject());
            ois.close();
            ois.close();
        }catch (Exception e)
        {
            System.out.println("File: An error occurred." + e);
        }
    }
}

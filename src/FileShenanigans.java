import java.io.*;

public abstract class FileShenanigans implements Serializable
{
    static File myRestaurant = new File("Restaurante.ser");

    //region Horario Restaurante

    public static void createRestaurantFile(){
        try {
            if (myRestaurant.createNewFile()) {
                writeScheduleList();
            }else {
                readScheduleList();
            }
        } catch (Exception e) {
            System.out.println("File: An error occurred.");
        }
    }

    public static void writeScheduleList()
    {
        try {
            FileOutputStream fos = new FileOutputStream(myRestaurant);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(RestaurantService.myRestaurant);
            fos.close();
            oos.close();
        }catch (Exception e) {
            System.out.println("File: An error occurred." + e);
        }
    }

    public static void readScheduleList()
    {
        try {
            FileInputStream fis = new FileInputStream(myRestaurant);
            ObjectInputStream ois = new ObjectInputStream(fis);
            RestaurantService.myRestaurant =(Restaurant)ois.readObject();
            ois.close();
            ois.close();
        }catch (Exception e)
        {
            System.out.println("File: An error occurred." + e);
        }

    //endregion
    }
}

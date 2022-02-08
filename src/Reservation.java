import java.io.Serializable;
import java.time.LocalDateTime;

public class Reservation implements Serializable
{
	private String reservoirName;
	private int phoneNumber;
	private int numberOfClients;
	private int numberOfReservedTables;
	private LocalDateTime reservoirDate;
	
	

	public Reservation(String reservoirName, int tempPhone, int numberOfReservedTables, LocalDateTime reservoirDate) {
		this.reservoirName = reservoirName;
		this.phoneNumber = tempPhone;
		this.numberOfReservedTables = numberOfReservedTables;
		this.reservoirDate = reservoirDate;
	}

	public String getReservoirName() {
		return reservoirName;
	}

	public void setReservoirName(String reservoirName) {
		this.reservoirName = reservoirName;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getNumberOfClients() {
		return numberOfClients;
	}

	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}

	public int getNumberOfReservedTables() {
		return numberOfReservedTables;
	}

	public void setNumberOfReservedTables(int numberOfReservedTables) {
		this.numberOfReservedTables = numberOfReservedTables;
	}

	public LocalDateTime getReservoirDate() {
		return reservoirDate;
	}

	public void setReservoirDate(LocalDateTime reservoirDate) {
		this.reservoirDate = reservoirDate;
	}

	@Override
	public String toString() {
		return "/////////// \nClient: " + reservoirName + "\nDate: " + reservoirDate + "\nPhone: " + phoneNumber + "\nTables Occupied: " + numberOfReservedTables + "\n///////////";
	}
}
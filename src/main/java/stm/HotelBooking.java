package stm;

public interface HotelBooking extends Service {
	public void bookHotel(int rooms);
	public boolean getBooked();
}

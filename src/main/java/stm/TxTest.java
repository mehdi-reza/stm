package stm;

import org.jboss.stm.Container;
import org.jboss.stm.Container.TYPE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arjuna.ats.arjuna.AtomicAction;

public class TxTest {

	Logger logger = LoggerFactory.getLogger(TxTest.class);

	public static void main(String[] args) {
		new TxTest().test1();
	}

	private void test1() {
		
		Container<Service> c = new Container<>(TYPE.PERSISTENT);
		
		FlightBooking flight = (FlightBooking) c.create(new FlightBookingImpl());
		HotelBooking hotel = (HotelBooking) c.create(new HotelBookingImpl());

		AtomicAction aa = new AtomicAction();
		
		try {
			aa.begin();
				flight.bookFlight();
				hotel.bookHotel(2);
			
			logger.info("Committing transaction");
			aa.commit();
		} catch (Exception e) {
			logger.info("Aborting transaction");
			aa.abort();
		}

		logger.info("Is flight booked: "+flight.getBooked());
		logger.info("Is hotel booked: "+hotel.getBooked());
		
	}
}

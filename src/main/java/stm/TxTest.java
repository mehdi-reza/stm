package stm;

import org.jboss.stm.Container;
import org.jboss.stm.Container.MODEL;
import org.jboss.stm.Container.TYPE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arjuna.ats.arjuna.AtomicAction;
import com.arjuna.ats.txoj.Lock;
import com.arjuna.ats.txoj.LockMode;

public class TxTest {

	Logger logger = LoggerFactory.getLogger(TxTest.class);

	public static void main(String[] args) {
		new TxTest().test1();
	}

	private void test1() {
		
		//Container<Service> c = new Container<>(TYPE.PERSISTENT, MODEL.EXCLUSIVE);

		FlightBookingImpl flight = new FlightBookingImpl();
		HotelBookingImpl hotel = new HotelBookingImpl();

		AtomicAction a = new AtomicAction();
		
		try {
			a.begin();
				flight.setlock(new Lock(LockMode.WRITE));
				flight.bookFlight();
				
				hotel.setlock(new Lock(LockMode.WRITE));
				hotel.bookHotel(2);
			a.commit();
		} catch (Exception e) {
			logger.info("Aborting transaction");
			a.abort();
		}

		logger.info("Is flight booked: "+flight.isBooked());
		logger.info("Is hotel booked: "+hotel.isBooked());
	}
}

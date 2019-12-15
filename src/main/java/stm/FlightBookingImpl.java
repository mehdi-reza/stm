package stm;

import org.jboss.logging.Logger;
import org.jboss.stm.annotations.NotState;
import org.jboss.stm.annotations.ReadLock;
import org.jboss.stm.annotations.State;
import org.jboss.stm.annotations.Transactional;
import org.jboss.stm.annotations.WriteLock;

@Transactional
public class FlightBookingImpl implements FlightBooking {

	@NotState
	Logger logger = Logger.getLogger(FlightBooking.class);

	@State
	private boolean booked;
	
	public FlightBookingImpl() {
		this.booked = false;
	}
	
	@Override
	@WriteLock
	public void bookFlight() {
		this.booked = true;
		logger.info("---- Flight is booked");
	}
	
	@Override
	@ReadLock
	public boolean getBooked() {
		return this.booked;
	}
}

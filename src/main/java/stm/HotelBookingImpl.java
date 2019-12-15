package stm;

import org.jboss.logging.Logger;
import org.jboss.stm.annotations.NotState;
import org.jboss.stm.annotations.ReadLock;
import org.jboss.stm.annotations.State;
import org.jboss.stm.annotations.Transactional;
import org.jboss.stm.annotations.WriteLock;

@Transactional
public class HotelBookingImpl implements HotelBooking {

	@NotState
	Logger logger = Logger.getLogger(HotelBooking.class);

	@State
	private boolean booked;
	
	public HotelBookingImpl() {
		this.booked = false;
	}
	
	@Override
	@WriteLock
	public void bookHotel(int rooms) {
		if(rooms > 1) throw new IllegalArgumentException("Only 1 room is available");
		this.booked = true;
		logger.info("---- Hotel is booked");
	}

	@Override
	@ReadLock
	public boolean getBooked() {
		return this.booked;
	}
}

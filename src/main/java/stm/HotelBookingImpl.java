package stm;

import java.io.IOException;

import org.jboss.logging.Logger;
import org.jboss.stm.annotations.NestedTopLevel;
import org.jboss.stm.annotations.ReadLock;
import org.jboss.stm.annotations.State;
import org.jboss.stm.annotations.Transactional;
import org.jboss.stm.annotations.WriteLock;

import com.arjuna.ats.arjuna.state.InputObjectState;
import com.arjuna.ats.arjuna.state.OutputObjectState;
import com.arjuna.ats.txoj.LockManager;

@Transactional
@NestedTopLevel
public class HotelBookingImpl implements HotelBooking {

	Logger logger = Logger.getLogger(HotelBooking.class);

	@State
	private boolean booked = false;
	
	@Override
	@WriteLock
	public void bookHotel(int rooms) {
		if(rooms > 1) throw new IllegalArgumentException("Only 1 room is available");
		this.booked = true;
		logger.info("Hotel is booked");
	}

	@Override
	@ReadLock
	public boolean isBooked() {
		return this.booked;
	}
	

	/*@Override
	public synchronized boolean save_state(OutputObjectState os, int ot) {
		logger.info("Save state");
		try {
			os.packBoolean(this.booked);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.save_state(os, ot);
	}
	
	@Override
	public synchronized boolean restore_state(InputObjectState os, int ot) {
		logger.info("Restore state");
		try {
			this.booked = os.unpackBoolean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.restore_state(os, ot);
	}*/

}

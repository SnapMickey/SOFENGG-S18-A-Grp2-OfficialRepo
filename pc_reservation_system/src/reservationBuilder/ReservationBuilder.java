package reservationBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import beans.*;
import services.SystemService;

public class ReservationBuilder {

	private static final int START_HOUR = 8;
	private static final int END_HOUR = 18;
	private static final double RESERVATION_INTERVAL = 1.00;
	private static final double RESERVATION_DURATION = 1.00;
	
	

	/**
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param building
	 * @param room
	 * @return
	 */
	public static ArrayList<PcReservation> generatePcReservations(Date date, Date startTime, Date endTime,
			String building, String room) {
		ArrayList<PcReservation> pReservations = new ArrayList<>();

		if (startTime != null && endTime != null) {
			ArrayList<Pc> availPc = SystemService.getAllFreePcs(date, startTime, endTime, building, room);

			for (Pc pc : availPc) {
				PcReservation possibleReservation = new PcReservation();
				possibleReservation.setPcID(pc.getPcID());
				possibleReservation.setDateTimeStart(startTime);
				possibleReservation.setDateTimeEnd(endTime);

				pReservations.add(possibleReservation);
			}
		}
		
		return pReservations;
	}

	/**
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param building
	 * @return
	 */
	public static ArrayList<LabReservation> generateLabReservations(Date date, Date startTime, Date endTime,
			String building) {
		ArrayList<LabReservation> lReservations = new ArrayList<>();

		if (startTime != null && endTime != null) {
			ArrayList<Lab> availLabs = SystemService.getAllFreeLabs(date, startTime, endTime, building);

			for (Lab lb : availLabs) {
				LabReservation possibleReservation = new LabReservation();
				possibleReservation.setLocationID(lb.getLocationID());
				possibleReservation.setDateTimeStart(startTime);
				possibleReservation.setDateTimeEnd(endTime);

				lReservations.add(possibleReservation);
			}
		} 

		return lReservations;
	}

}

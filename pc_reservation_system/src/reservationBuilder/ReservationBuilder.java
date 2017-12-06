package reservationBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import beans.*;
import services.SystemService;

public class ReservationBuilder {
	public static final double START_TIME = 8.00;
	public static final double END_TIME = 18.00;
	public static final double RESERVATON_DURATION = 1.00;

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
		} else {
			double time = ReservationBuilder.START_TIME;

			Date sTime, eTime;

			while (time < ReservationBuilder.END_TIME) {				
				try {
					sTime = convertToTime(date, time);
					eTime = convertToTime(date, time + ReservationBuilder.RESERVATON_DURATION);
					
					ArrayList<Pc> availPc = SystemService.getAllFreePcs(date, sTime, eTime, building, room);
					
					for (Pc pc : availPc) {
						PcReservation possibleReservation = new PcReservation();
						possibleReservation.setPcID(pc.getPcID());
						possibleReservation.setDateTimeStart(sTime);
						possibleReservation.setDateTimeEnd(eTime);

						pReservations.add(possibleReservation);
					}
				} catch (Exception e) {
				}

				time += ReservationBuilder.RESERVATON_DURATION;
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
		else {

			double time = ReservationBuilder.START_TIME;

			Date sTime, eTime;

			while (time < ReservationBuilder.END_TIME) {

				try {
					sTime = convertToTime(date, time);
					eTime = convertToTime(date, time + ReservationBuilder.RESERVATON_DURATION);

					ArrayList<Lab> availLabs = SystemService.getAllFreeLabs(date, sTime, eTime, building);

					for (Lab lb : availLabs) {
						LabReservation possibleReservation = new LabReservation();
						possibleReservation.setLocationID(lb.getLocationID());
						possibleReservation.setDateTimeStart(sTime);
						possibleReservation.setDateTimeEnd(eTime);

						lReservations.add(possibleReservation);
					}
				} catch (Exception e) {
				}

				time += ReservationBuilder.RESERVATON_DURATION;
			}

		}


		return lReservations;
	}

	private static Date convertToTime(Date date, double time) throws ParseException {

		String hours = "" + (int) time;
		String minutes = "" + (int) ((time* 100) % 100);

		if (hours.length() == 1)
			hours = "0" + hours;
		if (minutes.length() == 1)
			minutes = "0" + minutes;

		String stringDate = (date.getYear() + 1900) + "-";
		
		if(date.getMonth() / 10 != 1)
			stringDate += "0" + date.getMonth() + "-";
		else
			stringDate += date.getMonth() + "-";
		
		if(date.getDate() / 10 != 1)
			stringDate += "0" + date.getDate();
		else
			stringDate += date.getDate();
		
		String stringTime = hours + ":" + minutes + ":" + "00";

		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(stringDate + " " + stringTime);
	}

}

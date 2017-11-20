package reservationBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

import beans.*;
import services.SystemService;

public class ReservationBuilder {

	private static final int START_HOUR = 8;
	private static final int END_HOUR = 18;
	
	public static ArrayList<PcReservation> generatePcReservations(Date date, 
			Date startTime, 
			Date endTime,
			String building,
			String room)
	{
		ArrayList<PcReservation> pReservations = new ArrayList<>();

		if(startTime != null && endTime != null) {
			ArrayList<Pc> availPc = SystemService.getAllFreePcs(date, startTime, endTime, building, room);
			
			for(Pc pc : availPc) {
				PcReservation possibleReservation = new PcReservation();
				possibleReservation.setPcID(pc.getPcID());
				possibleReservation.setDateTimeStart(startTime);
				possibleReservation.setDateTimeEnd(endTime);
				
				pReservations.add(possibleReservation);
			}
		}
		else {
			for(int j = ReservationBuilder.START_HOUR; j < ReservationBuilder.END_HOUR; j++) {
				Date sTime = new Date();
				Date eTime = new Date();
				
				sTime.setDate(date.getDate());
				sTime.setMonth(date.getMonth());
				sTime.setYear(date.getYear());
				sTime.setHours(j);
				sTime.setMinutes(0);
				sTime.setSeconds(0);
				
				eTime.setDate(date.getDate());
				eTime.setMonth(date.getMonth());
				eTime.setYear(date.getYear());
				eTime.setHours(j + 1);
				eTime.setMinutes(0);
				eTime.setSeconds(0);
				
				ArrayList<Pc> availPc = SystemService.getAllFreePcs(date, sTime, eTime, building, room);
				
				for(Pc pc : availPc) {
					PcReservation possibleReservation = new PcReservation();
					possibleReservation.setPcID(pc.getPcID());
					possibleReservation.setDateTimeStart(sTime);
					possibleReservation.setDateTimeEnd(eTime);
					
					pReservations.add(possibleReservation);
				}

			}
		}
		
		return pReservations;
	}
	
	public static ArrayList<LabReservation> generateLabReservations(Date date, 
			Date startTime, 
			Date endTime,
			String building)
	{
		ArrayList<LabReservation> lReservations = new ArrayList<>();

		if(startTime != null && endTime != null) {
			ArrayList<Lab> availLabs = SystemService.getAllFreeLabs(date, startTime, endTime, building);
			
			for(Lab lb : availLabs) {
				LabReservation possibleReservation = new LabReservation();
				possibleReservation.setLocationID(lb.getLocationID());
				possibleReservation.setDateTimeStart(startTime);
				possibleReservation.setDateTimeEnd(endTime);
				
				lReservations.add(possibleReservation);
			}
		}
		else {
			for(int j = ReservationBuilder.START_HOUR; j < ReservationBuilder.END_HOUR; j++) {
				Date sTime = new Date();
				Date eTime = new Date();
				
				sTime.setDate(date.getDate());
				sTime.setMonth(date.getMonth());
				sTime.setYear(date.getYear());
				sTime.setHours(j);
				sTime.setMinutes(0);
				sTime.setSeconds(0);
				
				eTime.setDate(date.getDate());
				eTime.setMonth(date.getMonth());
				eTime.setYear(date.getYear());
				eTime.setHours(j + 1);
				eTime.setMinutes(0);
				eTime.setSeconds(0);
				
				ArrayList<Lab> availLabs = SystemService.getAllFreeLabs(date, sTime, eTime, building);
				
				for(Lab lb : availLabs) {
					LabReservation possibleReservation = new LabReservation();
					possibleReservation.setLocationID(lb.getLocationID());
					possibleReservation.setDateTimeStart(sTime);
					possibleReservation.setDateTimeEnd(eTime);
					
					lReservations.add(possibleReservation);
				}
				
				System.out.println("Hour: " + j);
			}
		}
		
		return lReservations;
	}
	
}

package reservationBuilder;

import java.text.DateFormat;
import java.text.ParseException;
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
				
				String start = "" + j + ":00:00";
				String end = "" + (j+1) + ":00:00";
				
				try {
					sTime = new SimpleDateFormat("hh:mm:ss").parse(start);
					eTime = new SimpleDateFormat("hh:mm:ss").parse(end);
				}catch(Exception e) {e.printStackTrace();}
				
				
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
				
				String start = "" + j + ":00:00";
				String end = "" + (j+1) + ":00:00";
				
				try {
					sTime = new SimpleDateFormat("hh:mm:ss").parse(start);
					eTime = new SimpleDateFormat("hh:mm:ss").parse(end);
				}catch(Exception e) {e.printStackTrace();}
				
				ArrayList<Lab> availLabs = SystemService.getAllFreeLabs(date, sTime, eTime, building);
				
				for(Lab lb : availLabs) {
					LabReservation possibleReservation = new LabReservation();
					possibleReservation.setLocationID(lb.getLocationID());
					possibleReservation.setDateTimeStart(sTime);
					possibleReservation.setDateTimeEnd(eTime);
					
					lReservations.add(possibleReservation);
				}

			}
		}
		
		return lReservations;
	}
	
}

package reservationBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

import beans.*;
import services.SystemService;

public class PcReservationBuilder {

	private static final int START_HOUR = 8;
	private static final int END_HOUR = 18;
	
	public static ArrayList<PcReservation> generateReservations(Date date, 
			Date startTime, 
			Date endTime,
			String building,
			String room)
	{
		ArrayList<PcReservation> pReservations = new ArrayList<>();

		if(startTime != null && endTime != null) {
			ArrayList<Pc> availPc = SystemService.getAllFreePcs(date, startTime, endTime, building, room);
			
			for(Pc pc : availPc) {
				pReservations.add(new PcReservation(-1,pc.getPcID(),date,startTime,endTime,null,null,false,false));
			}
		}
		else {
			for(int j = PcReservationBuilder.START_HOUR; j < PcReservationBuilder.END_HOUR; j++) {
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
					pReservations.add(new PcReservation(-1,pc.getPcID(),sTime,eTime, null, null,null,false,false));
				}
			}
		}
		
		return pReservations;
	}
	
}

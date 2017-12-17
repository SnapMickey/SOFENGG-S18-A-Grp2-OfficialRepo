package test;

import java.util.ArrayList;
import java.util.Date;

import beans.*;
import reservationBuilder.ReservationBuilder;
import services.SystemService;

public class Test {
	
	public static void main(String[] args){
		
		Date d = new Date();
		ArrayList<PcReservation> pr = ReservationBuilder.generatePcReservations(new Date(), null, null, null, null);
		
		for(PcReservation p : pr) {
			System.out.println(p.getDateTimeStart().getHours() + " |||| " + p.getDateTimeEnd().getHours());
		}
	}
}
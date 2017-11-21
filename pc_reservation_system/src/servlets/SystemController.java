package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import beans.Lab;
import beans.LabReservation;
import beans.Pc;
import beans.PcReservation;
import beans.User;
import reservationBuilder.ReservationBuilder;
import services.SystemService;

/**
 * Servlet implementation class SystemController
 */
@WebServlet (urlPatterns = {"/login", "/logout", "/adminpage", "/userpage", "/adminreservationpage", "/userreservationpage"
							, "/requestUserDetails", "/requestUserReservations", "/requestPcReservationList", "/requestUserDetailsByAdmin"
							,"/getAdminSchedules" , "/getLabReservations", "/getPcReservations", "/singleReserve", "/labReserve"
							,"/requestLabReservationList", "/adminhistorypage", "/userReserve"
							})
@MultipartConfig
public class SystemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SystemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String path = request.getServletPath();
		System.out.println(path);
		
		switch(path) {
			case "/login":
				doLogin(request, response);
				break;
			case "/logout":
				doLogout(request, response);
				break;
			case "/adminpage":
				doAdminPage(request, response);
				break;
			case "/userpage":
				doUserPage(request, response);
				break;
			case "/adminhistorypage":
				doAdminHistoryPage(request, response);
				break;
			case "/adminreservationpage":
				doAdminReservationPage(request, response);
				break;
			case "/userreservationpage":
				doUserReservationPage(request, response);
				break;
			case "/requestUserDetails":
				requestUserDetails(request, response);
				break;
			case "/requestUserReservations":
				requestUserReservations(request, response);
				break;
			case "/requestPcReservationList":
				requestPcReservationList(request,response);
				break;
			case "/requestLabReservationList":
				requestLabReservationList(request,response);
				break;
			case "/requestUserDetailsByAdmin":
				requestUserDetailsByAdmin(request,response);
				break;
			case "/getAdminSchedules":
				requestScheduleList(request, response);
				break;
			case "/getLabReservations":
				requestLabReservations(request, response);
				break;
			case "/getPcReservations":
				requestPcReservations(request, response);
				break;
			case "/userReserve":
				reservePCByUser(request,response);
				break;
			case "/singleReserve":
				reserveSinglePc(request,response);
				break;
			case "/labReserve":
				reserveLab(request,response);
				break;
			default: 
				break;
		}

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private void requestPcReservationList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JsonArray reservationList = new JsonArray();
		String building, room, startTime, endTime, date;
		building = request.getParameter("bldg");
		room = request.getParameter("room");
		date = request.getParameter("date");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		
		if(building .equals("default")){
			building = null;
		}
		
		if(room.equals("default")){
			room = null;
		}
		
		if(startTime.equals("default")){
			startTime = null;
			endTime = null;
		}
		
		System.out.println(startTime + " " + endTime );
		Date finalDate, finalSTime, finalETime;
		finalDate = null;				
		finalSTime = null;
		finalETime = null;
		
		try {
			if(startTime != null)
				finalDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date + " " + startTime);
			else
				finalDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date + " " + "08:00:00");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(building + " " + room + " " + date + " " + startTime + " " + endTime);
		if(startTime != null){
			try {
				finalSTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date + " " + startTime);
				finalETime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date + " " + endTime);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("date:" + finalDate + "sTime:" + finalSTime + "eTime:" + finalETime);
		
		ArrayList<PcReservation> tempReservationList = null;
		
		System.out.println("creating list for individual");
		tempReservationList = ReservationBuilder.generatePcReservations(finalDate, finalSTime, finalETime, building, room);
		
		if(tempReservationList.isEmpty())
			System.out.println("there are no pcs :---(");
		
		for(PcReservation pr : tempReservationList) {
			JsonObject json = new JsonObject();
			
			Lab lab = SystemService.getLabOfPc(pr.getPcID());
			
			String sTime, eTime;
			
			Date startT = pr.getDateTimeStart();
			Date endT = pr.getDateTimeEnd();
			
			if(startT.getHours() % 12 == 0) {
				sTime = "" + startT.getHours() + ":" + startT.getMinutes();
				
				if(sTime.charAt(sTime.length()-2) == ':') sTime += "0";
				
				sTime += " AM";
			}
			else {
				sTime = "" + startT.getHours() % 12 + ":" + startT.getMinutes();
			
				if(sTime.charAt(sTime.length()-2) == ':') sTime += "0";
				
				sTime += " PM";
			}
			if(endT.getHours() % 12 == 0) {
				eTime = "" + endT.getHours() + ":" + endT.getMinutes();
				
				if(eTime.charAt(eTime.length()-2) == ':') eTime += "0";
				
				eTime += " AM";
			}
			else {
				eTime = "" + endT.getHours() % 12 + ":" + endT.getMinutes();
			
				if(eTime.charAt(eTime.length()-2) == ':') eTime += "0";
				
				eTime += " PM";
			}
			
			json.addProperty("pcnum", "" + pr.getPcID());
			json.addProperty("location", lab.getBuilding());
			json.addProperty("room", lab.getName());
			json.addProperty("start", sTime);
			json.addProperty("end", eTime);
		
			reservationList.add(json);
		}
		
		response.setContentType("application/json");
		response.getWriter().write(reservationList.toString());
	}

	private void requestLabReservationList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		JsonArray reservationList = new JsonArray();
		String building, room, startTime, endTime, date;
		building = request.getParameter("bldg");
		room = request.getParameter("room");
		date = request.getParameter("date");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		
		if(building .equals("default")){
			building = null;
		}
		
		if(room.equals("default")){
			room = null;
		}
		
		if(startTime.equals("default")){
			startTime = null;
			endTime = null;
		}
		
		System.out.println(startTime + " " + endTime );
		Date finalDate, finalSTime, finalETime;
		finalDate = null;				
		finalSTime = null;
		finalETime = null;
		
		try {
			if(startTime != null)
				finalDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date + " " + startTime);
			else
				finalDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date + " " + "08:00:00");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(building + " " + room + " " + date + " " + startTime + " " + endTime);
		if(startTime != null){
			try {
				finalSTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date + " " + startTime);
				finalETime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date + " " + endTime);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("date:" + finalDate + "sTime:" + finalSTime + "eTime:" + finalETime);
		
		ArrayList<PcReservation> tempReservationList = null;
		
//		if(checkbox.equals("individual")){
//			System.out.println("creating list for individual");
//			tempReservationList = ReservationBuilder.generatePcReservations(finalDate, finalSTime, finalETime, building, room);
//		}else if(checkbox.equals("event")){
//			System.out.println("creating list for event");
//			//need to change stuff
//			
//		}
		
		//tempReservationList = ReservationBuilder.generateLabReservations(date, startTime, endTime, building);
		
		if(tempReservationList.isEmpty())
			System.out.println("there are no pcs :---(");
		
		for(PcReservation pr : tempReservationList) {
			JsonObject json = new JsonObject();
			
			Lab lab = SystemService.getLabOfPc(pr.getPcID());
			
			String sTime, eTime;
			
			Date startT = pr.getDateTimeStart();
			Date endT = pr.getDateTimeEnd();
			
			if(startT.getHours() % 12 == 0) {
				sTime = "" + startT.getHours() + ":" + startT.getMinutes();
				
				if(sTime.charAt(sTime.length()-2) == ':') sTime += "0";
				
				sTime += " AM";
			}
			else {
				sTime = "" + startT.getHours() % 12 + ":" + startT.getMinutes();
			
				if(sTime.charAt(sTime.length()-2) == ':') sTime += "0";
				
				sTime += " PM";
			}
			if(endT.getHours() % 12 == 0) {
				eTime = "" + endT.getHours() + ":" + endT.getMinutes();
				
				if(eTime.charAt(eTime.length()-2) == ':') eTime += "0";
				
				eTime += " AM";
			}
			else {
				eTime = "" + endT.getHours() % 12 + ":" + endT.getMinutes();
			
				if(eTime.charAt(eTime.length()-2) == ':') eTime += "0";
				
				eTime += " PM";
			}
			
			json.addProperty("pcnum", "" + pr.getPcID());
			json.addProperty("location", lab.getBuilding());
			json.addProperty("room", lab.getName());
			json.addProperty("start", startTime);
			json.addProperty("end", endTime);
		
			reservationList.add(json);
		}
		
		response.setContentType("application/json");
		response.getWriter().write(reservationList.toString());
	}
	
	private void requestScheduleList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Schedules for admin home page	
		JsonArray schedules = new JsonArray();
		String location = request.getParameter("location");
		
		if(location.equals("default")){
			location = null;
		}
		
		ArrayList<Lab> labs = SystemService.getAllLabs(location);
		
		Date curTime = new Date();
		Date endTime = new Date();
		
		curTime.setMinutes(0);
		endTime.setHours(curTime.getHours() + 1);
		endTime.setMinutes(0);
		
		for(Lab lb : labs) {
			int total = SystemService.getAllPcs(lb.getLocationID()).size();
			int free = SystemService.getAllFreePcs(curTime, curTime, endTime, lb.getBuilding(), lb.getName()).size();
			String status = "available";
			if(lb.isAvailable()) status = "unavailable";
			
			JsonObject json = new JsonObject();
			json.addProperty("location", lb.getName());
			json.addProperty("slots", free + "/" + total);
			json.addProperty("status", status);
			
			schedules.add(json);
		}
		
		response.setContentType("application/json");
		response.getWriter().write(schedules.toString());
	}
	
	private void requestLabReservations(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Lab Reservations for admin home page
		
		String location = request.getParameter("location");
		JsonArray reservations = new JsonArray();
		
		
		if(location.equals("default")){
			location = null;
		}
		
		ArrayList<LabReservation> reservationsLab = SystemService.getAllLabReservations(location);
		
		for(LabReservation lr : reservationsLab) {
			Lab lab = SystemService.getLab(lr.getLocationID());
			
			String startTime, endTime;
			
			Date startT = lr.getDateTimeStart();
			Date endT = lr.getDateTimeEnd();
			
			if(startT.getHours() % 12 == 0) {
				startTime = "" + startT.getHours() + ":" + startT.getMinutes();
				
				if(startTime.charAt(startTime.length()-2) == ':') startTime += "0";
				
				startTime += " AM";
			}
			else {
				startTime = "" + startT.getHours() % 12 + ":" + startT.getMinutes();
			
				if(startTime.charAt(startTime.length()-2) == ':') startTime += "0";
				
				startTime += " PM";
			}
			if(endT.getHours() % 12 == 0) {
				endTime = "" + endT.getHours() + ":" + endT.getMinutes();
				
				if(endTime.charAt(endTime.length()-2) == ':') endTime += "0";
				
				endTime += " AM";
			}
			else {
				endTime = "" + endT.getHours() % 12 + ":" + endT.getMinutes();
			
				if(endTime.charAt(endTime.length()-2) == ':') endTime += "0";
				
				endTime += " PM";
			}
			
			JsonObject json = new JsonObject();
			
			json.addProperty("id", lab.getLocationID());
			json.addProperty("location", lab.getName());
			json.addProperty("event", lr.getEventName());
			json.addProperty("date", "" + endT.getDate() + "/" + endT.getMonth()
								+ "/" + (endT.getYear() + 1900));
			json.addProperty("start", startTime);
			json.addProperty("end", endTime);
			
			reservations.add(json);
		}
		
		response.setContentType("application/json");
		response.getWriter().write(reservations.toString());
	}
	
	private void requestPcReservations(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Recent Reservations for admin home page
		JsonArray userReservations = new JsonArray();
		
		String location = request.getParameter("location");
		
		if(location.equals("default")){
			location = null;
		}
		
		ArrayList<PcReservation> singleReservationList = SystemService.getAllPcReservations(location);
		
		for(PcReservation pr : singleReservationList) {
			JsonObject json = new JsonObject();

			User user = SystemService.getUser(pr.getUserID());
			Lab lab = SystemService.getLabOfPc(pr.getPcID());
			
			String startTime, endTime;
			
			Date startT = pr.getDateTimeStart();
			Date endT = pr.getDateTimeEnd();
			
			if(startT.getHours() % 12 == 0) {
				startTime = "" + startT.getHours() + ":" + startT.getMinutes();
				
				if(startTime.charAt(startTime.length()-2) == ':') startTime += "0";
				
				startTime += " AM";
			}
			else {
				startTime = "" + startT.getHours() % 12 + ":" + startT.getMinutes();
			
				if(startTime.charAt(startTime.length()-2) == ':') startTime += "0";
				
				startTime += " PM";
			}
			if(endT.getHours() % 12 == 0) {
				endTime = "" + endT.getHours() + ":" + endT.getMinutes();
				
				if(endTime.charAt(endTime.length()-2) == ':') endTime += "0";
				
				endTime += " AM";
			}
			else {
				endTime = "" + endT.getHours() % 12 + ":" + endT.getMinutes();
			
				if(endTime.charAt(endTime.length()-2) == ':') endTime += "0";
				
				endTime += " PM";
			}
			
			json.addProperty("name", user.getName());
			json.addProperty("id", user.getUserID());
			json.addProperty("location", lab.getBuilding());
			json.addProperty("pcnum", "" + pr.getPcID());
			json.addProperty("date", "" + endT.getDate() + "/" + endT.getMonth()
								+ "/" + (endT.getYear() + 1900));
			json.addProperty("start", startTime);
			json.addProperty("end", endTime);
		
			userReservations.add(json);
		}
		
		response.setContentType("application/json");
		response.getWriter().write(userReservations.toString());
	}
	
	private void requestUserDetailsByAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JsonObject userDetails = new JsonObject();
		String idString = request.getParameter("id");
		int userId = Integer.parseInt(idString);
		
		User user = SystemService.getUser(userId);

		userDetails.addProperty("name", user.getName());
		userDetails.addProperty("id", "" + user.getUserID());
		userDetails.addProperty("college", "n/a");
		userDetails.addProperty("last_login", "n/a");
		
		response.setContentType("application/json");
		response.getWriter().write(userDetails.toString());
	}
	
	private void requestUserReservations(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JsonArray userReservations = new JsonArray();
		
		int userId = (Integer)request.getSession().getAttribute("id");
		User user = SystemService.getUser(userId);
		

		ArrayList<PcReservation> reservations = SystemService.getUserPcReservations(userId);
		for(PcReservation pr : reservations) {
			JsonObject json = new JsonObject();

			Lab lab = SystemService.getLabOfPc(pr.getPcID());
			
			String startTime, endTime;
			
			Date startT = pr.getDateTimeStart();
			Date endT = pr.getDateTimeEnd();
			
			if(startT.getHours() % 12 == 0) {
				startTime = "" + startT.getHours() + ":" + startT.getMinutes();
				
				if(startTime.charAt(startTime.length()-2) == ':') startTime += "0";
				
				startTime += " AM";
			}
			else {
				startTime = "" + startT.getHours() % 12 + ":" + startT.getMinutes();
			
				if(startTime.charAt(startTime.length()-2) == ':') startTime += "0";
				
				startTime += " PM";
			}
			if(endT.getHours() % 12 == 0) {
				endTime = "" + endT.getHours() + ":" + endT.getMinutes();
				
				if(endTime.charAt(endTime.length()-2) == ':') endTime += "0";
				
				endTime += " AM";
			}
			else {
				endTime = "" + endT.getHours() % 12 + ":" + endT.getMinutes();
			
				if(endTime.charAt(endTime.length()-2) == ':') endTime += "0";
				
				endTime += " PM";
			}
			
			json.addProperty("location", lab.getBuilding());
			json.addProperty("room", lab.getName());;
			json.addProperty("pcnum", "" + pr.getPcID());
			json.addProperty("date", "" + endT.getDate() + "/" + endT.getMonth()
								+ "/" + (endT.getYear() + 1900));
			json.addProperty("start", startTime);
			json.addProperty("end", endTime);
		
			userReservations.add(json);
		}
		
		response.setContentType("application/json");
		response.getWriter().write(userReservations.toString());
	}

	private void requestUserDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JsonObject userDetails = new JsonObject();
		
		int userId = (Integer)request.getSession().getAttribute("id");
		User user = SystemService.getUser(userId);

		userDetails.addProperty("name", user.getName());
		userDetails.addProperty("id", "" + user.getUserID());
		userDetails.addProperty("college", "n/a");
		userDetails.addProperty("last_login", "n/a");
		
		response.setContentType("application/json");
		response.getWriter().write(userDetails.toString());
	}

	private void reserveSinglePc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PcReservation newReservation = new PcReservation();
		
		String tPc, tBldg, tRoom, tStart, tEnd, tDate, tID;
		
		tPc = request.getParameter("pc");
		tBldg = request.getParameter("building");
		tRoom = request.getParameter("room");
		tStart = request.getParameter("sTime");
		tEnd = request.getParameter("eTime");
		tDate = request.getParameter("date");
		tID = request.getParameter("id");
		
		int pcNum, idNum;
		
		pcNum = Integer.parseInt(tPc);
		idNum = Integer.parseInt(tID);
		
		Date date, start, end, checkInTime, currDate;
		date = null;
		start = null;
		end = null;
		checkInTime = null;
		currDate = new Date();
		
		try {
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(tDate + " " + tStart);
			start = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(tDate + " " + tStart);
			end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(tDate + " " + tEnd);
			checkInTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("9999-09-09 09:09:09");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newReservation.setUserID(idNum);
		newReservation.setPcID(pcNum);
		newReservation.setCheckInTime(checkInTime);
		newReservation.setReserveTime(currDate);
		newReservation.setDateTimeStart(start);
		newReservation.setDateTimeEnd(end);
		newReservation.setAdminConfirmed(false);
		//please set the attributes in order to add properly
		
		SystemService.addPcReservation(newReservation);
	}
	
	private void reserveLab(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LabReservation newReservation = new LabReservation();
		
		//please set the attributes in order to add
		String tPc, tBldg, tRoom, tStart, tEnd, tDate, tID, tEventName;
		/*
		tPc = request.getParameter("pc");
		tBldg = request.getParameter("building");
		tRoom = request.getParameter("room");
		tStart = request.getParameter("sTime");
		tEnd = request.getParameter("eTime");
		tDate = request.getParameter("date");
		tID = request.getParameter("id");
		tEventName = request.getParameter("eventName");
		
		int pcNum, idNum;
		
		pcNum = Integer.parseInt(tPc);
		idNum = Integer.parseInt(tID);
		
		Date date, start, end, checkInTime, currDate;
		date = null;
		start = null;
		end = null;
		checkInTime = null;
		currDate = new Date();
		
		try {
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(tDate + " " + tStart);
			start = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(tDate + " " + tStart);
			end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(tDate + " " + tEnd);
			checkInTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("9999-09-09 09:09:09");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newReservation.setUserID(idNum);
		newReservation.setPcID(pcNum);
		newReservation.setCheckInTime(checkInTime);
		newReservation.setReserveTime(currDate);
		newReservation.setEventName(tEventName);
		newReservation.setDateTimeStart(start);
		newReservation.setDateTimeEnd(end);
		newReservation.setAdminConfirmed(false);
		*/
		
		// TODO Change this to LabReservation
		SystemService.addLabReservation(newReservation);
	}
	
	private void reservePCByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PcReservation newReservation = new PcReservation();
		
		String tPc, tRoom, tStart, tEnd, tDate, tID;
		
		tPc = request.getParameter("pc");
		tRoom = request.getParameter("room");
		tDate = request.getParameter("date");
		tStart = request.getParameter("sTime");
		tEnd = request.getParameter("eTime");
		
		int userId = (Integer)request.getSession().getAttribute("id");
		int pcNum;
		
		pcNum = Integer.parseInt(tPc);
		
		Date date, start, end, checkInTime, currDate;
		date = null;
		start = null;
		end = null;
		checkInTime = null;
		currDate = new Date();
		
		try {
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(tDate + " " + tStart);
			start = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(tDate + " " + tStart);
			end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(tDate + " " + tEnd);
			checkInTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("9999-09-09 09:09:09");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newReservation.setUserID(userId);
		newReservation.setPcID(pcNum);
		newReservation.setCheckInTime(checkInTime);
		newReservation.setReserveTime(currDate);
		newReservation.setDateTimeStart(start);
		newReservation.setDateTimeEnd(end);
		newReservation.setAdminConfirmed(false);
		//please set the attributes in order to add properly
		SystemService.addPcReservation(newReservation);
		
	}
	
	private void doUserReservationPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("user_reservation_page.html").forward(request, response);
	}

	private void doAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("admin_front_page.html").forward(request, response);
		//redirect to login page
	}
	
	private void doAdminReservationPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("admin_reservation_page.html").forward(request, response);
	}
	
	private void doUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("user_front_page.html").forward(request, response);
	}
	
	private void doAdminHistoryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("admin_history.html").forward(request, response);
	}
	
	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		request.getSession().invalidate();
		System.out.println("entered");
		response.sendRedirect("index.html");
		}
		catch(Exception e) {e.printStackTrace();}
		//redirect to login page
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NullPointerException{
		int id = -1;
		String password = "";
		String link = "index.html?error=";
		String errors = "";
		boolean success = false;
		
		try{
			String str = request.getParameter("id");
			if(str == null || str == ""){
				errors+="BU";
			}else{
				id = Integer.parseInt(str);
				if(id<=8000000 || id >= 30000000 && id != -1){
					errors+="WU";
					//reset id to -1 so DNE doesnt come out
					id = -1;
				}
			}
		}catch(Exception e){
			
			//number format exception,i.e. input wasnt an int
			errors+="WU";
		}
		password = request.getParameter("password");
	
		//Changed id -> user just to prevent unwanted error (Edited by: Jerome)
		if(password == null || password == ""){
			errors+="BP";
		}
		
		//check first if id isnt blank, if it isnt blank, do stuff
		if(id != -1){
			User user = SystemService.getUser(id);
			
			//check first if user is null / doesnt exist before comparing password
			if(user == null ){
				errors+="DNE";
			}
			else if(!user.getPassword().equals(password)){
				errors+="WP";
			}
			else if(user != null && user.getPassword().equals(password)){//user n pass is correct
				System.out.println("USER VALID");
				request.getSession().setAttribute("id", id);
				request.getSession().setAttribute("position", user.getPosition());
				
				if(user.getPosition().equals("admin")) {
					//changed admin_front_page.html -> adminpage (note: check doGet method) (Edited by: Jerome)
					link = "adminpage";
				}
				else {
					link = "userpage";
				}
				success = true;
			}
		}
		
		
		//if login failed, append the error codes to the link
		if(!success){
			link+=errors;
		}
		//request.getRequestDispatcher(link).forward(request,response);
		response.sendRedirect(link);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

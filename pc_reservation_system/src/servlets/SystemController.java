package servlets;

import java.io.IOException;
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
import beans.Pc;
import beans.PcReservation;
import beans.User;
import reservationBuilder.PcReservationBuilder;
import services.SystemService;

/**
 * Servlet implementation class SystemController
 */
@WebServlet (urlPatterns = {"/login", "/logout", "/adminpage", "/userpage", "/adminreservationpage", "/userreservationpage"
							, "/requestUserDetails", "/requestUserReservations", "/requestAdminReservationList"
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
			case "/requestAdminReservationList":
				requestAdminReservationList(request,response);
				break;
			default: 
				break;
		}

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private void requestAdminReservationList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
				JsonArray reservationList = new JsonArray();
				String bldg, room, time, date;
				bldg = request.getParameter("bldg");
				room = request.getParameter("room");
				date = request.getParameter("date");
				time = request.getParameter("time");
				
				System.out.println(bldg + " " + room + " " + date + " " + time);
			
				
				response.setContentType("application/json");
				response.getWriter().write(reservationList.toString());
	}
	
	private void requestUserReservations(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JsonArray userReservations = new JsonArray();
		/*
		//instead of get from session -> get from HTML TEXTBOX
		int userId = (Integer)request.getSession().getAttribute("id");
		User user = SystemService.getUser(userId);
		
		//get all data from html dropdowns
		
		ArrayList<PcReservation> tempReservationList = PcReservationBuilder.generateReservations();
		for(PcReservation pr : tempReservationList) {
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
		*/
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

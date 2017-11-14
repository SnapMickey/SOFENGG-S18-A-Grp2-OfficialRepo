package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import beans.User;
import services.SystemService;

/**
 * Servlet implementation class SystemController
 */
@WebServlet(urlPatterns = {"/login", "/logout", "/adminpage", "/userpage", "/adminreservationpage"})
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
		}

		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
		//redirect to login page
	}
	
	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		request.getRequestDispatcher("index.html").forward(request, response);
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

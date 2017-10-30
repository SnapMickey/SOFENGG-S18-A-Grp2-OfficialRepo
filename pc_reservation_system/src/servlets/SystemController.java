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
@WebServlet(urlPatterns = {"/login", "/logout", "/adminpage", "/userpage"})
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
				Login(request, response);
				break;
			case "/logout":
				Logout(request, response);
				break;
			case "/adminpage":
				AdminPage(request, response);
				break;
			case "/userpage":
				UserPage(request, response);
				break;
		}

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void AdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("admin_front_page.html").forward(request, response);
		//redirect to login page
	}
	
	private void UserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("user_front_page.html").forward(request, response);
		//redirect to login page
	}
	
	private void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		request.getRequestDispatcher("index.html").forward(request, response);
		//redirect to login page
	}

	private void Login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NullPointerException{
		int id = -1;
		String password = "";
		String link = "index.html?error=";
		String errors = "";
		boolean success = false;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
			
			password = request.getParameter("password");
		
			User user = SystemService.getUser(id);
		
			if(user == null || id == -1) {
				errors+="BU";
			}
			if(password == null || password == ""){
				errors+="BP";
			}
			if(!user.getPassword().equals(password)){
				errors+="WP";
			}
			if(user == null){
				errors+="WU";
			}
			if(user != null && user.getPassword().equals(password)){//user n pass is correct
				System.out.println("USER VALID");
				request.getSession().setAttribute("id", id);
				request.getSession().setAttribute("position", user.getPosition());
				
				if(user.getPosition().equals("admin")) {
					link = "adminpage";
				}
				else {
					link = "userpage";
				}
				success = true;
			}
		
		}
		catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
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

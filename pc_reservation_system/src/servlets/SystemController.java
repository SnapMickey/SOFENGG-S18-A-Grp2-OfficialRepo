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
@WebServlet(urlPatterns = {"/login", "/logout"})
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
		}

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		request.getRequestDispatcher("index.html").forward(request, response);
		//redirect to login page
	}

	private void Login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NullPointerException{
		// TODO Auto-generated method stub
		
		System.out.print("HEK");
		
		int id = -1;
		String password = "";
		String link = "index.html?error=yes";
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
			
			password = request.getParameter("password");
			
			System.out.println("ID: " + id);
			System.out.println("Password: " + password);
		
		User user = SystemService.getUser(id);
		
		if(user == null || !user.getPassword().equals(password)) {
			System.out.println("USER INVALID");
		}
		else {
			System.out.println("USER VALID");
			request.getSession().setAttribute("id", id);
			
			if(user.getPosition().equals("admin")) {
				link = "admin_front_page.html";
			}
			else {
				link = "user_front_page.html";
			}
		}
		
		}
		catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		System.out.println("END");
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

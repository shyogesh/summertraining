package com.bit.hostel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.hostel.common.UserRole;
import com.bit.hostel.common.Utils;
import com.bit.hostel.dao.StaffDaoImpl;
import com.bit.hostel.dao.StudentDaoImpl;
import com.bit.hostel.dao.UserDaoImpl;
import com.bit.hostel.model.Staff;
import com.bit.hostel.model.Student;
import com.bit.hostel.model.User;



/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private UserDaoImpl userDao;
	
	private StudentDaoImpl studentDao;
	
	private StaffDaoImpl staffDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        userDao = new UserDaoImpl();
        studentDao = new StudentDaoImpl();
        staffDao= new StaffDaoImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null || action.length() == 0){
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		} else if(action.equals("login")){
			request.getRequestDispatcher("./login.jsp").forward(request, response);
		} else if(action.equals("dashboard")){
			request.setAttribute("page", "main.jsp");
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		} else if(action.equals("admin")){
			request.getRequestDispatcher("./admin").forward(request, response);
		} else if(action.equals("user")){
			request.getRequestDispatcher("./userServlet").forward(request, response);
		} else if(action.equals("student")){
			response.sendRedirect("./studentServlet");
		} else if(action.equals("vendor")){
			request.getRequestDispatcher("./vendorServlet").forward(request, response);
		} else if(action.equals("staff")){
			response.sendRedirect("./staffServlet");
		} else if(action.equals("signup")){
			response.sendRedirect("./signup.jsp");
		} else if(action.equals("forgotpassword")){
			response.sendRedirect("./forgotpassword.jsp");
		}
		
		String basePage = request.getSession().getAttribute("user")==null?"./index.jsp":"./admin.jsp";
		if(action == null || action.length() ==0) {
			List<User> list = userDao.get();
			request.setAttribute("list", list);
			request.setAttribute("page", "user.jsp");
			
			request.getRequestDispatcher(basePage).forward(request, response);
		}  else if(action.equalsIgnoreCase("delete")){
			List<User> list = userDao.get();
			request.setAttribute("list", list);
			String UserId = request.getParameter("id");
			if(UserId == null || UserId.length() == 0) {
				request.setAttribute("errormsg", "either user is not exist or some issue with it.");
				request.setAttribute("page", "user.jsp");
			} else {
				Integer user = userDao.delete(Integer.parseInt(UserId));
				request.setAttribute("errormsg", "");
				request.setAttribute("user", user);
				request.setAttribute("page", "user.jsp");
			}
			request.getRequestDispatcher(basePage).forward(request, response);
		} 
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action == null || action.length() == 0){
			request.getRequestDispatcher("./login.jsp").forward(request, response);
		} else if(action.equals("signup")){
			String id = request.getParameter("id");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String email=request.getParameter("email");
			String role=request.getParameter("role");
			String stream=request.getParameter("stream");
			String lastlogin=request.getParameter("lastlogin");
			User user = new User();
			if(id != null && id.length() > 0){
				user.setUserId(Integer.parseInt(id));
			}
		
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			if(role.equalsIgnoreCase(UserRole.STUDENT_ROLE)){				
				user.setRole(UserRole.STUDENT_ROLE);
				user.setStatus(1);
			} else if(role.equalsIgnoreCase(UserRole.STAFF_ROLE)){				
				user.setRole(UserRole.STAFF_ROLE);
				user.setStatus(1); //TODO fix later.
			} else {
				user.setRole(UserRole.STAFF_ROLE);
				user.setStatus(0);
			}
			user.setLastLogin(Utils.stringToDate(request.getParameter(lastlogin)));
			
			
			userDao.saveOrUpdate(user);
			User savedUser = userDao.getUser(username, password);
			if(role.equalsIgnoreCase(UserRole.STUDENT_ROLE)){
				Student student = new Student();
				student.setSID(savedUser.getUserId());
				student.setFirstName(username);
				student.setsBranch(stream);
				studentDao.saveOrUpdate(student);
				request.setAttribute("errormsg", "");
			} else {
				Staff staff = new Staff();
				staff.setStaffId(savedUser.getUserId());
				staff.setStaffname(username);
				staff.setDepartment(stream);
				staffDao.saveOrUpdate(staff);
				request.setAttribute("errormsg", "Please ask to admin to enable you userid.");
			}
			request.getRequestDispatcher("./login.jsp").forward(request, response);
		} 
		
	}

}

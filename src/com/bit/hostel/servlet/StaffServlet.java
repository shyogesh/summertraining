package com.bit.hostel.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.hostel.common.Utils;
import com.bit.hostel.dao.StaffDaoImpl;
import com.bit.hostel.model.Leave;
import com.bit.hostel.model.Staff;
import com.bit.hostel.model.User;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/staffServlet")
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StaffDaoImpl staffDao;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StaffServlet() {
		super();
		staffDao = new StaffDaoImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String basePage = request.getSession().getAttribute("user")==null?"./index.jsp":"./admin.jsp";
		if(action == null || action.length() ==0) {
			List<Staff> list = staffDao.get();
			request.setAttribute("list", list);
			request.setAttribute("page", "staff.jsp");
			
			request.getRequestDispatcher(basePage).forward(request, response);
		} else if(action.equalsIgnoreCase("edit")){
			String staffId = request.getParameter("id");
			if(staffId == null || staffId.length() == 0) {
				List<Staff> list = staffDao.get();
				request.setAttribute("list", list);
				request.setAttribute("errormsg", "either staff is not exist or some issue with it.");
				request.setAttribute("page", "staff.jsp");
			} else {
				Staff staff = staffDao.get(Integer.parseInt(staffId));
				request.setAttribute("errormsg", "");
				request.setAttribute("staff", staff);
				request.setAttribute("page", "staff_edit.jsp");
			}
			request.getRequestDispatcher("./admin.jsp").forward(request, response);
		} else if(action.equalsIgnoreCase("delete")){
			List<Staff> list = staffDao.get();
			request.setAttribute("list", list);
			String staffId = request.getParameter("id");
			if(staffId == null || staffId.length() == 0) {
				request.setAttribute("errormsg", "either staff is not exist or some issue with it.");
				request.setAttribute("page", "staff.jsp");
			} else {
				Integer staff = staffDao.delete(Integer.parseInt(staffId));
				request.setAttribute("errormsg", "");
				request.setAttribute("staff", staff);
				request.setAttribute("page", "staff.jsp");
			}
			request.getRequestDispatcher(basePage).forward(request, response);
		} else if(action.equalsIgnoreCase("save")){
			List<Staff> list = staffDao.get();
			request.setAttribute("list", list);
			request.setAttribute("page", "staff.jsp");
			request.getRequestDispatcher(basePage).forward(request, response);
		} else if(action.equalsIgnoreCase("add")){	
			request.setAttribute("page", "staff_edit.jsp");
			request.getRequestDispatcher(basePage).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String staffid=request.getParameter("staffid");	
		String staffname = request.getParameter("staffname");
		String contact = request.getParameter("contact");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String department = request.getParameter("department");
		String stafftype = request.getParameter("stafftype");
		String dateofbirth = request.getParameter("dateofbirth");
		String updatedon = request.getParameter("updatedon");
    
		
		Staff staff = new Staff();
		if(staffid != null && staffid.length() > 0){
			staff.setStaffId(Integer.parseInt(staffid));
		}
		staff.setStaffname(staffname);
		staff.setContact(contact);
		staff.setEmail(email);
		staff.setGender(gender);
		staff.setDepartment(department);
		staff.setStafftype(stafftype);
		staff.setDateofbirth(Utils.stringToDate(request.getParameter(dateofbirth)));
		staff.setUpdatedOn(Utils.stringToDate(request.getParameter(updatedon)));
		User user = (User) request.getSession().getAttribute("user");
		staff.setUser(user);
	
		staffDao.saveOrUpdate(staff);

		List<Staff> list = staffDao.get();
		request.setAttribute("list", list);		
		response.sendRedirect("./staffServlet");
	}



}

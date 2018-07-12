package com.bit.hostel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.hostel.dao.VendorDaoImpl;
import com.bit.hostel.model.Staff;
import com.bit.hostel.model.Vendor;

/**
 * Servlet implementation class VendorServlet
 */
@WebServlet("/vendorServlet")
public class VendorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     */
	private VendorDaoImpl vendorDao;
    public VendorServlet() {
        super();
     vendorDao = new VendorDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String vendorId = request.getParameter("VendorId");
		if(action == null || action.length() == 0) {
		
		List<Vendor> list = new VendorDaoImpl().get();
		request.setAttribute("list", list);
		request.setAttribute("page", "vendor.jsp");
		request.getRequestDispatcher("./admin.jsp").forward(request, response);
	}
		 else if(action.equalsIgnoreCase("edit")){
				String Vendorid = request.getParameter("Vendorid");
				if(Vendorid == null || Vendorid.length() == 0) {
					List<Vendor> list = vendorDao.get();
					request.setAttribute("list", list);
					request.setAttribute("errormsg", "either vendor is not exist or some issue with it.");
					request.setAttribute("page", "vendor.jsp");
				} else {
					Vendor  vendor = vendorDao.get(Integer.parseInt(Vendorid));
					request.setAttribute("errormsg", "");
					request.setAttribute("vendor", vendor);
					request.setAttribute("page", "vendor_edit.jsp");
				}
				request.getRequestDispatcher("./admin.jsp").forward(request, response);
			} else if(action.equalsIgnoreCase("delete")){
	    		Integer student = vendorDao.delete(Integer.parseInt(vendorId));
	    		request.setAttribute("student", student);
	    		List<Vendor> list = vendorDao.get();
	    		request.setAttribute("list", list);
	    		request.setAttribute("page", "vendor.jsp");
	    		request.getRequestDispatcher("./admin.jsp").forward(request, response);
	    		} 
			else if(action.equalsIgnoreCase("save")){
				List<Vendor> list = vendorDao.get();
				request.setAttribute("list", list);
				request.setAttribute("page", "vendor.jsp");

			}
			else if(action.equals("add")){
				request.setAttribute("page", "vendor_edit.jsp");
				request.getRequestDispatcher("./admin.jsp").forward(request, response);
			}
			
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Vendorid= request.getParameter("Vendorid");
		String Vendorname = request.getParameter("Vendorname");
		String purpose = request.getParameter("Purpose");

		
		Vendor vendor = new Vendor();
		if(Vendorid != null && !Vendorid.equalsIgnoreCase("-1")){
			vendor.setVendorid(Integer.parseInt(Vendorid));
		}
		vendor.setVendorname(Vendorname);
		vendor.setPurpose(purpose);
		
		vendorDao.saveOrUpdate(vendor);
		List<Vendor> list = vendorDao.get();
		request.setAttribute("list", list);		
		response.sendRedirect("./vendorServlet");

		
		
	}

}

<%@page import="com.bit.hostel.model.Staff"%>
<%=request.getAttribute("resultmsg") == null ? "-" : request.getAttribute("resultmsg")%>

<%
	Staff staff;
	if (request.getAttribute("staff") != null) {
		staff = (Staff) request.getAttribute("staff");
	} else {
		staff = new Staff();
	}
%>

<div id="page-wrapper">
	<div class="panel-body">
		<div class="row">


			<h1>Edit Details</h1>

			<form action="staffServlet" method="post">
				<div class="well">

					<div class="row">

						<input type="hidden" name="staffid"
							value="<%=staff.getStaffId() == null ? "" : staff.getStaffId()%>">

						<div class="form-group col-lg-4 ">
							<label for="staffname">Staff Name</label> <input type="text"
								name="staffname" class="form-control"
								placeholder="Enter Staff Name"
								value="<%=staff.getStaffname() == null ? "" : staff.getStaffname()%>"
								required>
						</div>

						<div class="form-group col-lg-4">
							<label for="contact">Contact</label> <input type="text"
								name="contact" class="form-control"
								placeholder="Enter Contact No"
								value="<%=staff.getContact() == null ? "" : staff.getContact()%>">
						</div>

						<div class="form-group col-lg-4">
							<label for="email">Email Id</label> <input type="email"
								name="email" class="form-control" placeholder="Enter Email Id"
								value="<%=staff.getEmail() == null ? "" : staff.getEmail()%>">
						</div>




						<div class="form-group col-lg-4">
							<label for="gender">Gender</label>
							<div class="form-control">
								<input type="radio" name="gender" checked="checked" value="Male"
									value="<%=staff.getGender() == null ? "" : staff.getGender()%>">Male
								<input type="radio" name="gender" value="Female"
									value="<%=staff.getGender() == null ? "" : staff.getGender()%>">Female
							</div>
						</div>


						<div class="form-group col-lg-4">
							<label for="department">Department</label> <select
								name="department" class="form-control">
								<option>CSE</option>
								<option>EE</option>
								<option>ECE</option>
								<option>CE</option>
								<option>ME</option>
							</select>
						</div>



						<div class="form-group col-lg-4">
							<label for="stafftype">Staff Type</label> <select
								name="stafftype" class="form-control">
								<option>Admin</option>
								<option>HOD</option>
								<option>Staff</option>
								<option>gard</option>
							</select>


						</div>

						<div class="form-group col-lg-4">
							<label for="dateofbirth">Date Of Birth</label> <input type="date"
								name="dateofbirth" class="form-control" placeholder="Enter DOB"
								value="<%=staff.getDateofbirth() == null ? "" : staff.getDateofbirth()%>">
						</div>

					</div>


					<div class="row">
						<div class="form-group col-lg-2">


							<%
								if (request.getAttribute("action") != null && request.getAttribute("action").equals("edit")) {
							%>
							<button type="submit" class="form-control btn btn-success"
								value="edit">Edit</button>
							<%
								} else {
							%>
							<button type="submit" class="form-control btn btn-success"
								value="save">Save</button>
							<%
								}
							%>
						</div>


						<div class="form-group col-lg-2">
							<button type="reset" class="form-control btn btn-success"
								value="reset">Reset</button>
						</div>

					</div>

				</div>



			</form>

		</div>
	</div>

</div>
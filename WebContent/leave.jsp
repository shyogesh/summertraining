<jsp:include page="adminheader.jsp"></jsp:include>
<%@page import="com.bit.hostel.model.Leave"%>


<%=request.getAttribute("resultmsg") == null ? "-" : request.getAttribute("resultmsg")%>

<%
	Leave leave;
	if (request.getAttribute("leave") != null) {
		leave = (Leave) request.getAttribute("leave");
	} else {
		leave = new Leave();
	}
%>



<div id="page-wrapper">
<div class="panel-body">
<div class="row">

		<h1>Apply for leave</h1>
		<form action="./admin?action=leave" method="post">
		
            <div class="well ">
			<div class="row">
			
		                        <input type="hidden" name="id"
							value="<%=leave.getId() == null ? "" : leave.getId()%>">

					<div class="form-group col-lg-4">
						<label for="purpose">Purpose</label> <select name="purpose"
							class="form-control">
							<option>Official</option>
							<option>Personal</option>
							<option>Other</option>
						</select>
					</div>
					<div class="form-group col-lg-4 ">
						<label for="date">Leaving Time:</label> <input type="date"
							name="intime" class="form-control" placeholder="Enter date"
							required
							value="<%=leave.getIntime() == null ? "" : leave.getIntime()%>">
					</div>
					<div class="form-group col-lg-4 ">
						<label for="date"> Coming time:</label> <input type="date"
							name="outtime" class="form-control" placeholder="Enter date"
							required value="<%=leave.getOutTime() == null ? "" : leave.getOutTime()%>">
					</div>

					<div class="form-group col-lg-12 ">
						<label for="remark">Remark:</label> <input type="text"
							name="remark" class="form-control" placeholder="Enter Remark"
							required value="<%=leave.getRemark() == null ? "" : leave.getRemark()%>">
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
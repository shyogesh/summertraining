<%@page import="com.bit.hostel.model.Vendor"%>
<%=request.getAttribute("resultmsg")==null?"-":request.getAttribute("resultmsg") %>

<%
Vendor vendor;
if(request.getAttribute("staff") != null){
  	vendor = (Vendor)request.getAttribute("vendor");
} else {
	vendor=new Vendor();
}
%>

<div id="page-wrapper">
	<div class="panel-body">
		<div class="row">


			<h1>Edit Details</h1>

			<form action="vendorServlet" method="post">
				<div class="well">

					<div class="row">

						<input type="hidden" name="Vendorid" value="<%=vendor.getVendorid()==null?-1:vendor.getVendorid() %>">

						<div class="form-group col-lg-6">
							<label for="Vendorname">Vendor Name</label> <input type="text"
								name="Vendorname" class="form-control"
								placeholder="Enter Vendor Name" value="<%=vendor.getVendorname()==null?"":vendor.getVendorname() %>" required>
						</div>

						

						<div class="form-group col-lg-6">
							<label for="Purpose">purpose</label> <input type="text"
								name="Purpose" class="form-control" placeholder="Enter purpose" value="<%=vendor.getPurpose()==null?"":vendor.getPurpose() %>">
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
<%@page import="com.bit.hostel.model.Student"%>
<%@page import="java.util.*"%>
<%=request.getAttribute("resultmsg")==null?"-":request.getAttribute("resultmsg") %>
<div id="page-wrapper">


 

			<%
			Student student;
			if(request.getAttribute("student") != null){
		  	student = (Student)request.getAttribute("student");
			} else {
			student=new Student();
			}    
			%>

    
<center>
 <div style="padding-top: 10px"></div>		
	<div class="row">     
<div class="col-lg-12 well">
    <h1>Student Registration Form</h1><hr>
    <form class="form-horizontal" action="studentServlet" method="post">

                  <input type="hidden" name="SID" class="form-control" value="<%=student.getSID() %>">
                  <input type="hidden" name="SEnum" class="form-control" value="<%=student.getsEnum() %>">
                  <input type="hidden" name="UpdatedOn" class="form-control" value="<%=student.getUpdatedOn() %>">
                  <input type="hidden" name="UpdatedBy" class="form-control" value="<%=student.getUpdatedBy() %>">

    

    <div class="row form-group">
      <label class="control-label col-md-2">Student Info:</label>
      <div class="col-md-5"><input type="text" name="FirstName" class="form-control" value="<%=student.getFirstName()==null?"":student.getFirstName() %>" placeholder="Enter First Name"></div>
      <div class="col-md-5"><input type="text" name="LastName" class="form-control" value="<%=student.getLastName()==null?"":student.getLastName() %>" placeholder="Enter last Name"></div>
    </div>
    <div class="row form-group">
      <div class="col-md-2"></div>
      <div class="col-md-5"><input type="text" name="SMobile" class="form-control" value="<%=student.getsMobile()==null?"":student.getsMobile() %>" placeholder="Enter Student Contact No."></div>
      <div class="col-md-5"><input type="text" name="SEmail" class="form-control" value="<%=student.getsEmail()==null?"":student.getsEmail() %>" placeholder="Enter your email"></div>
    </div>
     <div class="row form-group">
      <div class="col-md-2"></div>
      <div class="col-md-5"><input type="date" name="SDOB" class="form-control" value="<%=student.getsDOB()==null?"":student.getsDOB() %>" placeholder="Enter your DOB"></div>
    </div>
    <div class="row form-group">
      <label class="control-label col-md-2">Gender:</label>
      <div class="col-md-2"><input type="radio" name="SGender" value="MALE" checked="checked">Male</div>
      <div class="col-md-3"><input type="radio" name="SGender" value="FEMALE">Female</div>
    </div><hr>

    <!--==================   GUARDIANS INFO.    ======================-->

    <div class="row form-group">
      <label class="control-label col-md-2">Guardians Info :</label>
      <div class="col-md-5"><input type="text" name="FatherName" class="form-control" value="<%=student.getFatherName()==null?"":student.getFatherName() %>" placeholder="Enter Father's Name"></div>
      <div class="col-md-5"><input type="text" name="FatherContact" class="form-control" value="<%=student.getFatherContact()==null?"":student.getFatherContact() %>" placeholder="Enter Father's Contact No."></div>
    </div>
    <div class="row form-group">
      <div class="col-md-2"></div>
      <div class="col-md-5"><input type="text" name="MotherName" class="form-control" value="<%=student.getMotherName()==null?"":student.getMotherName() %>" placeholder="Enter Mother's Name"></div>
      <div class="col-md-5"><input type="text" name="MotherConntact" class="form-control" value="<%=student.getMotherContact()==null?"":student.getMotherContact() %>" placeholder="Enter Mother's Contact No."></div>
    </div><hr>

    <!--==================    ADDRESS    ======================-->

     <div class="row form-group">
      <label class="control-label col-md-2">Address:</label>
      <div class="col-md-5"><input type="text" name="SAddressP" class="form-control" value="<%=student.getsAddressP()==null?"":student.getsAddressP() %>" placeholder="Enter Permanent Address"></div>
      <div class="col-md-5"><input type="text" name="SAddressC" class="form-control" value="<%=student.getsAddressC()==null?"":student.getsAddressC() %>" placeholder="Enter Current Address"></div>
    </div>
    <div class="row form-group">
      <div class="col-md-2"></div>
      <div class="col-md-5"><input type="text" name="SCity" class="form-control" value="<%=student.getsCity()==null?"":student.getsCity() %>" placeholder="Enter City"></div>
      <div class="col-md-5">
      		<select name=sState value="<%=student.getsState() %>" class="form-control">
<option selected disabled>Select State</option>
<option value="Andhra Pradesh">Andhra Pradesh</option>
<option value="Arunachal Pradesh">Arunachal Pradesh</option>
<option value="Assam">Assam</option>
<option value="Bihar">Bihar</option>
<option value="Chandigarh">Chandigarh</option>
<option value="Chhattisgarh">Chhattisgarh</option>
<option value="Delhi">Delhi</option>
<option value="Goa">Goa</option>
<option value="Gujarat">Gujarat</option>
<option value="Haryana">Haryana</option>
<option value="Himachal Pradesh">Himachal Pradesh</option>
<option value="Jammu and Kashmir">Jammu and Kashmir</option>
<option value="Jharkhand">Jharkhand</option>
<option value="Karnataka">Karnataka</option>
<option value="Kerala">Kerala</option>
<option value="Madhya Pradesh">Madhya Pradesh</option>
<option value="Maharashtra">Maharashtra</option>
<option value="Manipur">Manipur</option>
<option value="Meghalaya">Meghalaya</option>
<option value="Mizoram">Mizoram</option>
<option value="Nagaland">Nagaland</option>
<option value="Orissa">Orissa</option>
<option value="Pondicherry">Pondicherry</option>
<option value="Punjab">Punjab</option>
<option value="Rajasthan">Rajasthan</option>
<option value="Sikkim">Sikkim</option>
<option value="Tamil Nadu">Tamil Nadu</option>
<option value="Tripura">Tripura</option>
<option value="Uttaranchal">Uttaranchal</option>
<option value="Uttar Pradesh">Uttar Pradesh</option>
<option value="West Bengal">West Bengal</option>
</select>
      </div>
    </div><hr>

 

     <div class="row form-group">
      <label class="control-label col-md-2">Education Info:</label>
      <div class="col-md-5"><input type="text" name="S10Th" class="form-control" value="<%=student.getS10Th()==null?"":student.getS10Th() %>" placeholder="Enter 10th Percent"></div>
      <div class="col-md-5"><input type="text" name="S12Th" class="form-control" value="<%=student.getS12Th()==null?"":student.getS12Th() %>" placeholder="Enter 12th Percent"></div>
    </div>
    <div class="row form-group">
      <div class="col-md-2"></div>
      <div class="col-md-5"><input type="text" name="SDeploma" class="form-control" value="<%=student.getsDeploma()==null?"":student.getsDeploma() %>" placeholder="Enter Deploma Field"></div>
      <div class="col-md-5"><input type="text" name="SPercent" class="form-control" value="<%=student.getsPercent()==null?"":student.getsPercent() %>" placeholder="Enter Deploma Percent"></div>
    </div>
     <div class="row form-group">
      <label class="control-label col-md-2">Roll No.:</label>
      <div class="col-md-5"><input type="text" name="SRollNo" class="form-control" value="<%=student.getsRollNo()==null?"":student.getsRollNo() %>" placeholder="Enter Student Roll No."></div>
    </div>
      <div class="row form-group">
      <label class="control-label col-md-2">Branch:</label>
          <div class="col-md-5 ">
          <select name="SBranch" value="<%=student.getsBranch() %>" class="form-control">
            <option value="CSE">Computer Science</option>
            <option value="EE">Electrical Engineering</option>
            <option value="ECE">Electronics Engineering</option>
            <option value="CE">Civil Engineering</option>
            <option value="ME">Mechanical Engineering</option>
          </select>
        </div>
     </div>
     <div class="row form-group">
      <label class="control-label col-md-2">Addmission Type:</label>
      <div class="col-md-2"><input type="checkbox" name="SJee" value="By Jee" >By Jee</div>
      <div class="col-md-3"><input type="checkbox" name="SDirect" value="Direct">Direct</div>
    </div><hr>
  
 

    <div class="row form-group">         
      <div class="col-md-offset-8 col-md-2">
        <button type="submit" class="btn btn-success">Save</button>
      </div>
      <div class="col-md-2">
        <button type="submit" class="btn btn-success">Reset</button>
      </div>
      
    </div>
  </form>
</div>
</div>
</center>

</div>
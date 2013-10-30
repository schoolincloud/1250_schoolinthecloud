<!DOCTYPE html>
<html lang="en">
<%@ page import="java.sql.*"%>
<%@ page import="com.studcloud.ConnectionOne" %>
<head>
<title>Learn Center | Courses</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<script type="text/javascript" src="js/jquery-1.5.2.js" ></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script>
<script type="text/javascript" src="js/Molengo_400.font.js"></script>
<script type="text/javascript" src="js/Expletus_Sans_400.font.js"></script>
<script type="text/javascript">
function validateForm()
{
	var sub=document.forms["subjectform"]["subject"].value;
	var index0 = document.getElementById("standrd").selectedIndex;
	var std = document.getElementById("standrd").options[index0].text;
	var std_h=document.getElementById("hstd");
	var cat_h=document.getElementById("catid");
	var cat=cat_h.value;
	if (cat=="std")
	{
		std_h.value=std;
	 
	 }
	if (cat=="gen")
	{
		std_h.value="00";
	 
	 }
	 if(sub==null || sub=="" )
	{
		  alert("Subject must be entered..");
		  return false;
	}
	

}
function editRecord(subid,subnm,std,oprt,category)
{
	document.getElementById("btnAdd").disabled=true;
    document.getElementById("btnUpdate").disabled=false;
    
    var subjtid=document.getElementById("subjid");
    var subjtnm=document.getElementById("subj");
    var standard=document.getElementById("standrd");
    var opt=document.getElementById("opert");
    var cat=document.getElementById("catid");
    
    subjtid.value=subid;
    subjtnm.value=subnm;
    standard.value=std;
    opt.value=oprt;

    if(category=="std")
	{
		document.getElementById("std").disabled=false;
		document.getElementById("rstd").checked=true;
		cat.value=category;
		
		}
	if(category=="gen")
	{
		document.getElementById("std").disabled=true;
		document.getElementById("rgen").checked=true;
		cat.value=category;
	}
	
}
function clear1()
{
	document.getElementById("btnAdd").disabled=false;
    document.getElementById("btnUpdate").disabled=true;
}
function disable(category)
{
	var cat=document.getElementById("catid");
	
	if(category=="std")
	{
		document.getElementById("std").disabled=false;
		cat.value="std";
		alert("You have selected Standardwise Category");
		
	}
	if(category=="gen")
	{
		document.getElementById("std").disabled=true;
		cat.value="gen";
		alert("You have selected General Category");
	}
}

</script>
<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<style type="text/css">.bg, .box2{behavior:url("js/PIE.htc");}</style>
<![endif]--></head>
<body id="page2">
<% HttpSession hs=request.getSession(false);%>
<div class="body1">
  <div class="main">
    <!-- header -->
    <header>
      <div class="wrapper">
        <nav>
		 <ul id="menu">
            <li><a href="schoolReg.jsp?usernm=<%=(String)hs.getAttribute("usernm")%>">School Registration</a></li>
            <li><a>Subject Add</a></li>
            <li><a href="uploadData.jsp?username=<%=(String)hs.getAttribute("usernm")%>">Uploads</a></li>
            <li><a href="uploadNotification.jsp?username=<%=(String)hs.getAttribute("usernm")%>">Notification</a></li>
            <li><a href="scholarshipExamNotice.jsp?username=<%=(String)hs.getAttribute("usernm")%>">Scholarship Exams</a></li>
          </ul>
         
        </nav>
       <p>&nbsp;</p>
	   </div>
      <div class="wrapper">
        <h1><a href="index.html" id="logo">Learn Center</a></h1>
      </div>
      <div id="slogan"> We Will Open The World<span>of knowledge for you!</span> </div>
    </header>
    <!-- / header -->
  </div>
</div>
<div class="body2">
  <div class="main">
    <!-- content -->
    <section id="content">
      <div class="box1">
        <div class="wrapper">
        User : <%=(String)hs.getAttribute("usernm")%> |&nbsp;&nbsp;&nbsp;<a href="login.jsp">Logout</a>
        </br>
          <h1 style="font-family:Shivaji01; font-size:36px" >ivaYaya naaoMdNaI</h1>
          
          <br/>
          <form name="subjectform" action="SubjectAdd" method="post" onSubmit="return validateForm();">
           Category:&nbsp;&nbsp;&nbsp;&nbsp;
              <input name="cat" id="rstd" type="radio" onclick="disable('std')" checked="checked">Standardwise</input> 
              <input id="rgen" name="cat" type="radio" onclick="disable('gen')">General</input>
               <br/>
          <input id="subjid" type="hidden" name="subid"><br/><br/>
          Standard : <select id="standrd" name="std"><option selected="selected">01</option>
          								  <option>02</option>
          								  <option>03</option>
          								  <option>04</option></select><br/><br/>
          Subject  :&nbsp;&nbsp;<input id="subj" type="text" name="subject"><br/><br/>
          <input id="opert" name="operation"  type="hidden" value="add">
          <input id="catid" name="catg" type="hidden" value="std">
          <input id="hstd" name="stdh" type="hidden" value="00">
          <input id="btnAdd" type="submit" value="ADD">&nbsp;&nbsp;&nbsp;
          <input id="btnUpdate" type="submit" value="UPDATE" disabled="disabled">&nbsp;&nbsp;&nbsp;
          <input id="btnReset" type="reset" value="RESET" onClick="clear1();">&nbsp;&nbsp;&nbsp;	
         </form>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
		  <table width="100%" border="1" style="background-color:scrollbar">
				  <tr align="left">
				    
				    <th scope="col">Subject Name</th>
				    <th scope="col">Standard</th>
				    <th scope="col">Category</th>
				   
				  </tr>
			  <%
			 
			  ConnectionOne condet=new ConnectionOne();
			  Connection con;
			  Statement st=null;
			  ResultSet rs=null;
			  String url,unm,pwd;
				url=condet.getUrl();
				unm=condet.getUnm();
				pwd=condet.getPwd();
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					System.out.print("Driver Loaded...");
					con=DriverManager.getConnection(url,unm,pwd);
					System.out.print("Connection Successful..");
					
					st=con.createStatement();
					System.out.println("******Select Query******");
					 rs=st.executeQuery("select * from studcloud.subjectlist");
					%>
					<% while(rs.next())
					{%>
						<tr style="background-color:teal;">
							<td style="color: white;"><%=rs.getString(2)%></td>
							<td style="color: white;"><%=rs.getString(3)%></td>
							<td style="color: white;"><%=rs.getString(6)%></td>
							<td style="color: white;">
							<a><label id="edit" onClick="editRecord('<%=rs.getString(1)%>','<%=rs.getString(2)%>','<%=rs.getString(3)%>','edit','<%=rs.getString(6)%>');">Edit</label></a>/<a><label id="delete" onClick="deleteS('<%=rs.getString(1)%>');">Delete</label></a>
							</td>
						</tr>
						
					<%}
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
			  
			  %>
</table>

<script language="javascript">

function deleteS(subjid)
{
	
	alert('delete');

	window.location="/StudCloudServer/html/SubjectAdd?operation=delete&subid="+subjid;
	
			
}	
</script>
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
        </div>
      </div>
    </section>
    <!-- content -->
    <!-- footer -->
    <footer></footer>
    <!-- / footer -->
  </div>
</div>
<script type="text/javascript">Cufon.now();</script>
<div align=center></div></body>
</html>
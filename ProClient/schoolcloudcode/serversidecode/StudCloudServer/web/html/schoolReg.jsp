<!DOCTYPE html>
<%@ page language="java" import="java.sql.*"%>
<%@ page import="com.studcloud.ConnectionOne" %>

<html lang="en">
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
<script language="javascript">

function validateForm()
{
	
var scode=document.forms["schoolRegform"]["shcode"].value;
var sname=document.forms["schoolRegform"]["sname"].value;
var saddr=document.forms["schoolRegform"]["sadd"].value;
var sphn=document.forms["schoolRegform"]["sphone"].value;
var cperson=document.forms["schoolRegform"]["contactPerson"].value;
var cmob=document.forms["schoolRegform"]["mobno"].value;
var pname=document.forms["schoolRegform"]["principalNm"].value;
var pmob=document.forms["schoolRegform"]["pmobno"].value;
var usernm=document.forms["schoolRegform"]["unm"].value;
var password=document.forms["schoolRegform"]["pwd"].value;
if (scode==null || scode=="")
{
  alert("School Code must be entered..");
  return false;
 }
if(isNaN(scode))
{
	alert("School Code must be in No format..");
	  return false;
}
if (sname==null || sname=="")
{
	alert("School Name must be entered..");
	return false;
}
if (saddr==null || saddr=="")
{
	alert("School Addrs must be entered..");
	return false;
}
if (sphn==null || sphn=="")
{
	alert("School Phone No. must be entered..");
	return false;
}
if(isNaN(sphn))
{
	alert("School Phone No. must be in No format..");
	  return false;
}
if (cperson==null || cperson=="")
{
	alert("Contact Person Name must be entered..");
	return false;
}
if (cmob==null || cmob=="")
{
	alert("Contact Person Mob No must be entered..");
	return false;
}
if(isNaN(cmob))
{
	alert("Contact Person Mob No must be in No format..");
	  return false;
}
if (pname==null || pname=="")
{
	alert("Principal Name must be entered..");
	return false;
}
if (pmob==null || pmob=="")
{
	alert("Principle Mob No  must be entered..");
	return false;
}
if(isNaN(pmob))
{
	alert("Principle Mob No must be in No format..");
	  return false;
}
if (usernm==null || usernm=="")
{
	alert("Username must be entered..");
	return false;
}
if (password==null || password=="")
{
	alert("Password must be entered..");
	return false;
}
}

function editRecord(code,name,add,phn,stdfrm,stdto,cperson,cmob,pname,pmob,unm,pwd,op){

	    document.getElementById("btnAdd").disabled=true;
	    document.getElementById("btnUpdate").disabled=false;

    var scode=document.getElementById("code");
    var sname=document.getElementById("schlnm");
    var sadd=document.getElementById("schladd");
    var sphn=document.getElementById("schlphone");
    var sstdfrm=document.getElementById("stdfrm");
    var sstdto=document.getElementById("stdto");
    var scperson=document.getElementById("cperson");
    var scmob=document.getElementById("cmob");
    var spname=document.getElementById("pname");
    var spmob=document.getElementById("pmob");
    var sunm=document.getElementById("schlunm");
    var spwd=document.getElementById("schlpwd");
    var opt=document.getElementById("opert");
    var edunm=document.getElementById("eunm");
    
    scode.value=code;
    sname.value=name;
    sadd.value=add;
    sphn.value=phn;
    sstdfrm.value=stdfrm;
    sstdto.value=stdto;
    scperson.value=cperson;
    scmob.value=cmob;
    spname.value=pname;
    spmob.value=pmob;
    sunm.value=unm;
    spwd.value=pwd;
    opt.value=op;
    edunm.value=unm;
   // alert(opt.value);
    //alert(edunm.value);
    // btnUpdate btnDelete
   
   
}

function clear1()
{
	document.getElementById("btnAdd").disabled=false;
    document.getElementById("btnUpdate").disabled=true;
   
    
    
}
function sessioncheck(username)
{
    alert(username);
    if(username==null || username=="")
        {
            window.location="/StudCloudServer/html/login.jsp";
        }
}
</script>	  
<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<style type="text/css">.bg, .box2{behavior:url("js/PIE.htc");}</style>
<![endif]-->


</head>
<body id="page2">
<% HttpSession hs=request.getSession(false);
if(session.getAttribute("usernm")==null) {

    response.sendRedirect("login.jsp");
    }
%>
<div class="body1">
  <div class="main">
    <!-- header -->
    <header>
      <div class="wrapper">
        <nav>
         <ul id="menu">
            <li><a>School Registration</a></li>
            <li><a href="SubjectAdd.jsp?username=<%=(String)hs.getAttribute("usernm")%>">Subject Add</a></li>
            <li><a href="uploadData.jsp?username=<%=(String)hs.getAttribute("usernm")%>">Uploads</a></li>
            <li><a href="uploadNotification.jsp?username=<%=(String)hs.getAttribute("usernm")%>">Notification</a></li>
            <li><a href="scholarshipExamNotice.jsp?username=<%=(String)hs.getAttribute("usernm")%>">Scholarship Exams</a></li>
          </ul>
          
        </nav>
        
		
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
       <a id="ttt"> </a>User :<%=(String)hs.getAttribute("usernm")%> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="login.jsp">Logout</a>
        </br>
          <h1 style="font-family:Shivaji01; font-size:36px" >SaaLa naaoMdNaI</h1>
          <br/>
          <br/>
          <div>
          <form id="frm1" name="schoolRegform" action="SchoolReg" method="post" onSubmit="return validateForm();">
          School Code : &nbsp;&nbsp;&nbsp;&nbsp;<input id="code" type="text" name="shcode" width="300px"><br/><br/>
          School Name :&nbsp;&nbsp;&nbsp; <input id="schlnm" type="text" name="sname" width="300px"><br/><br/>
          Address:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="schladd" type="text" name="sadd" width="300px"><br/><br/>
          Phone No:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="schlphone" type="text" name="sphone" width="300px"><br/><br/>
          Std. from :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select id="stdfrm" name="frmstd"><option selected="selected">01</option>
          								  <option>02</option>
          								  <option>03</option>
          								  <option>04</option>
          			</select>&nbsp;&nbsp;
          To : <select id="stdto" name="tostd">
          								  <option>01</option>
          								  <option>02</option>
          								  <option>03</option>
          								  <option selected="selected">04</option>
          			</select><br/><br/>
          Contact Person : <input id="cperson" type="text" name="contactPerson" width="300px" ><br/><br/>
          Mob.No. :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="cmob" type="text" name="mobno" width="300px"><br/><br/>
          Principal Name. : &nbsp;<input id="pname" type="text" name="principalNm" width="300px"><br/><br/>
          Mob.No. :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="pmob" type="text" name="pmobno"  width="300px"><br/><br/>
          Username :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="schlunm" type="text" name="unm"  width="300px%"><br/><br/>
          Password :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="schlpwd" type="text" name="pwd" width="300px"><br/><br/>
          UserType :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <select id="schlutype" name="usertype">
          								  <option selected="selected">school</option>
          								  
          </select><br/><br/>
          <input id="opert" name="operation"  type="hidden" value="add">
          <input id="unm1" name="eunm" type="hidden">
          <input id="btnAdd" type="submit" value="ADD">&nbsp;&nbsp;&nbsp;
          <input id="btnUpdate" type="submit" value="UPDATE" disabled="disabled">&nbsp;&nbsp;&nbsp;
          <!--<input id="btnDelete" type="submit" value="DELETE" disabled="disabled">&nbsp;&nbsp;&nbsp;	
          --><input id="btnCancel" type="reset" value="RESET" onClick="clear1();">&nbsp;&nbsp;&nbsp;
          
          </form>
          </div>
          <!--<s:form>
           <label style="font-family:Shivaji01; font-size:x-large"> SaaLa kaoD naM.:</label><br/>
           <label style="font-family:Shivaji01; font-size:x-large"> SaaLa naava:</label><br/>
           <label style="font-family:Shivaji01; font-size:x-large"> SaaLa p%ta :</label><br/>
           <label style="font-family:Shivaji01; font-size:x-large"> SaaLa durQvanaI :</label><br/>
           <label style="font-family:Shivaji01; font-size:x-large"> [ya%ta pasaUna to pya-Mt:</label><br/>
           <label style="font-family:Shivaji01; font-size:x-large"> SaaLa kaoD naM.:</label>
           <label style="font-family:Shivaji01; font-size:x-large"> SaaLa kaoD naM.:</label>
           <label style="font-family:Shivaji01; font-size:x-large"> SaaLa kaoD naM.:</label>
           <label style="font-family:Shivaji01; font-size:x-large"> SaaLa kaoD naM.:</label>
           
          </s:form>
          --><p>&nbsp;</p>
          
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <form method="post" name="form">
          <table width="100%" border="1" style="background-color:scrollbar">
  <tr align="left">
    <th scope="col" style="border: 2px;">Code No</th>
    <th scope="col">School Name</th>
    <th scope="col">Address </th>
    <th scope="col">Phone No</th>
    <th scope="col">Std From</th>
    <th scope="col">To Std</th>
    <th scope="col">Contact Person</th>
    <th scope="col">Mob No</th>
    <th scope="col">Principal Name</th>
    <th scope="col">Mob No</th>
    <th scope="col">User Name</th>
    <th scope="col">Password</th>
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
  //String url="jdbc:mysql://localhost:3306/java";
  //String unm="root";
  //String pwd="sql1";
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.print("Driver Loaded...");
		con=DriverManager.getConnection(url,unm,pwd);
		System.out.print("Connection Successful..");
		
		st=con.createStatement();
		System.out.println("******Select Query******");
		 rs=st.executeQuery("select * from studcloud.schoolreg");
		%>
		<% while(rs.next())
		{%>
			<tr style="background-color:teal;">
				<td style="color: white;"><%=rs.getString(1)%></td>
				<td style="color: white;"><%=rs.getString(2)%></td>
				<td style="color: white;"><%=rs.getString(3)%></td>
				<td style="color: white;"><%=rs.getString(4)%></td>
				<td style="color: white;"><%=rs.getString(5)%></td>
				<td style="color: white;"><%=rs.getString(6)%></td>
				<td style="color: white;"><%=rs.getString(7)%></td>
				<td style="color: white;"><%=rs.getString(8)%></td>
				<td style="color: white;"><%=rs.getString(9)%></td>
				<td style="color: white;"><%=rs.getString(10)%></td>
				<td style="color: white;"><%=rs.getString(11)%></td>
				<td style="color: white;"><%=rs.getString(12)%></td>
				<td style="color: white;"><a href="#ttt"><label id="edit" onClick="editRecord('<%=rs.getString(1)%>','<%=rs.getString(2)%>','<%=rs.getString(3)%>','<%=rs.getString(4)%>','<%=rs.getString(5)%>','<%=rs.getString(6)%>','<%=rs.getString(7)%>','<%=rs.getString(8)%>','<%=rs.getString(9)%>','<%=rs.getString(10)%>','<%=rs.getString(11)%>','<%=rs.getString(12)%>','edit');">Edit</label></a> / <a><label id="delete" onClick="deleteS('<%=rs.getString(1)%>','<%=rs.getString(11)%>');">Delete</label></a>
			</tr>
			
		<%}
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
  
  %>
</table>
</form>
<script language="javascript">

function deleteS(code,user)
{
	alert('delete');
	window.location="/StudCloudServer/html/SchoolReg?operation=delete&eunm="+user+"&shcode="+code;
			
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
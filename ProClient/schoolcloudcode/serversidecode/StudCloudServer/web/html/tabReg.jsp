 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
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
var macadd=document.forms["tabregform"]["mac"].value;
var studnm=document.forms["tabregform"]["stname"].value;
var studadd=document.forms["tabregform"]["stadd"].value;
var studcontact=document.forms["tabregform"]["stcontact"].value;


if (macadd==null || macadd=="")
  {
  alert("MAC Address must be entered..");
  return false;
  }
if(macadd.length!=12)
{
alert("MAC Address must be in 12-digit hexadecimal nos..");
return false;
}
if (studnm==null || studnm=="")
{
alert("Student Name must be entered..");
return false;
}
if (studadd==null || studadd=="")
{
alert("Student Address must be entered..");
return false;
}
if (studcontact==null || studcontact=="")
{
alert("Student Contact must be entered..");
return false;
}
}
function editRecord(mac,studnm,add,contact,std,oprt)
{
	document.getElementById("btnAdd").disabled=true;
    document.getElementById("btnUpdate").disabled=false;
    var macid=document.getElementById("macId");
    var sname=document.getElementById("studnm");
    var sadd=document.getElementById("studadd");
    var sphn=document.getElementById("studcontact");
    var sstd=document.getElementById("studStd");
    var opt=document.getElementById("opert");
    macid.value=mac;
    sname.value=studnm;
    sadd.value=add;
    sphn.value=contact;
    sstd.value=std;
    opt.value=oprt;
    
	
}
function clear1()
{
	document.getElementById("btnAdd").disabled=false;
    document.getElementById("btnUpdate").disabled=true;

    
   
    
    
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
            <li><a>Tab Registration</a></li>
          
           
          </ul>
          
        </nav>
        
		
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
          <h1 style="font-family:Shivaji01; font-size:36px">T^ba  naaoMdNaI</h1>
          <br/>
          <br/>
		  <div align="left">
          <form name="tabregform" action="TabReg" method="post" onsubmit="return validateForm();">
          Tab MAC Address :<input id="macId" type="text" name="mac"><br/><br/>
          Student Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:<input id="studnm" type="text" name="stname"><br/><br/>
          Student Addr&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:<input id="studadd" type="text" name="stadd"><br/><br/>
          Contact No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:<input id="studcontact" type="text" name="stcontact"><br/><br/>
          Standard &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:<select id="studStd" name="std"><option selected="selected">01</option>
          								  <option>02</option>
          								  <option>03</option>
          								  <option>04</option></select><br/><br/>
          <input id="opert" name="operation"  type="hidden" value="add">
          <input id="btnAdd" type="submit" value="ADD">&nbsp;&nbsp;&nbsp;
          <input id="btnUpdate" type="submit" value="UPDATE" disabled="disabled">&nbsp;&nbsp;&nbsp;
          <input id="btnReset" type="reset" value="RESET" onclick="clear1();">&nbsp;&nbsp;&nbsp;	
         </form>
        </div>
          <p>&nbsp;</p>
		  <table width="100%" border="1" style="background-color:scrollbar">
  <tr align="left">
    <th scope="col">MAC Address</th>
    <th scope="col">Student Name</th>
    <th scope="col">Student Address</th>
    <th scope="col">Contact No</th>
    <th scope="col">Standard</th>
   
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
		 rs=st.executeQuery("select * from studcloud.tabregister");
		%>
		<% while(rs.next())
		{%>
			<tr style="background-color:teal;">
				<td style="color: white;"><%=rs.getString(1)%></td>
				<td style="color: white;"><%=rs.getString(2)%></td>
				<td style="color: white;"><%=rs.getString(3)%></td>
				<td style="color: white;"><%=rs.getString(4)%></td>
				<td style="color: white;"><%=rs.getString(5)%></td>
				
				<td style="color: white;">
				<a><label id="edit" onclick="editRecord('<%=rs.getString(1)%>','<%=rs.getString(2)%>','<%=rs.getString(3)%>','<%=rs.getString(4)%>','<%=rs.getString(5)%>','edit');">Edit</label></a>/<a><label id="delete" onclick="deleteS('<%=rs.getString(1)%>');">Delete</label></a>
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

function deleteS(mac)
{
	
	alert('delete');

	window.location="/StudCloudServer/html/TabReg?operation=delete&mac="+mac;
	
			
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
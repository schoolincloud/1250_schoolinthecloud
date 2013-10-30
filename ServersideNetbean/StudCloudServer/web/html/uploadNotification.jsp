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

		var notice=document.forms["uploadDataform"]["notice"].value;
		var fname=document.forms["uploadDataform"]["filename"].value;
		
		var index0 = document.getElementById("std").selectedIndex;
		var std = document.getElementById("std").options[index0].text;
		
		var cat_h=document.getElementById("catid");
		var std_h=document.getElementById("hstd");
		var notice_h=document.getElementById("hnotice");
		var fpath_h=document.getElementById("hfpath");
		
		var cat=cat_h.value;
		
		var fup=document.getElementById("fileid");
		var fileName=fup.value;
		var fileext=fileName.substring(fileName.lastIndexOf('.') + 1);

		
		notice_h.value=notice;
			

		if (cat=="std")
		{
			std_h.value=std;
			
		 }
		if (cat=="gen")
		{
			std_h.value="00";
			
		 }
	
		if (notice==null || notice=="")
			{
			  alert("Notice field should not be empty..");
			  return false;
			 }
		if (fname==null || fname=="")
		{
		  alert("Please select files...");
		  return false;
		 }
			 
		if(fileext == "pdf" )
	        {
			fpath_h.value=fname;
			return true;
	        } 
		else
        {
        alert("Upload pdf files only");
        fup.focus();
        return false;
        }

  }

function editRecord(code,name,add,phn,stdfrm,stdto,cperson,cmob,pname,pmob,op){

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
	document.getElementById("btnUpload").disabled=false;
    document.getElementById("btnEdit").disabled=true;
   
    
    
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
<![endif]-->


</head>
<body id="page2">
<% HttpSession hs=request.getSession(false);%>
<div class="body1">
  <div class="main">
    <!-- header -->
    <header>
      <div class="wrapper">
        <nav>
         <ul id="menu">
            <li><a href="schoolReg.jsp?username=<%=(String)hs.getAttribute("usernm")%>">School Registration</a></li>
            <li><a href="SubjectAdd.jsp?username=<%=(String)hs.getAttribute("usernm")%>">Subject Add</a></li>
            <li><a href="uploadData.jsp?username=<%=(String)hs.getAttribute("usernm")%>">Uploads</a></li>
            <li><a>Notification</a></li>
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
       <a id="ttt"> </a>User :<%=(String)hs.getAttribute("usernm")%> |&nbsp;&nbsp;&nbsp;<a href="login.jsp">Logout</a>
        </br>
          <h2>Notification </h2>
          <label id="msg">${requestScope["message"]}</label>
          <br/>
          <div>
          <form id="frm3" name="uploadDataform" action="NotificationUpload" method="post" onSubmit="return validateForm();" enctype="multipart/form-data" >
            <p><br/>
              Category:&nbsp;&nbsp;&nbsp;&nbsp;
              <input name="cat" id="rstd" type="radio" onClick="disable('std')" checked="checked">Standardwise</input> 
              <input name="cat" id="rgen" type="radio" onClick="disable('gen')">General</input>
              <br/> <br/>
             Standard : &nbsp;&nbsp;&nbsp;&nbsp;<select id="std" name="standard"><option selected="selected">01</option>
          								  <option>02</option>
          								  <option>03</option>
          								  <option>04</option>
          			</select><br/><br/>
          	
         	 <label>Notice :  &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="ntc" name="notice" rows="5" cols="30"   ></textarea></label>
         	 <br/>
         	  
                <br/>
                Select File:  &nbsp;&nbsp;&nbsp;&nbsp;
                <input id="fileid" type="file" name="filename" width="300px">
                <br/>
                <br/>
               
                <input id="opert" name="operation" type="hidden" value="add">
                <input id="unm1" name="eunm" type="hidden" value="<%=(String)hs.getAttribute("usernm")%>">
                <input id="catid" name="catg" type="hidden" value="std">
                <input id="hstd" name="stdh" type="hidden" value="00">
                <input id="hnotice" name="noticeh" type="hidden">
                <input id="hfpath" name="fpathh" type="hidden">
              
              
              
              
                <input id="btnUpload" type="submit" value="UPLOAD">
&nbsp;&nbsp;&nbsp;
              <input id="btnEdit" type="submit" value="EDIT" disabled="disabled">
&nbsp;&nbsp;&nbsp;
              <!--<input id="btnDelete" type="submit" value="DELETE" disabled="disabled">&nbsp;&nbsp;&nbsp;	
          -->
              <input id="btnCancel" type="reset" value="RESET" onClick="clear1();">
&nbsp;&nbsp;&nbsp;            
</p>
          </p>
            </form>
          </div>
         
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <form method="post" name="form">
          <table width="100%" border="1" style="background-color:scrollbar">
 			 <tr align="left"><!--
   			 <th scope="col" style="border: 2px;">Sr No</th>
						 --><th scope="col">Notice</th>
						 <th scope="col">File Name </th>
						 <th scope="col">File Path</th>
						 <th scope="col">Std</th>
						 <th scope="col">Stdwise</th>
						 <th scope="col">General</th>
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
		 rs=st.executeQuery("select * from studcloud.uploadnotification");
		%>
		<% while(rs.next())
		{
		
		
		%>
			<tr style="background-color:teal;">
				<!--<td style="color: white;"><%=rs.getString(1)%></td>
				--><td style="color: white;"><%=rs.getString(2)%></td>
				<td style="color: white;"><%=rs.getString(3)%></td>
				<td style="color: white;"><%=rs.getString(4)%></td>
				<td style="color: white;"><%=rs.getString(5)%></td>
				<td style="color: white;"><%=rs.getString(7)%></td>
				<td style="color: white;"><%=rs.getString(8)%></td>
				
				
				<td style="color: white;"><a href="#ttt"><label id="show" onclick="showFile('<%=rs.getString(4)%>','<%=rs.getString(3)%>')">Show</label></a> / <a><label id="delete"  onclick="deleteS('<%=rs.getString(1)%>','<%=rs.getString(4)%>','<%=rs.getString(3)%>')" >Remove</label></a>
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
function showFile(filepath,filename)
{
	alert('show : '+filename);
	window.location="/StudCloudServer/"+filepath+"/"+filename;
			
}

function deleteS(srno,path,fname)
{
	alert('delete');
	var fpathnm="/"+path+"/"+fname;
	//alert(fpathnm);
	window.location="/StudCloudServer/html/DeleteNotification?srno="+srno+"&filenm="+fpathnm;
			
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
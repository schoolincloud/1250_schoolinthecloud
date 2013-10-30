<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Learn Center</title>
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
var unm=document.forms["loginFrm"]["username"].value;
var pwd=document.forms["loginFrm"]["pass"].value;
if (unm==null || unm=="")
  {
  alert("Username must be entered..");
  return false;
  }
if (pwd==null || pwd=="")
{
alert("Password must be entered..");
return false;
}
}



</script>

<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<style type="text/css">.bg, .box2{behavior:url("js/PIE.htc");}</style>
<![endif]-->
</head>
<body id="page1">
<div class="body1">
  <div class="main">
    <!-- header -->
    <header>
      <div class="wrapper">
        <nav>
          <p>&nbsp;</p>
          <ul id="menu"><!--
            <li><a>About</a></li>
            <li><a>Courses</a></li>
            <li><a>Programs</a></li>
            <li><a>Teachers</a></li>
            <li><a>Admissions</a></li>
            <li class="end"><a href="contacts.html">Contacts</a></li>
          --></ul>
        </nav>
        <ul id="icon">
          <!--<li><a href="#"><img src="images/icon1.jpg" alt=""></a></li>
          <li><a href="#"><img src="images/icon2.jpg" alt=""></a></li>
          <li><a href="#"><img src="images/icon3.jpg" alt=""></a></li>
        --></ul>
      </div>
      <div class="wrapper">
        <h1><a href="index.html" id="logo">Learn Center</a></h1>
      </div>
      <div id="slogan"> We Will Open The World<span>of knowledge for you!</span> </div>
      <ul class="banners">
        <li><a href="#"><img src="images/banner1.jpg" alt=""></a></li>
        <li><a href="#"><img src="images/banner2.jpg" alt=""></a></li>
        <li><a href="#"><img src="images/banner3.jpg" alt=""></a></li>
      </ul>
    </header>
    <!-- / header -->
  </div>
</div>
<div class="body2">
  <div class="main">
    <!-- content -->
    <section id="content">
      <div class="wrapper">
        
      </div>
      <div class="box1">
        <div class="wrapper">
          <article class="col1">
            <div class="pad_left1">
              <h2>Welcome </h2>
              <p class="font2">Learn Center is one of free website templates created by <span>TemplateMonster.com team</span></p>
              <p><strong>Learn Center Template</strong> is optimized for 1024X768 screen resolution. It�s also XHTML &amp; CSS valid. This website template has several pages: <a href="courses.html">Courses</a>, <a href="programs.html">Programs</a>, <a href="teachers.html">Teachers</a>, <a href="admissions.html">Admissions</a>, <a href="contacts.html">Contacts</a> (note that contact us form � doesn�t work).</p>
            </div>
            
            <div class="pad_left1">
              <h2></h2>
            </div>
            <div class="wrapper">
              <figure class="left marg_right1"></figure>
              <p class="pad_bot1 pad_top2"><strong></strong></p>
              <p> </p>
            </div>
            <div class="pad_top2">  </div>
          </article>
          <article class="col2 pad_left2">
            <div class="pad_left1">
              <h2> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Login</h2>
            </div>
            <form name="loginFrm" action="Login1" method="post" onSubmit="return validateForm()">
            <ul class="list1">
            <li>UserName :
                <input type="text"  name="username" > </li>
              <li>Password :&nbsp;
                <input type="password" name="pass" > </li>
              <li><input type="submit" value="Login">
              &nbsp;&nbsp;<input type="submit" name="btn1" value="Register"></li>
              
             
            </ul>
            </form>
            <%! String msg="Enter Username & Password"; %>
            <% HttpSession hs=request.getSession(false);
               String err=(String)hs.getAttribute("msg");
               if(err!=null)
               {
            	  %><%= err+"..Login Again"%>
               <%} 
               else
               {
               %>
               
               <%= msg%>
               <%
               }
               %>
             <div class="pad_left1">
              <h2></h2>
            </div>
           
           
          </article>
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
<div align=center>
</div></body>
</html>
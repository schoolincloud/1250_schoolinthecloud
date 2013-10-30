package com.studcloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SchoolReg extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Database Connection Data
	String url,username,password;
	Connection con;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		ConnectionOne condet=new ConnectionOne();
		url=condet.getUrl();
		username=condet.getUnm();
		password=condet.getPwd();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.print("Driver Loaded...");
			con=DriverManager.getConnection(url,username,password);
			System.out.print("Connection Successful..");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}//end init
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("In doGet()");
		doPost(request, response);
			}//end doGet
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost()");
		Statement st=null;
		ResultSet rs=null;
		// User Login Data //
		/*String usernm=request.getParameter("username");
		String pass=request.getParameter("pass");
		System.out.println("Username:-"+usernm);
		System.out.println("Password:-"+pass);*/
		
		String code,schoolname,address,phone,stdfrm,stdto,contactperson,cpmob,principal,pmob,sch_username,sch_password,registerOn,registerBy;
		String usertype,opert,edunm;
		code=request.getParameter("shcode");
		schoolname=request.getParameter("sname");
		address=request.getParameter("sadd");
		phone=request.getParameter("sphone");
		stdfrm=request.getParameter("frmstd");
		stdto=request.getParameter("tostd");
		contactperson=request.getParameter("contactPerson");
		cpmob=request.getParameter("mobno");
		principal=request.getParameter("principalNm");
		pmob=request.getParameter("pmobno");
		sch_username=request.getParameter("unm");
		sch_password=request.getParameter("pwd");
		usertype=request.getParameter("usertype");
		opert=request.getParameter("operation");
		edunm=request.getParameter("eunm");
		//registerOn=request.getParameter("");
		//registerBy=request.getParameter("");
		PrintWriter pr=response.getWriter();
		RequestDispatcher rd;
		try {
				st=con.createStatement();
				if(opert.equals("add"))
				{
					System.out.println("******Insert Query******");
					int rowcount=st.executeUpdate("insert into studcloud.schoolreg(code,schoolname,address,phone,stdfrm,stdto,contactperson,cpmob,principal,pmob,sch_username,sch_password)values('"+code+"','"+schoolname+"','"+address+"','"+phone+"','"+stdfrm+"','"+stdto+"','"+contactperson+"','"+cpmob+"','"+principal+"','"+pmob+"','"+sch_username+"','"+sch_password+"')");
					System.out.println("Record Inserted in schoolreg table..... "+rowcount);
					int rowcount1=st.executeUpdate("insert into studcloud.login(username,password,usertype)values('"+sch_username+"','"+sch_password+"','"+usertype+"');");
					System.out.println("Record Inserted in login table..... "+rowcount1);
					rd=request.getRequestDispatcher("schoolReg.jsp");
					rd.forward(request,response);
				}
				if(opert.equals("edit"))
				{
					System.out.println("******Update Query******");
					int rowcount=st.executeUpdate("UPDATE studcloud.schoolreg SET code='"+code+"',schoolname='"+schoolname+"',address='"+address+"',phone='"+phone+"',stdfrm='"+stdfrm+"',stdto='"+stdto+"',contactperson='"+contactperson+"',cpmob='"+cpmob+"',principal='"+principal+"',pmob='"+pmob+"',sch_username='"+sch_username+"',sch_password='"+sch_password+"' WHERE code='"+code+"';"); 
					System.out.println("Record UPDATED in schoolreg table..... "+rowcount);
					
					int rowcount1=st.executeUpdate("DELETE FROM studcloud.login WHERE username='"+edunm+"';");
					System.out.println("Record deleted for new insertion in login table..... "+rowcount1);
					
					int rowcount2=st.executeUpdate("insert into studcloud.login(username,password,usertype)values('"+sch_username+"','"+sch_password+"','"+usertype+"');");
					System.out.println("Record NEWLY UPDATED in login table..... "+rowcount2);
					
					rd=request.getRequestDispatcher("schoolReg.jsp");
					rd.forward(request,response);
				}
				if(opert.equals("delete"))
				{
					System.out.println("******Delete Query******");
					int rowcount1=st.executeUpdate("DELETE FROM studcloud.login WHERE username='"+edunm+"';");
					System.out.println("Record DELETED in login table..... "+rowcount1);
					
					int rowcount2=st.executeUpdate("DELETE FROM studcloud.schoolreg WHERE code='"+code+"';");
					System.out.println("Record DELETED in schoolreg table..... "+rowcount2);
					
					rd=request.getRequestDispatcher("schoolReg.jsp");
					rd.forward(request,response);
				}
				
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rd=request.getRequestDispatcher("schoolReg.jsp");
			rd.forward(request,response);
		}
	}
}

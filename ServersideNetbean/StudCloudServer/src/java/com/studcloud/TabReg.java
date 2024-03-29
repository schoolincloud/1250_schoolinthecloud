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

public class TabReg extends HttpServlet {
	
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
		
		String macadd,studname,address,contactno,stdcode,studcode,schoolcode,opert;
		
		macadd=request.getParameter("mac");
		studname=request.getParameter("stname");
		address=request.getParameter("stadd");
		contactno=request.getParameter("stcontact");
		stdcode=request.getParameter("std");
		opert=request.getParameter("operation");
		
		
		// Standard Code as it is: 01,02,03,04 etc.
		// School Code as it is 101,102,...etc.
		// Student code as it is schoolcode+stdcode+studentcode ie 001(AutoGenerated)=10101001
		//studcode=request.getParameter("tostd");
		//schoolcode=request.getParameter("contactPerson");
		//registerOn=request.getParameter("");
		//registerBy=request.getParameter("");
		
		
		
		PrintWriter pr=response.getWriter();
		RequestDispatcher rd;
		try {
				st=con.createStatement();
				if(opert.equals("add"))
				{
						System.out.println("******Insert Query in Tab Registration******");
						int rowcount=st.executeUpdate("insert into studcloud.tabregister(macadd,studname,address,contactno,stdcode)values('"+macadd+"','"+studname+"','"+address+"','"+contactno+"','"+stdcode+"')");
						System.out.println("Record Inserted into tabregister.... Tab registered Successfully..! "+rowcount);
						rd=request.getRequestDispatcher("tabReg.jsp");
						rd.forward(request,response);
				}			
				if(opert.equals("edit"))
				{
						System.out.println("******Update Query in Tab Registration******");
						int rowcount1=st.executeUpdate("UPDATE studcloud.tabregister SET macadd='"+macadd+"',studname='"+studname+"',address='"+address+"',contactno='"+contactno+"',stdcode='"+stdcode+"' WHERE macadd='"+macadd+"';"); 
						System.out.println("Record UPDATED in tabreg table..... "+rowcount1);
						rd=request.getRequestDispatcher("tabReg.jsp");
						rd.forward(request,response);
				}
				if(opert.equals("delete"))
				{
						System.out.println("******Delete Query in Tab Registration******");
						int rowcount1=st.executeUpdate("DELETE FROM studcloud.tabregister WHERE macadd='"+macadd+"';"); 
						System.out.println("Record DELETED in tabreg table..... "+rowcount1);
						rd=request.getRequestDispatcher("tabReg.jsp");
						rd.forward(request,response);
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Invalid / Duplicate entry...");
			rd=request.getRequestDispatcher("tabReg.jsp");
			rd.forward(request,response);
		}
	}
}

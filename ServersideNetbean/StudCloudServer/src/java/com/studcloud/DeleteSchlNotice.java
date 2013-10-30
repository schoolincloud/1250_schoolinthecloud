package com.studcloud;

import java.io.File;
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

public class DeleteSchlNotice extends HttpServlet {

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
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("In doGet()");
		Statement st=null;
		ResultSet rs=null;
		String srno=request.getParameter("srno");
		String filenm=request.getParameter("filenm");
		String absoluteDiskPath = getServletContext().getRealPath(filenm);
		absoluteDiskPath=absoluteDiskPath.replace("\\","/");
		System.out.println("filepath :"+absoluteDiskPath);

		PrintWriter pr=response.getWriter();
		RequestDispatcher rd;
		try {
				st=con.createStatement();
				File afile =new File(absoluteDiskPath);
				if(afile.delete())
				{
					
						System.out.println("******Delete Query in schoralshipexamnotice******");
						int rowcount1=st.executeUpdate("DELETE FROM schoralshipexamnotice WHERE srno='"+srno+"';"); 
						System.out.println("Record DELETED in schoralshipexamnotice table..... "+rowcount1);
						rd=request.getRequestDispatcher("scholarshipExamNotice.jsp");
						rd.forward(request,response);
				}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Record not deleted in schoralshipexamnotice table....");
			rd=request.getRequestDispatcher("scholarshipExamNotice.jsp");
			rd.forward(request,response);
		}
		
			}//end doGet
	
	
}

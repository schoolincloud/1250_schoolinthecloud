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
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	
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
	
	public void createDir(String usertype)
	{
		String relativeWebPath="/uploads";
		String path=null;
		String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
		absoluteDiskPath=absoluteDiskPath.replace("\\","/");
		String[] filetype=new String[]{"Video","E-books","Images","Notification","Scholarship Notice"};
		String[] std=new String[]{"01","02","03","04","00"};

		for(int i=0;i<filetype.length;i++)
		{
			path=absoluteDiskPath+"/"+usertype+"/"+filetype[i]+"/"+"Standard";
			 File theDir = new File(path);  // Defining Directory/Folder Name  
		     
		        if (!theDir.exists()){  // Checks that Directory/Folder Doesn't Exists!  
		         boolean result = theDir.mkdirs();    
		         if(result){  
		        	 System.out.println("Directory created for "+filetype[i]);}  
		        }
		        
		        for(int j=0;j<std.length;j++)
		        {
		        	 String path1=path+"/"+std[j];
		        	 File theDir1 = new File(path1);  // Defining Directory/Folder Name  
				     
				        if (!theDir1.exists()){  // Checks that Directory/Folder Doesn't Exists!  
				         boolean result = theDir1.mkdirs();    
				         if(result){  
				        	 System.out.println("Directory created for "+std[j]);}  
				        }
		        }
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost()");
		Statement st=null;
		ResultSet rs=null;
		HttpSession session=null;
		String id=null;
		int flag=0;
		// User Login Data //
		String usernm=request.getParameter("username");
		String pass=request.getParameter("pass");
		
		System.out.println("Username:-"+usernm);
		System.out.println("Password:-"+pass);
		
		PrintWriter pr=response.getWriter();
		RequestDispatcher rd;
		try {
			st=con.createStatement();
			System.out.println("******Select Query******");
			 rs=st.executeQuery("select * from studcloud.login");
			
			while(rs.next())
			{
				System.out.println("Username="+rs.getString(1)+"\tpassword="+rs.getString(2)+"\tUsertype="+rs.getString(3));
				if(usernm.equals(rs.getString(1))&& pass.equals(rs.getString(2)))
				{
					
					String msg=null;
					session=request.getSession(true);
					session.setAttribute("msg", msg);
					session.setAttribute("usernm", usernm);
					if(rs.getString(3).equals("Admin"))
					{
					System.out.println("Admin Login Successful..");
					rd=request.getRequestDispatcher("schoolReg.jsp");
					rd.forward(request,response);
					String utype=rs.getString(3);
					createDir(utype);
					}
					else if(rs.getString(3).equals("school"))
					{
						System.out.println("School Login Successful..");
						rd=request.getRequestDispatcher("tabReg.jsp");
						rd.forward(request,response);
					}
					flag=1;
					break;
				}
				//else
				//{
					//flag=2;
					//pr.print("Invalid User...");
					/*String msg = "Login Failed";
					session=request.getSession(true);
					System.out.println("Invalid User");
					id=session.getId();
					System.out.println("Session Id.."+id);
					session.setAttribute("msg", msg);
					System.out.println("Msg.."+msg);
					rd=request.getRequestDispatcher("login.jsp");
					rd.forward(request,response);*/
					
				
				
			}
			
			if(flag==0)
			{
				String msg = "Login Failed";
				session=request.getSession(true);
				System.out.println("Invalid User");
				id=session.getId();
				System.out.println("Session Id.."+id);
				session.setAttribute("msg", msg);
				System.out.println("Msg.."+msg);
				rd=request.getRequestDispatcher("login.jsp");
				rd.forward(request,response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request,response);
		}
	}
}

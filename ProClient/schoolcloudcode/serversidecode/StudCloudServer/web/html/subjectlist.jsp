<%@ page language="java" contentType="text/xml; charset=UTF-8"
   pageEncoding="UTF-8" import="java.sql.*"%>
 
<%@ page import="com.studcloud.ConnectionOne" %>
<%
ConnectionOne condet=new ConnectionOne();
Connection con;
Statement st=null;
ResultSet rs=null;
String url,unm,pwd;

	url=condet.getUrl();
	unm=condet.getUnm();
	pwd=condet.getPwd();
	int cnt=0;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
				System.out.print("Driver Loaded...");
				con=DriverManager.getConnection(url,unm,pwd);
				System.out.print("Connection Successful..\n");
		        st=con.createStatement();
                String std=request.getParameter("std");
               // String std=request.getParameter("std");
                System.out.print("XML formating...\n");
                rs=st.executeQuery("select subject from subjectlist where standard='"+std+"'");
                System.out.print("Select Query...\n");
                		
                		//out.println("<?xml version='1.0' encoding='UTF-8'?>");
                        out.println("<Subjects>");
                         while(rs.next())
                            {
                            String subject=rs.getString(1);
                           	out.println("<subject>"+subject+"</subject>");
                            }
                        
                        out.println("</Subjects>");
                        System.out.print("xml parse1...\n");
}
		catch(Exception ex)
		{
                    out.println(ex.getMessage());
                }

         

        %>

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
				System.out.print("Connection Successful..");
		        st=con.createStatement();
                String std=request.getParameter("std");
               // String subjectcode=request.getParameter("subjectcode");
              //  String filedoctype=request.getParameter("filedoctype");
               // String filename=request.getParameter("filename");
               
              
                System.out.print("XML formating std:"+std);
                rs=st.executeQuery("select srno,notice,pdf_file,filepath,std,usercode,isStd,isGen from uploadnotification where std='"+std+"';");
                System.out.print("Select Query...");
                
                		out.println("<Notices>");
                       
                        while(rs.next())
                            {
                            String notice=rs.getString(2);
                            String fname=rs.getString(3);
                            String fpath=rs.getString(4);
                           
                            out.println("<Notice>");
                            out.println("<notice>"+notice+"</notice>");
                            out.println("<filename>"+fname+"</filename>");
                            out.println("<filepath>"+fpath+"</filepath>");
                            out.println("</Notice>");
                            }
                       
                      
                        out.println("</Notices>");
                        System.out.print("xml parse... notificationlist.jsp");
             
}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
                    out.println("<Results>");
                     out.println("<Result>");
                      out.println("<Status>Fail</Status>");
       				 out.println("</Result>");
					out.println("</Results>");
                }

         

        %>

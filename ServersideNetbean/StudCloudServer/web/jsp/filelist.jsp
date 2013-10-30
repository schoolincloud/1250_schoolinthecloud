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
                String subjectcode=request.getParameter("subjectcode");
                String filedoctype=request.getParameter("filedoctype");
                String filename=request.getParameter("filename");
               
              
                System.out.print("XML formating std:"+std+" sub:"+subjectcode+" dctype:"+filedoctype+" filename:"+filename);
                rs=st.executeQuery("select srno,usercode,std,subjectcode,title,filename,filepath,filedoctype,isStd,isGeneral from uploadfile where std='"+std+"' and subjectcode='"+subjectcode+"' and filedoctype='"+filedoctype+"';");
                System.out.print("Select Query...");
                
                		out.println("<Files>");
                       
                        while(rs.next())
                            {
                            String title=rs.getString(5);
                            String fname=rs.getString(6);
                            String fpath=rs.getString(7);
                            String doctype=rs.getString(8);
                            out.println("<File>");
                            out.println("<title>"+title+"</title>");
                            out.println("<filename>"+fname+"</filename>");
                            out.println("<filepath>"+fpath+"</filepath>");
                            out.println("<doctype>"+doctype+"</doctype>");
                            out.println("</File>");
                            }
                       
                      
                        out.println("</Files>");
                        System.out.print("xml parse... filelist.jsp");
             
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

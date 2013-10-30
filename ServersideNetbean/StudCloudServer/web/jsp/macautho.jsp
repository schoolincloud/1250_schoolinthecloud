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
                String mac=request.getParameter("mac");
               // String std=request.getParameter("std");
                System.out.print("XML formating");
                rs=st.executeQuery("select macadd,studname,address,contactno,stdcode,studcode,schoolcode from studcloud.tabregister where macadd='"+mac+"'");
                System.out.print("Select Query...");
                        out.println("<Results>");
                        out.println("<Result>");
                        if(rs.next())
                            {
                            String macadd=rs.getString(1);
                            String stud_name=rs.getString(2);
                            String std=rs.getString(5);
                            out.println("<Status>Success</Status>");
                            out.println("<macaddress>"+macadd+"</macaddress>");
                            out.println("<standard>"+std+"</standard>");
                            out.println("<studentname>"+stud_name+"</studentname>");
                           
                            }
                        else
                            {
                            out.println("<Status>Fail</Status>");
                            }
                        out.println("</Result>");
                        out.println("</Results>");
                        System.out.print("xml parse...");
}
		catch(Exception ex)
		{
                    out.println(ex.getMessage());
                }

         

        %>

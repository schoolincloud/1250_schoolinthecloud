package com.studcloud;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.oreilly.servlet.MultipartRequest;

public class NotificationUploadHandler extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "D:/uploads/";
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
		    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	 	            throws ServletException, IOException {
		
		 File theDir = new File(UPLOAD_DIRECTORY);  // Defining Directory/Folder Name  
	     
	        if (!theDir.exists()){  // Checks that Directory/Folder Doesn't Exists!  
	         boolean result = theDir.mkdirs();    
	         if(result){  
	        	 System.out.println("Directory created for "+UPLOAD_DIRECTORY);}  
	        }
		
		MultipartRequest mpreg=new MultipartRequest(request, UPLOAD_DIRECTORY, 104857600);
		System.out.println("In doPost()");
		Statement st=null;
		ResultSet rs=null;
		String srno,notice,std,usercode,pdf_file=null,filepath = null,filedoctype=null,isStd = "N",isGen="N",opert,cat;
		
		
		notice=mpreg.getParameter("noticeh");
		usercode=mpreg.getParameter("eunm");
		std=mpreg.getParameter("stdh");
		opert=mpreg.getParameter("operation");
		cat=mpreg.getParameter("catg");
		pdf_file=mpreg.getParameter("fpathh");
		pdf_file=pdf_file.substring(pdf_file.lastIndexOf("\\")+1);
		pdf_file=pdf_file.replace("\\","/");
		if(cat.equals("std"))
		{
			isStd="Y";
		}
		if(cat.equals("gen"))
		{
			isGen="Y";
		}
		String relativeWebPath="/uploads/Admin/Notification/Standard/"+std;
		String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
		absoluteDiskPath=absoluteDiskPath.replace("\\","/");
		String path=absoluteDiskPath;
		filepath=path.substring(path.lastIndexOf("uploads/"));
		System.out.println("usercode:"+usercode+" std:"+std+" notice:"+notice+" pdf:"+pdf_file+" isStd:"+isStd+" isGen:"+isGen+" filepath:"+filepath);
         
		PrintWriter pr=response.getWriter();
		RequestDispatcher rd;
		InputStream inStream = null;
 		OutputStream outStream = null;
	        //process only if its multipart content
	        if(ServletFileUpload.isMultipartContent(request)){
	           try {
	        	   st=con.createStatement();
	        	   System.out.println("******Insert Query for uploadnotification Table******");
	        	   int rowcount=st.executeUpdate("insert into studcloud.uploadnotification(notice,pdf_file,filepath,std,usercode,isStd,isGen)values('"+notice+"','"+pdf_file+"','"+filepath+"','"+std+"','"+usercode+"','"+isStd+"','"+isGen+"')");
	        	   System.out.println("Record Inserted in uploadnotification table..... "+rowcount);
	        	   System.out.println("filename: "+pdf_file+" filepath:"+filepath);
	        	 
	        	   File afile =new File(UPLOAD_DIRECTORY+pdf_file);
	       	       File bfile =new File(path+"/"+pdf_file);
		    
		       	    inStream = new FileInputStream(afile);
		       	    outStream = new FileOutputStream(bfile);
		    
		       	    byte[] buffer = new byte[1024];
		    
		       	    int length;
		       	    //copy the file content in bytes 
		       	    while ((length = inStream.read(buffer)) > 0){
		    
		       	    	outStream.write(buffer, 0, length);
		    
		       	    }
		       	    inStream.close();
		    	    outStream.close();
		 
		    	    //delete the original file
		    	    afile.delete();
	 
	    	    System.out.println("File is copied successful!"); 
	    	    /*
					                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
						               
				 	                for(FileItem item : multiparts){
				 	                	System.out.println("filename:1 "+item.getName());
				 	  	        	 
				 	                    if(!item.isFormField()){
										 	                    	String name = new File(item.getName()).getName();
										 	                    	item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
										 	                      
				 	                   
				 	                        }//end if
				 	                }//end for
				 	            */
				               //File uploaded successfully
				 	               request.setAttribute("message", "File Uploaded Successfully....");
						
	 	            } catch (Exception ex) {
		               request.setAttribute("message", "File Upload Failed due to " + ex);
		            }         
		          
	 	        }else{
	 	            request.setAttribute("message",
	                                 "Sorry this Servlet only handles file upload request");
		        }
		     
	        request.getRequestDispatcher("uploadNotification.jsp").forward(request, response);
	 	      
	    }
	
	
}



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

public class FileUploadHandler extends HttpServlet {
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
	
	public void upload(HttpServletRequest request,String path)
	{
		System.out.println("In doPost()");
		try {
			MultipartRequest mpreg1=new MultipartRequest(request, path, 104857600);
			System.out.println("Uploads");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
		String srno,usercode,std,subjectcode,title,filename=null,filepath=null,filedoctype,isStd = "N",isGen="N",opert,cat;
		

		
		usercode=mpreg.getParameter("eunm");
		std=mpreg.getParameter("stdh");
		subjectcode=mpreg.getParameter("subh");
		title=mpreg.getParameter("titleh");
		filedoctype=mpreg.getParameter("filetypeh");
		opert=mpreg.getParameter("operation");
		cat=mpreg.getParameter("catg");
		filename=mpreg.getParameter("fpathh");
		filename=filename.substring(filename.lastIndexOf("\\")+1);
		filename=filename.replace("\\","/");
		if(cat.equals("std"))
		{
			isStd="Y";
		}
		if(cat.equals("gen"))
		{
			isGen="Y";
		}
		String relativeWebPath="/uploads/Admin/"+filedoctype+"/Standard/"+std;
		String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
		absoluteDiskPath=absoluteDiskPath.replace("\\","/");
		String path=absoluteDiskPath+"/"+subjectcode;
		
		 File theDir1 = new File(path);  // Defining Directory/Folder Name  
	     
	        if (!theDir1.exists()){  // Checks that Directory/Folder Doesn't Exists!  
	         boolean result = theDir1.mkdirs();    
	         if(result){  
	        	 System.out.println("Directory created for "+subjectcode);}  
	        }
	        
		filepath=path.substring(path.lastIndexOf("uploads/"));
	       // upload(request,path);
		
		System.out.println("usercode:"+usercode+" std:"+std+" subjectcode:"+subjectcode+" title:"+title+" isStd:"+isStd+" isGen:"+isGen+" doctype:"+filedoctype);
		
		PrintWriter pr=response.getWriter();
		RequestDispatcher rd;
		
		InputStream inStream = null;
 		OutputStream outStream = null;
	        //process only if its multipart content
	       if(ServletFileUpload.isMultipartContent(request)){
	           try {
	        	   st=con.createStatement();
	        	   System.out.println("******Insert Query for uploadfile Table******");
	        	   int rowcount=st.executeUpdate("insert into studcloud.uploadfile(usercode,std,subjectcode,title,filename,filepath,filedoctype,isStd,isGeneral)values('"+usercode+"','"+std+"','"+subjectcode+"','"+title+"','"+filename+"','"+filepath+"','"+filedoctype+"','"+isStd+"','"+isGen+"')");
	        	   System.out.println("Record Inserted in schoolreg table..... "+rowcount);
	        	   System.out.println("filename: "+path+" "+filename);
	        	  
	        	   File afile =new File(UPLOAD_DIRECTORY+filename);
	       	       File bfile =new File(path+"/"+filename);
	    
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
	       	    				/*  List<FileItem> multiparts =new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
						               
				 	                for(FileItem item : multiparts){
				 	                	System.out.println("filename:1 "+item.getName());
				 	  	        	 
				 	                    if(!item.isFormField()){
										 	                    	String name = new File(item.getName()).getName();
										 	                    	item.write(new File(path+File.pathSeparator+filename));
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
		     
	        request.getRequestDispatcher("uploadData.jsp").forward(request, response);
	 	      
	    }
	
	
	
}



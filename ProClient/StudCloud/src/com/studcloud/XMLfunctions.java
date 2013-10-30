package com.studcloud;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.app.Activity;
//import android.sax.Element;


public class XMLfunctions {
	static String port="http://192.168.1.46:8080/";
	public final static Document XMLfromString(String xml){
		
		Document doc = null;
		
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
        	
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(xml));
	        doc = db.parse(is); 
	        
		} catch (ParserConfigurationException e) {
			System.out.println("XML parse error: " + e.getMessage());
			return null;
		} catch (SAXException e) {
			System.out.println("Wrong XML file structure: " + e.getMessage());
            return null;
		} catch (IOException e) {
			System.out.println("I/O exeption: " + e.getMessage());
			return null;
		}
		       
        return doc;
        
	}
	
	/** Returns element value
	  * @param elem element (it is XML tag)
	  * @return Element value otherwise empty String
	  */
	 public final static String getElementValue( Node elem ) {
	     Node kid;
	     if( elem != null){
	         if (elem.hasChildNodes()){
	             for( kid = elem.getFirstChild(); kid != null; kid = kid.getNextSibling() ){
	                 if( kid.getNodeType() == Node.TEXT_NODE  ){
	                     return kid.getNodeValue();
	                 }
	             }
	         }
	     }
	     return "";
	 }
		 
	 public static String getXML(String url){	 
			String line = null;

			try {
				
				DefaultHttpClient httpClient = new DefaultHttpClient();
				
				HttpPost httpPost = new HttpPost(port+url);
				//HttpPost httpPost = new HttpPost("http://192.168.1.5:8080/StudCloudServer/html/subjectlist.jsp?std=02");
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				line = EntityUtils.toString(httpEntity);
				
			} catch (UnsupportedEncodingException e) {
				line = "<results status=\"error\"><msg>"+ e.getMessage()+"</msg></results>";
			} catch (MalformedURLException e) {
				line = "<results status=\"error\"><msg>"+ e.getMessage()+"</msg></results>";
			} catch (IOException e) {
				line = "<results status=\"error\"><msg>"+ e.getMessage()+"</msg></results>";
			}

			return line;

	}
	 
	
	 
	 
	 public static String getDetailXML(String type){	 
			String line = null;

			try {
				
				DefaultHttpClient httpClient = new DefaultHttpClient();
				
				
				HttpPost httpPost = new HttpPost("http://192.168.1.38:8080/StudCloudServer/html/macautho.jsp?mac=44444444444");
				
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				line = EntityUtils.toString(httpEntity);
					
			} catch (UnsupportedEncodingException e) {
				line = "<results status=\"error\"><msg>"+ e.getMessage()+"</msg></results>";
			} catch (MalformedURLException e) {
				line = "<results status=\"error\"><msg>"+ e.getMessage()+"</msg></results>";
			} catch (IOException e) {
				line = "<results status=\"error\"><msg>"+ e.getMessage()+"</msg></results>";
			}

			return line;

	}
	 
	 
	public static int numResults(Document doc){		
		Node results = doc.getDocumentElement();
		int res = -1;
		
		try{
			res = Integer.valueOf(results.getAttributes().getNamedItem("count").getNodeValue());
		}catch(Exception e ){
			res = -1;
		}
		
		return res;
	}

	public static String getValue(Element e, String str) {		
		NodeList n = (NodeList) e.getElementsByTagName(str);		
		return XMLfunctions.getElementValue(n.item(0));
	}
}

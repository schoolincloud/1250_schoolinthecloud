package com.studcloud;

import java.net.InetAddress;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainPage extends Activity {
	
	String macid=null,catg=null;
	WifiManager wifimgr;
	WifiInfo winfo;
	SharedPreferences sp;
	SharedPreferences.Editor Ed;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WifiManager wifimgr=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        WifiInfo winfo=wifimgr.getConnectionInfo();
        macid=winfo.getMacAddress();
        setContentView(R.layout.mainpage);
        authenicate();
        
        sp=getSharedPreferences("Login", 0);
        Ed=sp.edit();
        
        Toast.makeText(this, "MAC Id: "+macid,Toast.LENGTH_LONG).show();
        
        ImageButton video=(ImageButton)findViewById(R.id.btnVideo);
        ImageButton notice=(ImageButton)findViewById(R.id.btnNotice);
        ImageButton ebook=(ImageButton)findViewById(R.id.btnEbook);
        ImageButton images=(ImageButton)findViewById(R.id.btnImages);
        
        ImageButton go=(ImageButton)findViewById(R.id.btnstdselection);
        TextView mview=(TextView)findViewById(R.id.textViewM);
        Typeface font = Typeface.createFromAsset(getAssets(), "Shivaji01.ttf");
        mview.setTypeface(font);
        go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				/*if(sp.getString("Category", null).equals("gen"))
	        	{
	        		authenicate();
	        		catg="std";
					Ed.putString("Category",catg );
		        	Ed.commit();
	        		Intent obj=new Intent(v.getContext(),StdActivity.class);
	    			startActivityForResult(obj,0);
	        		
	        	}
				else{*/
				authenicate();
				catg="std";
				Ed.putString("Category",catg );
	        	Ed.commit();
	        	
	        	
        	        Intent obj=new Intent(v.getContext(),StdActivity.class);
    				startActivityForResult(obj,0);
				//}
			}
		});
        
       video.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			catg="gen";
			String doc="Video";
			Ed.putString("MAC",macid );              
        	Ed.putString("Category",catg );
        	Ed.putString("DocType",doc );
        	Ed.commit();
        	Intent obj=new Intent(v.getContext(),SubjectList.class);
			startActivityForResult(obj,0);
		}
	});
       images.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			catg="gen";
			String doc="Images";
			Ed.putString("MAC",macid );              
        	Ed.putString("Category",catg );
        	Ed.putString("DocType",doc );
        	Ed.commit();
        	Intent obj=new Intent(v.getContext(),SubjectList.class);
			startActivityForResult(obj,0);
		}
	});
        
        ebook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				catg="gen";
				String doc="E-books";
				Ed.putString("MAC",macid );              
	        	Ed.putString("Category",catg );
	        	Ed.putString("DocType",doc );
	        	Ed.commit();
	        	Intent obj=new Intent(v.getContext(),SubjectList.class);
				startActivityForResult(obj,0);
			}
		});
        
        notice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				catg="gen";
				String doc="Notification";
				Ed.putString("MAC",macid );              
	        	Ed.putString("Category",catg );
	        	Ed.putString("DocType",doc );
	        	Ed.commit();
				
				Intent obj=new Intent(v.getContext(),Notificationlist.class);
				startActivityForResult(obj,0);
			}
		});
      }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }
    public void authenicate()
    {
    	//macid="00:c3:e2:33:7d:6a";
    	macid="00:c3:b1:12:70:4a";
    	  sp=getSharedPreferences("Login", 0);
          Ed=sp.edit();
    	String url="StudCloudServer/jsp/macautho.jsp?mac="+macid;
		String xml = XMLfunctions.getXML(url);
    	Document doc = XMLfunctions.XMLfromString(xml);
       
        NodeList nodes = doc.getElementsByTagName("Result");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
    				
       	Element e = (Element)nodes.item(0);
       	String ss=XMLfunctions.getValue(e,"Status");
        String std=XMLfunctions.getValue(e,"standard");
       	String studname=XMLfunctions.getValue(e,"studentname");
       
        sb.append(ss);
        sb1.append(std);
        sb2.append(studname);
        
		String text = sb.toString();
		String text1 = sb1.toString();
		String text2 = sb2.toString();
    	
		if(text.equals("Success"))
	        {
	        	
	        	Ed.putString("MAC",macid );  
	        	Ed.putString("Std",text1 );
	        	Ed.putString("student",text2 );
	        	
	        	Ed.commit();
	        	Toast.makeText(this, "MAC Authenication Successful", Toast.LENGTH_LONG).show();
	        	 
	        	
	        }
	        if(text.equals("Fail"))
	        {
	        	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	    				this);
	     
	    			// set title
	    			alertDialogBuilder.setTitle("School On Cloud");
	     
	    			// set dialog message
	    			alertDialogBuilder
	    				.setMessage("MAc Not registered!")
	    				.setCancelable(false)
	    				.setPositiveButton("Close",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						// if this button is clicked, close
	    						// current activity
	    						MainPage.this.finish();
	    					}
	    				  });
	    					     
	    				// create alert dialog
	    				AlertDialog alertDialog = alertDialogBuilder.create();
	     
	    				// show it
	    				alertDialog.show();
	        }
    }
}

package com.studcloud;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class StdActivity extends Activity {
	String macid;
	String stdcode=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stdwise);
		
		SharedPreferences sp1=this.getSharedPreferences("Login",0);
        String studentname=sp1.getString("student", null);
        stdcode=sp1.getString("Std", null);
		
		ImageButton first=(ImageButton)findViewById(R.id.btnfirst);
		ImageButton second=(ImageButton)findViewById(R.id.btnsecond);
		ImageButton third=(ImageButton)findViewById(R.id.btnthird);
		ImageButton fourth=(ImageButton)findViewById(R.id.btnfourth);
		
		Button back=(Button)findViewById(R.id.btnBack);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "Shivaji01.ttf");
	    back.setTypeface(font);
	    
	    	WifiManager wifimgr=(WifiManager)getSystemService(Context.WIFI_SERVICE);
	        WifiInfo winfo=wifimgr.getConnectionInfo();
	        macid=winfo.getMacAddress();
	        Toast.makeText(this, "MAC Id: "+macid,Toast.LENGTH_LONG).show();
	       // macid="00:c3:b1:12:70:4a";
	        
		first.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(stdcode.equals("01"))
	        	{
	        	Toast.makeText(v.getContext(), "Success", Toast.LENGTH_LONG).show();
	        	Intent intent=new Intent(v.getContext(),MainMenuActivity.class);
				startActivityForResult(intent, 0);
	        	}
	        	else
	        	{
	        		Toast.makeText(v.getContext(), "Invalid Standard Selection", Toast.LENGTH_LONG).show();
	        	}
				
				
				// TODO Auto-generated method stub http://192.168.1.18:8080/
				/*String url="StudCloudServer/jsp/macautho.jsp?mac="+macid;
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
        	        	SharedPreferences sp=getSharedPreferences("Login", 0);
        	        	SharedPreferences.Editor Ed=sp.edit();
        	        	Ed.putString("MAC",macid );              
        	        	Ed.putString("student",text2 );
        	        	Ed.commit();
        	        	Toast.makeText(v.getContext(), "Success", Toast.LENGTH_LONG).show();
        	        	Intent intnt=new Intent(v.getContext(),MainMenuActivity.class);
        				startActivityForResult(intnt, 0);
        	        	if(std.equals("01"))
        	        	{
        	        	Toast.makeText(v.getContext(), "Success", Toast.LENGTH_LONG).show();
        	        	Intent intent=new Intent(v.getContext(),MainMenuActivity.class);
        				startActivityForResult(intent, 0);
        	        	}
        	        	else
        	        	{
        	        		Toast.makeText(v.getContext(), "Invalid Standard Selection", Toast.LENGTH_LONG).show();
        	        	}

        				
        	        }
        	        if(text.equals("Fail"))
        	        {
        	        	Toast.makeText(v.getContext(), "MAC Not Registered", Toast.LENGTH_LONG).show();
        	        	Intent obj=new Intent(v.getContext(),StdActivity.class);
        				startActivityForResult(obj,0);
        	        }*/
				
				
			}
		});
		

		second.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(stdcode.equals("02"))
	        	{
	        	Toast.makeText(v.getContext(), "Success", Toast.LENGTH_LONG).show();
	        	Intent intent=new Intent(v.getContext(),MainMenuActivity.class);
				startActivityForResult(intent, 0);
	        	}
	        	else
	        	{
	        		Toast.makeText(v.getContext(), "Invalid Standard Selection", Toast.LENGTH_LONG).show();
	        	}
			
			}
		});
		
		third.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(stdcode.equals("03"))
	        	{
	        	Toast.makeText(v.getContext(), "Success", Toast.LENGTH_LONG).show();
	        	Intent intent=new Intent(v.getContext(),MainMenuActivity.class);
				startActivityForResult(intent, 0);
	        	}
	        	else
	        	{
	        		Toast.makeText(v.getContext(), "Invalid Standard Selection", Toast.LENGTH_LONG).show();
	        	}
			
			}
		});
		
		fourth.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(stdcode.equals("04"))
	        	{
	        	Toast.makeText(v.getContext(), "Success", Toast.LENGTH_LONG).show();
	        	Intent intent=new Intent(v.getContext(),MainMenuActivity.class);
				startActivityForResult(intent, 0);
	        	}
	        	else
	        	{
	        		Toast.makeText(v.getContext(), "Invalid Standard Selection", Toast.LENGTH_LONG).show();
	        	}
			
			}
		});
		
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intnt=new Intent(v.getContext(),MainPage.class);
				startActivityForResult(intnt, 0);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.std, menu);
		return true;
	}

}

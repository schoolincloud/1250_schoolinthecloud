package com.studcloud;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ShowEbooks extends Activity {
	SharedPreferences sp;
	SharedPreferences.Editor Ed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_ebooks);
		 sp=this.getSharedPreferences("Login",0);
		 
		 Bundle b=this.getIntent().getExtras();
		 final String filename=b.getString("filename");
		 final String doctype=sp.getString("DocType", null);  
	     final String subject=sp.getString("Subject", null);  
	     final String std=sp.getString("Std", null);
	     Button show = (Button) findViewById(R.id.btnebkshw);
	     //String url="http://192.168.1.18:8080/StudCloudServer/uploads/Admin/"+doctype+"/Standard/"+std+"/"+subject+"/"+filename;
	    // Toast.makeText(this, "url:"+url, Toast.LENGTH_LONG).show();
	    /* WebView webView = (WebView) findViewById(R.id.webViewpdf);
	     webView.getSettings().setJavaScriptEnabled(true);
		 webView.getSettings().setAllowFileAccess(true);
		 webView.loadUrl( "http://docs.google.com/gview?embedded=true&url="+url);*/
		 
				//url="http://192.168.1.18:8080/StudCloudServer/uploads/Admin/"+doctype+"/Standard/"+std+"/"+subject+"/"+filename;
				
				// "http://docs.google.com/gview?embedded=true&url="
			//	Uri uri=Uri.parse(url);
				//Intent intent = new Intent(Intent.ACTION_VIEW,uri);
		         
		         //intent.setDataAndType(url, "application/pdf");
		         //intent.setDataAndType(Uri.parse(url),"application/pdf");
		        // startActivity(intent);
		         
		         
		         show.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String dirName= "mnt/sdcard/Download/";
						
						  File images = new File(dirName+filename); 
						         
						 Intent intent1 = new Intent();
				         intent1.setAction(Intent.ACTION_VIEW);
				         Uri uri1 = Uri.fromFile(images.getAbsoluteFile());
				         intent1.setDataAndType(uri1, "application/pdf");
				         startActivity(intent1);
					}
				});
		 
			
		
		
	   
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_images, menu);
		return true;
	}

}

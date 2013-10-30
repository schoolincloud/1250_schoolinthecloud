package com.studcloud;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ShowImages extends Activity {
	SharedPreferences sp;
	SharedPreferences.Editor Ed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_images);
		 sp=this.getSharedPreferences("Login",0);
		 
		 Bundle b=this.getIntent().getExtras();
		 final String filename=b.getString("filename");
		 final String doctype=sp.getString("DocType", null);  
	     final String subject=sp.getString("Subject", null);  
	     final String std=sp.getString("Std", null);
		//Button back=(Button)findViewById(R.id.btnback);
		ImageView jpgView = (ImageView)findViewById(R.id.imageViewShw);
		 URL url;
			try {
				url = new URL("http://192.168.1.18:8080/StudCloudServer/uploads/Admin/"+doctype+"/Standard/"+std+"/"+subject+"/"+filename);
			    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
				jpgView.setImageBitmap(bmp);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			Toast.makeText(this, ""+filename+doctype+subject+std, Toast.LENGTH_LONG).show();
			 
		
		   
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_images, menu);
		return true;
	}

}

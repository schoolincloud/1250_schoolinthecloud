package com.studcloud;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		SharedPreferences sp1=this.getSharedPreferences("Login",0);
        String studentname=sp1.getString("student", null);   
		ImageButton subject=(ImageButton)findViewById(R.id.imgBsub);
		ImageButton diet=(ImageButton)findViewById(R.id.imgBdiet);
		TextView studname=(TextView)findViewById(R.id.textViewName);
		//ImageButton health=(ImageButton)findViewById(R.id.imgBhealth);
		studname.setText(studentname);
		//naava :
		TextView mview=(TextView)findViewById(R.id.textViewtitle);
		mview.setText("naava :");
        Typeface font = Typeface.createFromAsset(getAssets(), "Shivaji01.ttf");
        mview.setTypeface(font);
        
		subject.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent obj=new Intent(v.getContext(),SubjectList.class);
				startActivityForResult(obj,0);
			}
		});
		diet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent obj=new Intent(v.getContext(),Vidoelist.class);
				startActivityForResult(obj,0);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

}

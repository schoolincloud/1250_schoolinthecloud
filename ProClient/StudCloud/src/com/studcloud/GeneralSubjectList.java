package com.studcloud;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class GeneralSubjectList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gensublist);
		ListView gensublist=(ListView)findViewById(R.id.listViewGenSub);
		String subjectlist[]=new String[]{""};
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.general_subject_list, menu);
		return true;
	}

}

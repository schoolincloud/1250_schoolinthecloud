package com.studcloud;



import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PopupMenulist extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.popupmenulist_layout);
		 /*final String menu[]=new String[]{"Books","Images","Video"};
		    ListView menulist=(ListView)findViewById(R.id.listViewMenu);
		    ArrayAdapter<String> adapter;
	        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,menu);
	        menulist.setAdapter(adapter);
	        menulist.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					
				}
			});*/
	        
	}
	



}

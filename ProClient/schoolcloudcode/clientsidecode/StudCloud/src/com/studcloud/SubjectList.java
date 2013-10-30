package com.studcloud;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class SubjectList extends Activity {
	PopupWindow popupWin;
	 SharedPreferences sp1;
	SharedPreferences.Editor Ed;
	//The "x" and "y" position of the "Show Button" on screen.
	Point p;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_list_main);
		//http://192.168.1.18:8080/
		
	     
		
		 sp1=this.getSharedPreferences("Login",0);
		 Ed=sp1.edit();
	     final String cat=sp1.getString("Category", null);  
	      final String doctype=sp1.getString("DocType", null);  
	     String std;  
	     if(cat.equals("gen"))
	     {
	    	std="00"; 
	    	Ed.putString("Std",std );
	    	Ed.commit();
	     }
	     else
	     {
	    	 std=sp1.getString("Std", null);  
	    	 Ed.putString("Std",std );
		     Ed.commit();
	     }
		 String url="StudCloudServer/jsp/subjectlist.jsp?cat="+cat+"&std="+std;
	      
	        String xml = XMLfunctions.getXML(url);
	       	Document doc = XMLfunctions.XMLfromString(xml);
	   
	       	NodeList nodes = doc.getElementsByTagName("Subject");
	       	Toast.makeText(this, ""+nodes.getLength(), Toast.LENGTH_LONG).show();
	        final String subjects[]=new String[nodes.getLength()];
	
			  for (int i = 0,q=1; i< nodes.getLength(); i++,q++) 
				{							
		        	Element e = (Element)nodes.item(i);
					subjects[i]=new String();
				    subjects[i]=XMLfunctions.getValue(e, "subject");
				}
		       
		        ListView subjectlist=(ListView)findViewById(R.id.listViewSubject);
		        ArrayAdapter<String> adapter;
		        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,subjects);
		       subjectlist.setAdapter(adapter); 
		       
		       subjectlist.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View v,
						int pos, long arg3) {
					// TODO Auto-generated method stub
					Ed.putString("Subject",subjects[pos]);
		        	Ed.commit();
		        	
		        	if(cat.equals("gen"))
		        	{
		        		if (doctype.equals("E-books")) 
		    			{
		    				Toast.makeText(v.getContext(), "You have selected :"+doctype, Toast.LENGTH_SHORT).show();
		    	        	Intent intent=new Intent(v.getContext(),Bookslist.class);
		    				startActivityForResult(intent, 0);
		    			}
		    			if (doctype.equals("Images")) 
		    			{
		    				Toast.makeText(v.getContext(), "You have selected :"+doctype, Toast.LENGTH_SHORT).show();
		    	        	Intent intent=new Intent(v.getContext(),Imageslist.class);
		    				startActivityForResult(intent, 0);
		    			}
		    			if (doctype.equals("Video")) 
		    			{
		    				Toast.makeText(v.getContext(), "You have selected :"+doctype, Toast.LENGTH_SHORT).show();
		    	        	Intent intent=new Intent(v.getContext(),Vidoelist.class);
		    				startActivityForResult(intent, 0);
		    			}
		        	}
		        	
		        	else{
					  //Open popup window
				       if (p != null)
				       showPopup(SubjectList.this, p);
				       
		        	}
					
				}
		    	   
			});
	        
	}
	
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {

	   int[] location = new int[2];
	   ListView subjectlist=(ListView)findViewById(R.id.listViewSubject);
	   subjectlist.getLocationOnScreen(location);
	  // Button button = (Button) findViewById(R.id.btnShow);

	   // Get the x, y location and store it in the location[] array
	   // location[0] = x, location[1] = y.
	  // button.getLocationOnScreen(location);

	   //Initialize the Point with x, and y positions
	   p = new Point();
	   p.x = location[0];
	   p.y = location[1];
	}

	// The method that displays the popup.
	
	private void showPopup(final Activity context, Point p) {
	   int popupWidth = 200;
	   int popupHeight = 200;

	   // Inflate the popup_layout.xml
	   LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
	   LayoutInflater layoutInflater = (LayoutInflater) context
	     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  
	   View layout = layoutInflater.inflate(R.layout.popupmenulist_layout, viewGroup);

	   // Creating the PopupWindow
	   final PopupWindow popup = new PopupWindow(context);
	   popup.setContentView(layout);
	   popup.setWidth(popupWidth);
	   popup.setHeight(popupHeight);
	   popup.setFocusable(true);
	   
	   
	   final String menu[]=new String[]{"E-books","Images","Video"};
	    ListView menulist=(ListView)layout.findViewById(R.id.listViewMenu);
	    ArrayAdapter<String> adapter;
       adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,menu);
       menulist.setAdapter(adapter);
       
       menulist.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View v, int position,
				long arg3) {
			// TODO Auto-generated method stub
			String category=menu[position];
			
			Ed.putString("DocType",category );              
        	Ed.commit();
			if (category.equals("E-books")) 
			{
				Toast.makeText(v.getContext(), "You have selected :"+category, Toast.LENGTH_SHORT).show();
	        	Intent intent=new Intent(v.getContext(),Bookslist.class);
				startActivityForResult(intent, 0);
			}
			if (category.equals("Images")) 
			{
				Toast.makeText(v.getContext(), "You have selected :"+category, Toast.LENGTH_SHORT).show();
	        	Intent intent=new Intent(v.getContext(),Imageslist.class);
				startActivityForResult(intent, 0);
			}
			if (category.equals("Video")) 
			{
				Toast.makeText(v.getContext(), "You have selected :"+category, Toast.LENGTH_SHORT).show();
	        	Intent intent=new Intent(v.getContext(),Vidoelist.class);
				startActivityForResult(intent, 0);
			}
			
		}
    	   
	});
       
	   // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
	   int OFFSET_X = 30;
	   int OFFSET_Y = 30;

	   // Clear the default translucent background
	 //  popup.setBackgroundDrawable(new BitmapDrawable());

	   // Displaying the popup at the specified location, + offsets.
	   popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

	   // Getting a reference to Close button, and close the popup when clicked.
	   Button close = (Button) layout.findViewById(R.id.close);
	   close.setOnClickListener(new OnClickListener() {

	     @Override
	     public void onClick(View v) {
	       popup.dismiss();
	     }
	   });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.subject_list, menu);
		return true;
	}

}

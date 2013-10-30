package com.studcloud;



import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Vidoelist extends ListActivity {
	String[] data=new String[]{"Aaa.pdf","BBB.pdf"};
	
	ArrayAdapter<String> adapter;
    int clickCounter=0;
    ArrayList<String> listItems=new ArrayList<String>();
    private File[] imagelist;
    String[] videolist;
    List<RowItem> rowItems;
    SharedPreferences sp;
	SharedPreferences.Editor Ed;
   
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookslist);
		//setListAdapter(new MyAdapter(this, data));
		 sp=this.getSharedPreferences("Login",0);
		 Ed=sp.edit();
		 
		 String doctype=sp.getString("DocType", null);  
	     String subject=sp.getString("Subject", null);  
	     String std=sp.getString("Std", null);  
	     
	     String url="StudCloudServer/jsp/filelist.jsp?std="+std+"&subjectcode="+subject+"&filedoctype="+doctype;
	      
	        String xml = XMLfunctions.getXML(url);
	       	Document doc = XMLfunctions.XMLfromString(xml);
	   
	       	NodeList nodes = doc.getElementsByTagName("File");
	       	Toast.makeText(this, "Video Qty: "+nodes.getLength(), Toast.LENGTH_LONG).show();
	       	videolist=new String[nodes.getLength()];
	
			  for (int i = 0,q=1; i< nodes.getLength(); i++,q++) 
				{							
		        	Element e = (Element)nodes.item(i);
		        	videolist[i]=new String();
		        	videolist[i]=XMLfunctions.getValue(e, "filename");
				}
	
	/*File images = Environment.getExternalStorageDirectory(); 
    imagelist = images.listFiles(new FilenameFilter(){ 
        public boolean accept(File dir, String name) 
        { 
            return ((name.endsWith(".wmv"))); 
        } 
    });
    videolist = new String[imagelist.length];
    for(int i = 0;i<imagelist.length;i++)
    {
    	videolist[i] = imagelist[i].getName();
    }
    */
    rowItems = new ArrayList<RowItem>();
    for (int i = 0; i < videolist.length; i++) {
        RowItem item = new RowItem(R.drawable.video_icon, videolist[i], videolist[i]);
        rowItems.add(item);
    }
    this.setListAdapter(new  CustomListViewAdapter(this, R.layout.listrow, rowItems));
    
}

@Override
protected void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    Ed.putString("Filename",videolist[position]);
	Ed.commit();
    Toast.makeText(v.getContext(), "You have selected :"+videolist[position], Toast.LENGTH_LONG).show();
	Bundle b=new Bundle();
	b.putString("filename", videolist[position]);
	Intent obj=new Intent(v.getContext(),MediaPlayerActivity.class);
	obj.putExtras(b);
	startActivityForResult(obj,0);
    /*PackageManager packageManager = getPackageManager();
     Intent testIntent = new Intent(Intent.ACTION_VIEW);
     testIntent.setType("application/pdf");
     List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
     if (list.size() > 0 && imagelist[(int) id].isFile()) {
         Intent intent = new Intent();
         intent.setAction(Intent.ACTION_VIEW);
         Uri uri = Uri.fromFile(imagelist[(int) id].getAbsoluteFile());
         intent.setDataAndType(uri, "application/pdf");
         startActivity(intent);
     }*/

}

	



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bookslist, menu);
		return true;
	}

}

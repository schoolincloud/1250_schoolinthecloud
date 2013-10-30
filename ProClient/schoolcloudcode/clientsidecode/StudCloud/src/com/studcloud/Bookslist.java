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

public class Bookslist extends ListActivity {
	String[] data=new String[]{"Aaa.pdf","BBB.pdf"};
	
	ArrayAdapter<String> adapter;
    int clickCounter=0;
    ArrayList<String> listItems=new ArrayList<String>();
    private File[] imagelist;
    String[] pdflist;
    List<RowItem> rowItems;
    SharedPreferences sp;
	SharedPreferences.Editor Ed;
	 String doctype,subject,std;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookslist);
		//setListAdapter(new MyAdapter(this, data));
		 sp=this.getSharedPreferences("Login",0);
		 Ed=sp.edit();
		 
		 doctype=sp.getString("DocType", null);  
	     subject=sp.getString("Subject", null);  
	     std=sp.getString("Std", null);  
	     
	     String url="StudCloudServer/jsp/filelist.jsp?std="+std+"&subjectcode="+subject+"&filedoctype="+doctype;
	      
	        String xml = XMLfunctions.getXML(url);
	       	Document doc = XMLfunctions.XMLfromString(xml);
	   
	       	NodeList nodes = doc.getElementsByTagName("File");
	       	
	       	Toast.makeText(this, "Books Qty :"+nodes.getLength(), Toast.LENGTH_LONG).show();
	        pdflist=new String[nodes.getLength()];
	
			  for (int i = 0,q=1; i< nodes.getLength(); i++,q++) 
				{							
		        	Element e = (Element)nodes.item(i);
		        	pdflist[i]=new String();
		        	pdflist[i]=XMLfunctions.getValue(e, "filename");
				}
	
			  String dirName= "mnt/sdcard/Download";
			
			  File images = new File(dirName);
	//File images = Environment.getExternalStorageDirectory(); 
    imagelist = images.listFiles(new FilenameFilter(){ 
        public boolean accept(File dir, String name) 
        { 
            return ((name.endsWith(".pdf"))); 
        } 
    });
   /* pdflist = new String[imagelist.length];
    for(int i = 0;i<imagelist.length;i++)
    {
        pdflist[i] = imagelist[i].getName();
    }*/
    
    rowItems = new ArrayList<RowItem>();
    for (int i = 0; i < pdflist.length; i++) {
        RowItem item = new RowItem(R.drawable.pdficons, pdflist[i], pdflist[i]);
        rowItems.add(item);
    }
    this.setListAdapter(new  CustomListViewAdapter(this, R.layout.listrow, rowItems));
    
}

@Override
protected void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    
    Ed.putString("Filename",pdflist[position]);
	Ed.commit();
    Toast.makeText(v.getContext(), "You have selected :"+pdflist[position], Toast.LENGTH_LONG).show();
	Bundle b=new Bundle();
	b.putString("filename", pdflist[position]);
	download(doctype, std, subject, pdflist[position]);
	Intent obj=new Intent(v.getContext(),ShowEbooks.class);
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

public void download(String doc,String stdr,String sub,String fname)
{
	 String url="http://192.168.1.18:8080/StudCloudServer/uploads/Admin/"+doc+"/Standard/"+stdr+"/"+sub+"/"+fname;
     Toast.makeText(this, "url:"+url, Toast.LENGTH_SHORT).show();
     Uri uri=Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW,uri);
      
      //intent.setDataAndType(url, "application/pdf");
      //intent.setDataAndType(Uri.parse(url),"application/pdf");
      startActivity(intent);
}
	



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bookslist, menu);
		return true;
	}

}

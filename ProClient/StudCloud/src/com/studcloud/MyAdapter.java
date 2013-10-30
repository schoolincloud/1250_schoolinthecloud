package com.studcloud;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
public class MyAdapter extends ArrayAdapter<String>{
 final Context c;
 String[]data=new String[]{};
	public MyAdapter(Context context, String[] d) {
		super(context,R.layout.listrow,d);
		// TODO Auto-generated constructor stub
		c=context;
		data=d;
		
		
	}
	

	public View getView(int pos,View v, ViewGroup p)
	{
		LayoutInflater inflator=(LayoutInflater)c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
		View obj1=inflator.inflate (R.layout.listrow, p, false);
		TextView txtv=(TextView) obj1.findViewById(R.id.title);
		ImageView imgv=(ImageView) obj1.findViewById(R.id.imgIcon);
		
		txtv.setText(data[pos]);
		imgv.setImageResource(R.drawable.pdficons);
		
		
		return obj1;
	}
	

}

package com.studcloud;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore.Video.Thumbnails;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
public class CustomVdListViewAdapter extends ArrayAdapter<RowItem>{
 
 Context context;
 
 public CustomVdListViewAdapter(Context context, int resourceId,
         List<RowItem> items) {
     super(context, resourceId, items);
     this.context = context;
 }

 /*private view holder class*/
 private class ViewHolder {
     ImageView imageView;
     TextView txtTitle;
     TextView txtDesc;
 }

 public View getView(int position, View convertView, ViewGroup parent) {
     ViewHolder holder = null;
     RowItem rowItem = getItem(position);

     LayoutInflater mInflater = (LayoutInflater) context
             .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
     if (convertView == null) {
         convertView = mInflater.inflate(R.layout.v_listrow, null);
         holder = new ViewHolder();
         holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
         holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
         holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
         convertView.setTag(holder);
     } else
         holder = (ViewHolder) convertView.getTag();

     holder.txtDesc.setText(rowItem.getDesc());
     holder.txtTitle.setText(rowItem.getTitle());
    
     Bitmap bmThumbnail;
     bmThumbnail = ThumbnailUtils.createVideoThumbnail("/sdcard/"+rowItem.getTitle(), Thumbnails.MICRO_KIND);
     holder.imageView.setImageBitmap(bmThumbnail);

     return convertView;
 }

}

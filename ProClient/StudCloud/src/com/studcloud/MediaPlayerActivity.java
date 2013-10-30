package com.studcloud;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Video.Thumbnails;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MediaPlayerActivity extends Activity{
	
	SharedPreferences sp;
	SharedPreferences.Editor Ed;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player);
        
        sp=this.getSharedPreferences("Login",0);
		 
		 Bundle b=this.getIntent().getExtras();
		 final String filename=b.getString("filename");
		 final String doctype=sp.getString("DocType", null);  
	     final String subject=sp.getString("Subject", null);  
	     final String std=sp.getString("Std", null);
        
	     String videoUrl="http://192.168.1.46:8080/StudCloudServer/uploads/Admin/"+doctype+"/Standard/"+std+"/"+subject+"/"+filename;
	    
        VideoView videoView = (VideoView) findViewById(R.id.videoViewShow);
        //ImageView imgv=(ImageView)findViewById(R.id.imageView1);
       //Bitmap bmThumbnail;
       // bmThumbnail = ThumbnailUtils.createVideoThumbnail(videoUrl,MediaStore.Video.Thumbnails.MINI_KIND);
      //  imgv.setImageBitmap(bmThumbnail);
      //Use a media controller so that you can scroll the video contents
      //and also to pause, start the video.
      MediaController mediaController = new MediaController(this); 
      mediaController.setAnchorView(videoView);
      videoView.setMediaController(mediaController);
      videoView.setVideoURI(Uri.parse(videoUrl));
      videoView.start();
    

}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.media_player, menu);
		return true;
	}

	

}

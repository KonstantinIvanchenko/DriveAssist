package com.example.myapp;

import java.io.Console;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v4.app.NavUtils;

public class ShowVideoActivity extends Activity implements SurfaceHolder.Callback {

	private static final int CAMERA_REQUEST = 1888;
	private SurfaceView surfView;
	private SurfaceHolder surfHolder;
	
	private Camera camera;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_video);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        surfView = (SurfaceView)this.findViewById(R.id.surfaceView1);
        surfHolder = surfView.getHolder();
        surfHolder.addCallback(this);
    }
    
    private Camera getCameraInstance(){
    	Camera c = null;
    	
    	try{
    		c = Camera.open();
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return c;
    }

    @Override
    public void onPause(){
    	super.onPause();
    	
    	camera.stopPreview();
    	camera.release();
    }

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder holder)
	{
		 camera = getCameraInstance();
	        
	        try 
	        {
				camera.setPreviewDisplay(surfHolder);
				
				camera.startPreview();
				
				camera.setPreviewCallback(new Camera.PreviewCallback() {
					
					public void onPreviewFrame(byte[] data, Camera camera) 
					{
						/* Здесь вызов обработки кадра */
					}
				});
			} 
	        catch (IOException e) 
			{
				e.printStackTrace();
			}
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) 
	{
    	camera.stopPreview();
    	camera.release();
	}
}

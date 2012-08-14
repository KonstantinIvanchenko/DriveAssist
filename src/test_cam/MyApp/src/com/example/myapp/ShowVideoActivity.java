package com.example.myapp;

import java.io.Console;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Camera;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v4.app.NavUtils;

class TopView extends View {
    
	private Paint p;
	
    public TopView(Context context) {
        super(context);
        
        p = new Paint();

    	p.setColor(android.graphics.Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
    	
      canvas.drawLine(0, 0, 150, 150, p);
    } 
}

public class ShowVideoActivity extends Activity implements SurfaceHolder.Callback {

	private static final int CAMERA_REQUEST = 1888;
	private SurfaceView surfView;
	private SurfaceHolder surfHolder;
	
	private TopView topView;
	
	private Camera camera;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_video);
        
        topView = new TopView(this);
        
        addContentView( topView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT) );
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        surfView = (SurfaceView)this.findViewById(R.id.surfaceView1);
        surfHolder = surfView.getHolder();
        surfHolder.addCallback(this);
    }
    
    private Camera getCameraInstance(){
    	Camera c = null;
    	
    	try{
    		c = Camera.open(0);
    		
    		Log.i("cam", Integer.toString(Camera.getNumberOfCameras()));
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
	        
		if(camera != null)
		{
		    try 
		    {
				camera.setPreviewDisplay(surfHolder);
				
				camera.startPreview();
				
				/*camera.setPreviewCallback(new Camera.PreviewCallback() {
					
					public void onPreviewFrame(byte[] data, Camera camera) 
					{
					}
				});
				*/
				
			} 
		    catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) 
	{
    	camera.stopPreview();
    	camera.release();
	}
}

package com.fuanxt.alert;

import com.fuanxt.alert.R;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class ChatHeadService extends Service {

	  private WindowManager windowManager;
	  private ImageView chatHead;

	  @Override public IBinder onBind(Intent intent) {
	    // Not used
	    return null;
	  }

	  @Override public void onCreate() {
	    super.onCreate();
	    Log.i("msg", "Service onCreate--->");  
	    windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

	    chatHead = new ImageView(this);
	    chatHead.setImageResource(R.drawable.dola1);

	    Log.i("mag","alert");
	    final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
	        WindowManager.LayoutParams.WRAP_CONTENT,
	        WindowManager.LayoutParams.WRAP_CONTENT,
	        WindowManager.LayoutParams.TYPE_PHONE,
	        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
	        PixelFormat.TRANSLUCENT);

	    params.gravity = Gravity.TOP | Gravity.LEFT;
	    params.x = 0;
	    params.y = 100;

	    windowManager.addView(chatHead, params);
	    
	    chatHead.setOnTouchListener(new View.OnTouchListener() {
	    	  private int initialX;
	    	  private int initialY;
	    	  private float initialTouchX;
	    	  private float initialTouchY;

	    	  @Override public boolean onTouch(View v, MotionEvent event) {
	    	    switch (event.getAction()) {
	    	      case MotionEvent.ACTION_DOWN:
	    	        initialX = params.x;
	    	        initialY = params.y;
	    	        initialTouchX = event.getRawX();
	    	        initialTouchY = event.getRawY();
	    	        return true;
	    	      case MotionEvent.ACTION_UP:
	    	        return true;
	    	      case MotionEvent.ACTION_MOVE:
	    	        params.x = initialX + (int) (event.getRawX() - initialTouchX);
	    	        params.y = initialY + (int) (event.getRawY() - initialTouchY);
	    	        windowManager.updateViewLayout(chatHead, params);
	    	        return true;
	    	    }
	    	    return false;
	    	  }
	    	});

	  }

	  @Override
	  public void onDestroy() {
	    super.onDestroy();
	    if (chatHead != null) windowManager.removeView(chatHead);
	  }
	  
	  
	}
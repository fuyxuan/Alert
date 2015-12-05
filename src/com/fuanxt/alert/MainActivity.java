package com.fuanxt.alert;

import com.fuanxt.alert.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	
	private static boolean isON = false;
	private Button btnSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		btnSwitch = (Button)findViewById(R.id.btn_switch);
		
		btnSwitch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				if(!isON){
					isON= true;
					btnSwitch.setText("OFF");
					startService(new Intent(MainActivity.this,ChatHeadService.class)); 
					
				}else{
					isON= false;
					btnSwitch.setText("ON");
					stopService(new Intent(MainActivity.this,ChatHeadService.class));  
				}
				Log.i("msg","MainActivity");
				
			}
		});
		
	}

}

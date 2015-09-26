package com.avnet.storepal.beacon;

import org.altbeacon.beacon.BeaconManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.avnet.storepal.R;

public class MonitoringActivity extends Activity  {
	protected static final String TAG = "MonitoringActivity";
    private BeaconManager beaconManager;
    private final Integer SPLASH_TIME_OUT = 1000;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		Log.i("", "Entering Monitoring Activity-----------------onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monitoring);
		verifyBluetooth();
        
        BeaconReferenceApplication1 beaconApp = (BeaconReferenceApplication1) getApplication();
        
        
        if(beaconApp.rangingCalledFromButtonClick){
        	Log.i("", "Calling Ranging Activity********************************");
        	beaconApp.rangingCalledFromButtonClick = false;
        	 new Handler().postDelayed(new Runnable() {
                 @Override
                 public void run() {
                     // This method will be executed once the timer is over
                     // Start your app main activity
                     Intent i = new Intent(MonitoringActivity.this, RangingActivity.class);
                     startActivity(i);
                     // close this activity
                     finish();
                 }
             }, SPLASH_TIME_OUT);
        }
        
       
	}
	
	public void onRangingClicked(View view) {
		Intent myIntent = new Intent(this, RangingActivity.class);
		this.startActivity(myIntent);
	}

//    @Override
//    public void onResume() {
//        super.onResume();
//        ((BeaconReferenceApplication1) this.getApplicationContext()).setMonitoringActivity(this);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        ((BeaconReferenceApplication1) this.getApplicationContext()).setMonitoringActivity(null);
//    }

	private void verifyBluetooth() {

		try {
			if (!BeaconManager.getInstanceForApplication(this).checkAvailability()) {
				final AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Bluetooth not enabled");			
				builder.setMessage("Please enable bluetooth in settings and restart this application.");
				builder.setPositiveButton(android.R.string.ok, null);
				builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						finish();
			            System.exit(0);					
					}					
				});
				builder.show();
			}			
		}
		catch (RuntimeException e) {
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Bluetooth LE not available");			
			builder.setMessage("Sorry, this device does not support Bluetooth LE.");
			builder.setPositiveButton(android.R.string.ok, null);
			builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

				@Override
				public void onDismiss(DialogInterface dialog) {
					finish();
		            System.exit(0);					
				}
				
			});
			builder.show();
			
		}
		
	}	

//    public void logToDisplay(final String line) {
//    	runOnUiThread(new Runnable() {
//    	    public void run() {
//    	    	EditText editText = (EditText)MonitoringActivity.this
//    					.findViewById(R.id.monitoringText);
//       	    	editText.append(line+"\n");            	    	    		
//    	    }
//    	});
//    }

}
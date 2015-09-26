/**
 * 
 */
package com.avnet.storepal.beacon;

import com.avnet.storepal.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author admin
 *
 */
public class VibratingToast {
	
	public int imageId;
	
	public VibratingToast(Context context,Activity activity, CharSequence text, String sectionName, int duration, PositionCustom positionCustom) {
	    //super(context);
	    Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
	    v.vibrate(300); 
	    //super.setGravity(Gravity.TOP|Gravity.LEFT, (int)positionCustom.getX(), (int)positionCustom.getY());
	    //super.makeText(context, text, duration).show();
	    
	    int resID = activity.getResources().getIdentifier(sectionName.toLowerCase() , "drawable", activity.getPackageName());
	    
	    ImageView image = new ImageView(activity);
        image.setImageResource(resID);
        
        
	    AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
        //builder1.setTitle("Promo");
        builder1.setMessage(text);
        builder1.setCancelable(true);
        /*builder1.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });*/
        builder1.setNegativeButton("Dismiss",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder1.setView(image);

        AlertDialog alert11 = builder1.create();
        alert11.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        alert11.show();
	    try {
	        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	        Ringtone r = RingtoneManager.getRingtone(context, notification);
	        r.play();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
	
package utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;

public class AndroidUtils {

	public static void showToast(Context context, String content, String duration){
		int durationInt = Toast.LENGTH_LONG;
		if ("long".equals(duration)){
			durationInt = Toast.LENGTH_LONG;
		}
		else if ("short".equals(duration)){
			durationInt = Toast.LENGTH_SHORT;
		}
		Toast.makeText(context, content, durationInt).show();
		context = null;
	}
	
	public static void showToastAt(Context context, String content, String duration, int gravity, int xOffSet, int yOffSet){
		int durationInt = Toast.LENGTH_LONG;
		if ("long".equals(duration)){
			durationInt = Toast.LENGTH_LONG;
		}
		else if ("short".equals(duration)){
			durationInt = Toast.LENGTH_SHORT;
		}
		Toast toast = Toast.makeText(context, content, durationInt);
		toast.setGravity(gravity, xOffSet, yOffSet);
		toast.show();
		context = null;
	}
	
	public static int px2dip(Context context, float pxValue){   
//      final float scale = context.getResources().getDisplayMetrics().density;   
//      return (int)(pxValue / scale + 0.5f);   
      
      DisplayMetrics dm = new DisplayMetrics();  
      ((Activity) context).getWindowManager().getDefaultDisplay()  
              .getMetrics(dm);  
      return (int) Math.ceil(((pxValue * 160) / dm.densityDpi)); 
	} 
	
	public static int dip2px(Context context, float dipValue){   
//      final float scale = context.getResources().getDisplayMetrics().density;   
//      return (int)(dipValue * scale + 0.5f);   
      
      DisplayMetrics dm = new DisplayMetrics();  
      ((Activity) context).getWindowManager().getDefaultDisplay()  
              .getMetrics(dm);  
      return (int) Math.ceil(dipValue * dm.density); 
	}  
	
}

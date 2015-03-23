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
	


	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率 从pix转化成 SP
	 * @param context
	 * @param px
	 * @return
	 */
	public static int pixelsToSp(Context context, Float px) {
	    float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
	    return (int)(px/scaledDensity+0.5f);
	}
	
	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * @param context
	 * @param spValue
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

}

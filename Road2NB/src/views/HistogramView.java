package views;

import com.xu.road2nb.R;

import android.R.color;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class HistogramView extends View {
	
	private float rb;
	private Paint white_paint;
	private Paint blue_paint;
	private Paint gray_paint;
	
	private Resources res;
	private int grayColor = 0x5faaaaaa; // 灰色
	private int blueColor = 0xff00ffff; // 蓝色
	private float interval_left_right;

	public HistogramView(Context context, AttributeSet attrs) {
		super(context, attrs);
		res = context.getResources();
//		rb = res.getDimension(R.dimen.historyscore_tb);
		rb = 30.0f;
		init();
	}

	private void init(){
		
		white_paint = new Paint();
		white_paint.setColor(color.white);
		white_paint.setStrokeWidth(rb * 0.1f);
		white_paint.setAntiAlias(true);
		white_paint.setStyle(Style.FILL);
		
		blue_paint = new Paint();
		blue_paint.setColor(blueColor);
		blue_paint.setStrokeWidth(rb * 0.1f);
		blue_paint.setAntiAlias(true);
		blue_paint.setStyle(Style.FILL);
		
		gray_paint = new Paint();
		gray_paint.setColor(grayColor);
		gray_paint.setStrokeWidth(rb * 0.1f);
		gray_paint.setAntiAlias(true);
		gray_paint.setStyle(Style.FILL);
		
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, (int) rb/3));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		drawRect(canvas);
	}
	
	private void drawRect(Canvas canvas){
		Rect rect = new Rect();
		rect.set(0, 0, getWidth(), getHeight());
		canvas.drawRect(rect, gray_paint);
		
		Rect rect1 = new Rect();
		rect1.set(0, 0, getWidth()/2, getHeight());
		canvas.drawRect(rect1, blue_paint);
	}
}

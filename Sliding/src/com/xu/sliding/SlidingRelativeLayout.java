package com.xu.sliding;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlidingRelativeLayout extends RelativeLayout {

	private final static String TAG = "SlidingRelativeLayou";
	private Bitmap dragBitmap = null;
	private Resources res;
	
	private Context mContext;

	private TextView tvSlidingBlock;
//	private TextView tvDstBlock;

	public SlidingRelativeLayout(Context context) {
		super(context);
		mContext = context;
		res = context.getResources();
		initDragBitmap();
	}

	public SlidingRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		mContext = context;
		res = context.getResources();
		initDragBitmap();
	}

	public SlidingRelativeLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		res = context.getResources();
		initDragBitmap();
	}
	
	// 場宎趙芞迍蚹奀腔Bitmap勤砓 萸笢 羲宎迍眳
	private void initDragBitmap() {
		if (dragBitmap == null)
			dragBitmap = BitmapFactory.decodeResource(res,
					R.drawable.slide_block);
	}

//	private int srcMotionx;
	private int removeMotionX;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "Down Down Down");
//			srcMotionx = (int) event.getX();
			removeMotionX = (int) event.getX();
			return handleActionDown(event);
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "Move Move Move");
			removeMotionX = (int) event.getX();
			invalidate();
			return true;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "Up Up Up");
			return handleActionUp(event);
		}

		return super.onTouchEvent(event);

	}

	/**
	 * 瓚剿岆瘁賑善硌隅弇离 甜釬峈賦旰
	 * 
	 * @param event
	 */
	private boolean handleActionUp(MotionEvent event) {
		boolean isHit = false;
		Rect mRectBlock = new Rect();
//		tvDstBlock.getHitRect(mRectBlock);
		int x = (int) event.getX();
		int y = (int) event.getY();
		if (mRectBlock.contains(x, y)) {
			isHit = true;
		}
		return isHit;
	}

	/**
	 * 瓚剿岆瘁岆萸僻婓賑輸奻
	 * 
	 * @param event
	 */
	private boolean handleActionDown(MotionEvent event) {
		boolean isHit = false;
		Rect mRectBlock = new Rect();
		tvSlidingBlock.getHitRect(mRectBlock);
		int x = (int) event.getX() ;
		int y = (int) event.getY();
		if (mRectBlock.contains(x, y)) {
			isHit = true;
//			tvSlidingBlock.setVisibility(View.INVISIBLE);
		}
		return isHit;
	}
	
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawRemovingBlock(canvas);
	}

	private void drawRemovingBlock(Canvas canvas) {
		int drawX = removeMotionX - dragBitmap.getWidth();;
//		int drawY = tvSlidingBlock.getTop();
		int drawY = 0;
		if (drawX < 0)
			drawX = 5;
		Log.d(TAG, "drawX:  "+ drawX+"   drawY:  "+drawY);
		canvas.drawBitmap(dragBitmap, drawX, drawY, null);
	}
	
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		// 该控件主要判断是否处于滑动点击区域。滑动时 处于INVISIBLE(不可见)状态，不滑动时处于VISIBLE(可见)状态
		tvSlidingBlock = (TextView) findViewById(R.id.slider_icon);
//		tvDstBlock = (TextView) findViewById(R.id.slider_dst);
	}

}

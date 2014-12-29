package com.xu.sliding;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlidingRelativeLayout extends RelativeLayout {

	private final static String TAG = "SlidingRelativeLayou";
	private final static int UNLOCK = 1;
	private Bitmap dragBitmap = null;
	private Resources res;

	private Context mContext;

	private TextView tvSlidingBlock;
	private TextView tvSlidingBgText;
	private Handler mainHandler;
	private OnUnlockListener listener;

	private boolean isBacking = false;

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

	private void initDragBitmap() {
		if (dragBitmap == null)
			dragBitmap = BitmapFactory.decodeResource(res,
					R.drawable.slide_block);
	}

	private int removeMotionX;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (!isBacking) {
				Log.d(TAG, "Down Down Down");
				// srcMotionx = (int) event.getX();
				removeMotionX = (int) event.getX();
				return handleActionDown(event);
			} else
				return false;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "Move Move Move");
			removeMotionX = (int) event.getX();
			invalidate();
			return true;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "Up Up Up");
			handleActionUp(event);
			return true;
		}

		return super.onTouchEvent(event);

	}

	private void handleActionUp(MotionEvent event) {
		int x = (int) event.getX();
		boolean unLock = Math.abs(x - getRight()) <= 30;
		if (unLock) {
			// int what = UNLOCK;
			// mainHandler.sendEmptyMessage(what);
			listener.onUnlock();
		} else {
			int leftDistance = x - this.getLeft();
			if (leftDistance > 0) {
				isBacking = true;
				mainHandler.postDelayed(DrawBackTask, BACK_DURATION);
			}
		}
	}

	// 回退动画时间间隔值
	private static int BACK_DURATION = 20; // 20ms
	// 水平方向前进速率
	private static float VE_HORIZONTAL = 1.0f; // 0.1dip/ms

	private Runnable DrawBackTask = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			removeMotionX = (int) (removeMotionX - (BACK_DURATION * VE_HORIZONTAL));
			invalidate();
			boolean isBack2Start = removeMotionX - getLeft() <= 0;
			System.out.println("layout getLeft():  " + getLeft());
			System.out.println("removeMotionX:  " + removeMotionX);
			if (isBack2Start) {
				isBacking = false;
				initLayout();
			} else {
				mainHandler.postDelayed(DrawBackTask, BACK_DURATION);
			}
		}
	};

	private boolean handleActionDown(MotionEvent event) {
		boolean isHit = false;
		Rect mRectBlock = new Rect();
		tvSlidingBlock.getHitRect(mRectBlock);
		int x = (int) event.getX();
		int y = (int) event.getY();
		if (mRectBlock.contains(x, y)) {
			isHit = true;
			tvSlidingBgText.setVisibility(View.INVISIBLE);
		}
		return isHit;
	}

	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawRemovingBlock(canvas);
	}

	private void drawRemovingBlock(Canvas canvas) {
		int drawX = removeMotionX - dragBitmap.getWidth();
		;
		// int drawY = tvSlidingBlock.getTop();
		int drawY = 0;
		if (drawX < 0)
			drawX = 0;
		Log.d(TAG, "drawX:  " + drawX + "   drawY:  " + drawY);
		canvas.drawBitmap(dragBitmap, drawX, drawY, null);
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		// 该控件主要判断是否处于滑动点击区域。滑动时 处于INVISIBLE(不可见)状态，不滑动时处于VISIBLE(可见)状态
		tvSlidingBlock = (TextView) findViewById(R.id.slider_icon);
		tvSlidingBgText = (TextView) findViewById(R.id.slider_bar_bg_text);
		// tvDstBlock = (TextView) findViewById(R.id.slider_dst);
	}

	public void setMainHandler(Handler handler) {
		this.mainHandler = handler;
	}

	public interface OnUnlockListener {

		public void onUnlock();
	}

	public void setOnUnlockListener(OnUnlockListener l) {
		this.listener = l;
	}

	public void initLayout() {
		removeMotionX = 0;
		tvSlidingBgText.setVisibility(View.VISIBLE);
		invalidate();
	}
}

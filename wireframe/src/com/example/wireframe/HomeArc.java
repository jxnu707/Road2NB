package com.example.wireframe;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;

/**
 * 圆弧计分
 * @author Administrator
 *
 */
public class HomeArc extends View { 

	private Paint paint_black, paint_white;
	private RectF rectf;
	private float tb;
	private int blackColor = 0x70000000; // 底黑色
	private int whiteColor = 0xddffffff; // 白色
	private int score;
	private float arc_y = 0f;
	private int score_text;

	public HomeArc(Context context, int score) {
		super(context);
		init(score);
	}

	public void init(int score) {
		this.score = score;
		Resources res = getResources();
		tb = res.getDimension(R.dimen.historyscore_tb);

		paint_black = new Paint();
		paint_black.setAntiAlias(true);
		paint_black.setColor(blackColor);
		paint_black.setStrokeWidth(tb * 0.2f);
		paint_black.setStyle(Style.STROKE);

		paint_white = new Paint();
		paint_white.setAntiAlias(true);
		paint_white.setColor(whiteColor);
		paint_white.setTextSize(tb*6.0f);
		paint_white.setStrokeWidth(tb * 0.2f);
		paint_white.setTextAlign(Align.CENTER);
		paint_white.setStyle(Style.STROKE);

		rectf = new RectF();
		rectf.set(tb * 0.5f, tb * 0.5f, tb * 18.5f, tb * 18.5f);

		setLayoutParams(new LayoutParams((int) (tb * 19.5f), (int) (tb * 19.5f)));

		/**注册一个回调函数，当一个视图树将要绘制时调用这个回调函数。
		　　 *参数 listener    将要被添加的回调函数
		　　 *异常 IllegalStateException       如果isAlive() 返回false
		 　　*/
		this.getViewTreeObserver().addOnPreDrawListener(
				new OnPreDrawListener() {
					public boolean onPreDraw() {
						new thread();
						getViewTreeObserver().removeOnPreDrawListener(this);
						return false;
					}
				});
	}

	protected void onDraw(Canvas c) {
		super.onDraw(c);
		//画圆弧 false表示不包含圆心 
	  /*oval :指定圆弧的外轮廓矩形区域。
		startAngle: 圆弧起始角度，单位为度。顺时针正，逆时针负。
		sweepAngle: 圆弧扫过的角度，顺时针方向，单位为度。
		useCenter: 如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形。
		paint: 绘制圆弧的画板属性，如颜色，是否填充等。*/
		c.drawArc(rectf, -90, 360, false, paint_black);
		c.drawArc(rectf, -90, arc_y, false, paint_white);
		c.drawText("" + score_text, tb * 9.7f, tb * 11.0f, paint_white);
	}

	class thread implements Runnable {
		private Thread thread;
		private int statek;
		int count;

		public thread() {
			thread = new Thread(this);
			thread.start();
		}

		public void run() {
			while (true) {
				switch (statek) {
				case 0:
					try {
						Thread.sleep(200);
						statek = 1;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case 1:
					try {
						Thread.sleep(15);
						arc_y += 3.6f;
						score_text++;
						count++;
						postInvalidate();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
				if (count >= score)
					break;
			}
		}
	}

}

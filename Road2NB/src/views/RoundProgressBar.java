package views;

import com.xu.road2nb.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class RoundProgressBar extends View {
	
	/** 
     * 画笔对象的引用 
     */  
    private Paint paint;  
      
    /** 
     * 圆环的颜色 
     */  
    private int roundColor;  
      
    /** 
     * 圆环进度的颜色 
     */  
    private int roundProgressColor;  
      
    /** 
     * 中间进度百分比的字符串的颜色 
     */  
    private int textColor;  
      
    /** 
     * 中间进度百分比的字符串的字体 
     */  
    private float textSize;  
      
    /** 
     * 圆环的宽度 
     */  
    private float roundWidth;  
      
    /** 
     * 最大进度 
     */  
    private int max;  
      
    /** 
     * 当前进度 
     */  
    private int progress ;  
    /** 
     * 是否显示中间的进度 
     */  
    private boolean textIsDisplayable;  
      
    /** 
     * 进度的风格，实心或者空心 
     */  
    private int style;  
      
    public static final int STROKE = 0;  
    public static final int FILL = 1;  

	public RoundProgressBar(Context context) {
		this(context,null);
	}

	public RoundProgressBar(Context context, AttributeSet attrs) {
		this(context,attrs,0);
	}
	
	public RoundProgressBar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		paint = new Paint();
		
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,  
                R.styleable.RoundProgressBar);  
		//获取自定义属性
		roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);  
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);  
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.GREEN);  
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 15);  
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 15);  
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);  
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);  
        style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);  
        
        mTypedArray.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//最外层圆环
		float cx = getWidth()/2;
		float cy = cx;
		float radius = cx - roundWidth/2;
//		float radius = cx ;
		
		paint.setStyle(Style.STROKE);
		paint.setColor(roundColor);
		paint.setStrokeWidth(roundWidth);//圈环宽度
		paint.setAntiAlias(true);
		
		canvas.drawCircle(cx, cy, radius, paint);
		
		//进度值 文字
		paint.setStrokeWidth(0);
		paint.setTextSize(textSize);
		paint.setColor(textColor);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		
		int percent = (int)(((float)progress/(float)max)*100);
		float textWidth = paint.measureText(percent + "%");
		if( percent != 0 && textIsDisplayable){
			canvas.drawText(percent + "%", cx - textWidth/2, cy + textSize/4, paint);//文字的左下角（x,y）
		}
		else if(percent == 0 && textIsDisplayable){
			paint.setColor(roundColor);
			canvas.drawText(percent + "%", cx - textWidth/2, cy + textSize/4, paint);//文字的左下角（x,y）
		}
		
		/** 
         * 画圆弧 ，画圆环的进度 
         */  
          
        //设置进度是实心还是空心  
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度  
        paint.setColor(roundProgressColor);  //设置进度的颜色  
        paint.setStrokeJoin(Paint.Join.ROUND);//圆角圆弧
        paint.setStrokeCap(Paint.Cap.ROUND);
        RectF oval = new RectF(cx - radius, cy - radius, cx + radius, cx + radius);  //用于定义的圆弧的形状和大小的界限  
//        RectF oval = new RectF(cx, cy , cx, cx );  //用于定义的圆弧的形状和大小的界限  
          
        switch (style) {  
        case STROKE:{  
            paint.setStyle(Paint.Style.STROKE);  
            canvas.drawArc(oval, -60, 360 * progress / max, false, paint);  //根据进度画圆弧  
            break;  
        }  
        case FILL:{  
            paint.setStyle(Paint.Style.FILL_AND_STROKE);  
            if(progress !=0)  
                canvas.drawArc(oval, -60, 360 * progress / max, true, paint);  //根据进度画圆弧  
            break;  
        }  
        }  
	}
	
	 public synchronized int getMax() {  
	        return max;  
	    }  
	  
	    /** 
	     * 设置进度的最大值 
	     * @param max 
	     */  
	    public synchronized void setMax(int max) {  
	        if(max < 0){  
	            throw new IllegalArgumentException("max not less than 0");  
	        }  
	        this.max = max;  
	    }  
	  
	    /** 
	     * 获取进度.需要同步 
	     * @return 
	     */  
	    public synchronized int getProgress() {  
	        return progress;  
	    }  
	  
	    /** 
	     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步 
	     * 刷新界面调用postInvalidate()能在非UI线程刷新 
	     * @param progress 
	     */  
	    public synchronized void setProgress(int progress) {  
	        if(progress < 0){  
	            throw new IllegalArgumentException("progress not less than 0");  
	        }  
	        if(progress > max){  
	            progress = max;  
	        }  
	        if(progress <= max){  
	            this.progress = progress;  
	            postInvalidate();  
	        }  
	          
	    }  
	      
	      
	    public int getCricleColor() {  
	        return roundColor;  
	    }  
	  
	    public void setCricleColor(int cricleColor) {  
	        this.roundColor = cricleColor;  
	    }  
	  
	    public int getCricleProgressColor() {  
	        return roundProgressColor;  
	    }  
	  
	    public void setCricleProgressColor(int cricleProgressColor) {  
	        this.roundProgressColor = cricleProgressColor;  
	    }  
	  
	    public int getTextColor() {  
	        return textColor;  
	    }  
	  
	    public void setTextColor(int textColor) {  
	        this.textColor = textColor;  
	    }  
	  
	    public float getTextSize() {  
	        return textSize;  
	    }  
	  
	    public void setTextSize(float textSize) {  
	        this.textSize = textSize;  
	    }  
	  
	    public float getRoundWidth() {  
	        return roundWidth;  
	    }  
	  
	    public void setRoundWidth(float roundWidth) {  
	        this.roundWidth = roundWidth;  
	    }  
	  
	
}

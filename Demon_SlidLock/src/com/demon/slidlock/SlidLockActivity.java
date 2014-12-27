package com.demon.slidlock;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlidLockActivity extends Activity {
	
	private static String TAG = "QINZDLOCK";
	private SliderRelativeLayout sliderLayout = null;
	private ImageView imgView_getup_arrow; // 动画图片
	private AnimationDrawable animArrowDrawable = null;
    private Context mContext = null ;
    public static int MSG_LOCK_SUCESS = 1;
    
    
    //////////////////////////////
   private static final int msgKey1 = 1;
//    private TextView mTime;
    ///////////////////////////////
//    MyBatteryReceiver mbr = null;
    
    
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=SlidLockActivity.this;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//设置全屏
      // 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.main);
        
        ///////显示时间/////////
//        mTime = (TextView) findViewById(R.id.mytime);
//        new TimeThread().start();
        
        
        ///////////////////////////////////
//        mbr = new MyBatteryReceiver();
//        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED); 
//		registerReceiver(mbr, filter);
		
		////////////////////////////////////////////
        initViews();
//        startService(new Intent(SlidLockActivity.this, ZdLockService.class));
        sliderLayout.setMainHandler(mHandler);
    }


	private void initViews()
	{
		// TODO Auto-generated method stub
		sliderLayout=(SliderRelativeLayout) findViewById(R.id.slider_layout);
//		imgView_getup_arrow=(ImageView) findViewById(R.id.getup_arrow);
//		animArrowDrawable=(AnimationDrawable) imgView_getup_arrow.getBackground();
	}
	
//	protected void onResume() {
//		super.onResume();
//		//设置动画
//		mHandler.postDelayed(AnimationDrawableTask, 300);  //开始绘制动画
//	}
	@Override
	protected void onPause() {
		super.onPause();
//		animArrowDrawable.stop();
	}

	protected void onDestory(){
		super.onDestroy();
	}
	//通过延时控制当前绘制bitmap的位置坐标
//	private Runnable AnimationDrawableTask = new Runnable(){
//		
//		public void run(){
//			animArrowDrawable.start();
//			mHandler.postDelayed(AnimationDrawableTask, 300);
//		}
//	};
private Handler mHandler =new Handler (){
		
		public void handleMessage(Message msg){
			
			Log.i(TAG, "handleMessage :  #### " );
			
			if(MSG_LOCK_SUCESS == msg.what)
//				unregisterReceiver(mbr);
				finish(); // 锁屏成功时，结束我们的Activity界面
		}
	};
	//屏蔽掉Home键
	public void onAttachedToWindow() {
		//this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
	    super.onAttachedToWindow();
    }
	//屏蔽掉Back键
	public boolean onKeyDown(int keyCode ,KeyEvent event){
		
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
			return true ;
		else
			return super.onKeyDown(keyCode, event);
		
	}
	
	//////////////////////////////////
//	 public class TimeThread extends Thread {
//	        @Override
//	        public void run () {
//	            do {
//	                try {
//	                    Thread.sleep(1000);
//	                    Message msg = new Message();
//	                    msg.what = msgKey1;
//	                    myHandler.sendMessage(msg);
//	                }
//	                catch (InterruptedException e) {
//	                    e.printStackTrace();
//	                }
//	            } while(true);
//	        }
//	    }
	
//	 private Handler myHandler = new Handler() {
//	        @Override
//	        public void handleMessage (Message msg) {
//	            super.handleMessage(msg);
//	            switch (msg.what) {
//	                case msgKey1:
//	                    long sysTime = System.currentTimeMillis();
//	                    CharSequence sysTimeStr = DateFormat.format("hh:mm", sysTime);
////	                    mTime.setText(sysTimeStr);
//	                    break;
//	                
//	                default:
//	                    break;
//	            }
//	        }
//	    };
	    
	    
	    
	    //////////////////////////////////////
//	    private class MyBatteryReceiver extends BroadcastReceiver{
//			@Override
//			public void onReceive(Context context, Intent intent) {		
//				int current = intent.getExtras().getInt("level");		//获得当前电量
//				int total = intent.getExtras().getInt("scale");			
//				int percent = current*100/total;		//计算百分比
////				TextView tv = (TextView)findViewById(R.id.tv);			
////				tv.setText("手机电量为："+percent+"%。");				//设置TextView显示的内容
//			}
//	    }
	
}
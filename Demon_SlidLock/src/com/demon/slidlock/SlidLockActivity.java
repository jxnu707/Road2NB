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
	private ImageView imgView_getup_arrow; // ����ͼƬ
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
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//����ȫ��
      // ���ر���
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.main);
        
        ///////��ʾʱ��/////////
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
//		//���ö���
//		mHandler.postDelayed(AnimationDrawableTask, 300);  //��ʼ���ƶ���
//	}
	@Override
	protected void onPause() {
		super.onPause();
//		animArrowDrawable.stop();
	}

	protected void onDestory(){
		super.onDestroy();
	}
	//ͨ����ʱ���Ƶ�ǰ����bitmap��λ������
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
				finish(); // �����ɹ�ʱ���������ǵ�Activity����
		}
	};
	//���ε�Home��
	public void onAttachedToWindow() {
		//this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
	    super.onAttachedToWindow();
    }
	//���ε�Back��
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
//				int current = intent.getExtras().getInt("level");		//��õ�ǰ����
//				int total = intent.getExtras().getInt("scale");			
//				int percent = current*100/total;		//����ٷֱ�
////				TextView tv = (TextView)findViewById(R.id.tv);			
////				tv.setText("�ֻ�����Ϊ��"+percent+"%��");				//����TextView��ʾ������
//			}
//	    }
	
}
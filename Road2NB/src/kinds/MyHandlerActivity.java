package kinds;

import java.lang.ref.WeakReference;

import com.xu.road2nb.R;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 防止Handler 和 thread 造成的activity内存泄露
 * 1  Handler 和 thread采用静态内部类的定义方式
 * 2  使用WeakReference切断内部类对外部activity的引用隐式持有
 * 3  OnDestroy方法中取消MessageQueue中所有待执行的Runnable 和 Message
 * 
 * @author 21427754
 *
 */
public class MyHandlerActivity extends Activity {
	
	private TextView tv1;
	private Button bn1;
	
	private int i = 0;
	
	private final MyHandler mHandler = new MyHandler(this);
	
	/*private static Handler mHandler = new Handler(){
		
		WeakReference<MyHandlerActivity> activity = new WeakReference<MyHandlerActivity>(r);
		
		

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				tv1.setText(i+"");
				i++;
				Log.e("lynch", String.valueOf(i));
				break;

			default:
				break;
			}
		}
		
	};*/
	
	private static class MyHandler extends Handler{
		WeakReference<MyHandlerActivity> mActivity;
		
		public MyHandler(MyHandlerActivity mActivity){
			this.mActivity = new WeakReference<MyHandlerActivity>(mActivity);
		}
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
//			MyHandlerActivity activity = mActivity.get();
			if (mActivity != null && mActivity.get() != null){
				switch (msg.what) {
				case 0:
					mActivity.get().tv1.setText(mActivity.get().i+"");
					mActivity.get().i++;
					Log.e("lynch", String.valueOf(mActivity.get().i));
					break;

				default:
					break;
				}
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_handler_layout);
		tv1 = (TextView) findViewById(R.id.textView1);
		bn1 = (Button) findViewById(R.id.button1);
		
		bn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UpdateUIThread updateUIThread = new UpdateUIThread(MyHandlerActivity.this);
//				UpdateUIThread updateUIThread = new UpdateUIThread();
				updateUIThread.start();
			}
		});
		
		
	}

	private static class UpdateUIThread extends Thread{

		WeakReference<MyHandlerActivity> mActivity;
		
		public UpdateUIThread(MyHandlerActivity activity){
			this.mActivity = new WeakReference<MyHandlerActivity>(activity);
		}
		
		@Override
		public void run() {
			try {
//				MyHandlerActivity activity = mActivity.get();
				if (mActivity !=null && mActivity.get() != null){
					while(mActivity.get().i<30){
						Message msg = mActivity.get().mHandler.obtainMessage();
						msg.what = 0;
						msg.arg1 = mActivity.get().i;
						mActivity.get().mHandler.sendMessageDelayed(msg, 1000);
						Thread.sleep(1000);
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
/*	private  class UpdateUIThread extends Thread{

		@Override
		public void run() {
			try {
//				MyHandlerActivity activity = mActivity.get();
					while(true){
						Message msg = mHandler.obtainMessage();
						msg.what = 0;
						msg.arg1 = i;
						mHandler.sendMessageDelayed(msg, 1000);
						Thread.sleep(1000);
					}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}*/
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mHandler.removeCallbacksAndMessages(null);
	}

	
	
}

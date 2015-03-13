package kinds;

import com.xu.road2nb.R;

import services.ICalculate;
import services.MyCalcAidlService;
import utils.AndroidUtils;
import views.fragments.MyContentFragMent;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyAidlActivity extends Activity implements OnClickListener{

	private static String TAG = "Aidl Activity";
	
	private static final int REFRESH_VIEW = 0;
	
	private ICalculate mICalcAidl;
	
	private EditText et_num_1;
	private EditText et_num_2;
	private TextView tv_result;
	
	private Button bn_bind;
	private Button bn_unbind;
	private Button bn_add;
	private Button bn_min;
	
	
	private int calculate_result;
	
	private Handler mHander = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case REFRESH_VIEW:
				tv_result.setText(calculate_result+"");
				break;

			default:
				break;
			}
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aidlactiviy_layout);
		initViews();
	}
	
	private void initViews(){
		et_num_1 = (EditText) findViewById(R.id.et_input1);
		et_num_2 = (EditText) findViewById(R.id.et_input2);
		tv_result = (TextView) findViewById(R.id.tv_calculate_result);
		
		bn_add = (Button) findViewById(R.id.bn_add_result);
		bn_min = (Button) findViewById(R.id.bn_min_result);
		bn_bind = (Button) findViewById(R.id.bn_bindservice);
		bn_unbind = (Button) findViewById(R.id.bn_unbindservice);
		 
		bn_add.setOnClickListener(this);
		bn_min.setOnClickListener(this);
		bn_bind.setOnClickListener(this);
		bn_unbind.setOnClickListener(this);
	}

	ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onServiceDisconnected");
			mICalcAidl = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onServiceConnected");
			//Cast an IBinder object into an services.ICalculate interface, generating a proxy if needed.
			//通过根据aidl文件自动编译的ICalculate.java(gen路径下)文件，获得一个service的代理（一个ICalcAidl接口的实例）
			mICalcAidl = ICalculate.Stub.asInterface(service);
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bn_bindservice:
			Log.d(TAG, "bind Button invoked.");
			Intent bind_intent = new Intent();
			System.out.println(ICalculate.class.getName());
			//隐式intent(setAction方式) 用startService是不安全的
//			bind_intent.setAction("services.MyCalcAidlService");
			bind_intent.setClass(this, MyCalcAidlService.class);
			startService(bind_intent);
			bindService(bind_intent, conn, BIND_AUTO_CREATE);
			break;
			
		case R.id.bn_unbindservice:
			Log.d(TAG, "unbind Button invoked.");
			unbindService(conn);
			break;
			
		case R.id.bn_add_result:
			int add_a =Integer.valueOf(et_num_1.getText().toString());
			int add_b =Integer.valueOf(et_num_2.getText().toString());
			try {
				if (mICalcAidl != null){
					Log.d(TAG, "add invoked.");
					calculate_result = mICalcAidl.add(add_a, add_b);
					mHander.sendEmptyMessage(REFRESH_VIEW);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				AndroidUtils.showToast(MyAidlActivity.this, "远程服务调用失败，请重试", "long");
			}
			break;
			
		case R.id.bn_min_result:
			int min_a =Integer.valueOf(et_num_1.getText().toString());
			int min_b =Integer.valueOf(et_num_2.getText().toString());
			try {
				if (mICalcAidl != null){
					Log.d(TAG, "min invoked.");
					calculate_result = mICalcAidl.min(min_a,min_b);
					mHander.sendEmptyMessage(REFRESH_VIEW);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				AndroidUtils.showToast(MyAidlActivity.this, "远程服务调用失败，请重试", "long");
			}
			break;

		default:
			break;
		}
		
	}
	
	
}

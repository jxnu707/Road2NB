package kinds;

import services.MyBindService;
import services.MyBindService.MyBinder;

import com.xu.road2nb.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyBindServiceActivity extends Activity {
	
	private MyBindService mBindService = null;
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_bindserviceactivity_layout);
		btn = (Button) findViewById(R.id.button1);
		Intent intent = new Intent (MyBindServiceActivity.this,MyBindService.class);
		bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				mBindService.serviceMethod();
			}
		});
	}

	private ServiceConnection mServiceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			System.out.println("DisConnected");
			mBindService = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			System.out.println("connected");
			mBindService = ((MyBinder)service).getService();
			mBindService.serviceMethod();
		}
	};
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("onStart: ");
		if (mBindService != null) System.out.println("here");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		System.out.println("onRestart: ");
		if (mBindService != null) System.out.println("here");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("onResume: ");
		if (mBindService != null) System.out.println("here");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("onPause: ");
		if (mBindService != null) System.out.println("here");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("onStop: ");
		if (mBindService != null) System.out.println("here");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(mServiceConnection);
		System.out.println("onDestroy:");
		if (mBindService != null) System.out.println("here");
	}
	
	

}

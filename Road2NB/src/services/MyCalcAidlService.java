package services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyCalcAidlService extends Service {

	private static String TAG = "Aidl Service";
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		//返回个一个IBinder作为这个服务的代理类 客户端通过这个IBinder与服务经行通信
		Log.d(TAG, "onBind");
		return mBinder;
	}

	//实现aidl接口中定义的接口 作为对外接口暴露给client
	private final ICalculate.Stub mBinder = new ICalculate.Stub() {
		
		@Override
		public int min(int a, int b) throws RemoteException {
			// TODO Auto-generated method stub
			
			return a - b;
		}
		
		@Override
		public int add(int a, int b) throws RemoteException {
			// TODO Auto-generated method stub
			return a + b;
		}
	};



	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreate");
		super.onCreate();
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}


	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onUnbind");
		return super.onUnbind(intent);
	}


	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onRebind");
		super.onRebind(intent);
	}

	private class MyBroadCastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}

package services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyBindService extends Service {
	
	private MyBinder mBinder = new MyBinder() ;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	public class MyBinder extends Binder{
		public MyBindService getService(){
			return MyBindService.this;
		}
	} 
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("service onCreate");
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}



	public void serviceMethod(){
		System.out.println("serciveMethod invoked");
	}
}

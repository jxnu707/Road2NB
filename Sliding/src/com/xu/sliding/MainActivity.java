package com.xu.sliding;

import com.xu.sliding.SlidingRelativeLayout.OnUnlockListener;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity implements OnUnlockListener,OnClickListener{
	
	private SlidingRelativeLayout sRL;
	private TextView tvBar;
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//…Ë÷√»´∆¡
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		
	}

	public void initViews(){
		mHandler = new Handler();
		
		tvBar = (TextView) findViewById(R.id.showBar);
		tvBar.setOnClickListener(this);
		sRL = (SlidingRelativeLayout) findViewById(R.id.sliding_layout);
		sRL.setOnUnlockListener(this);
		sRL.setMainHandler(mHandler);
	}

	@Override
	public void onUnlock() {
		// TODO Auto-generated method stub
		System.out.println("it's unLock!");
		sRL.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		sRL.initLayout();
		sRL.setVisibility(View.VISIBLE);
	}

}

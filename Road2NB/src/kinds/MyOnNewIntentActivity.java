package kinds;

import main.MainActivity;
import utils.AndroidUtils;

import com.xu.road2nb.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 用Intent action 实现跳转需要在manifest中静态设置此activity的intent—filter
 * 
 * @author 21427754
 * 
 */
public class MyOnNewIntentActivity extends Activity implements OnClickListener {

	private final String TAG = "com.xu.kinds.MyOnNewIntentActivity";

	private Button bn1;
	private Button bn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myonnewintent_layout);

		initViews();

		Intent intent = getIntent();
		// 同时在onCreate和onNewIntent中调用 以防系统finish该activity
		showIntentData(intent);
		String content = "get intent from onCreate().";
		String duration = "long";
		AndroidUtils.showToastAt(MyOnNewIntentActivity.this, content, duration,
				Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0,
				AndroidUtils.dip2px(MyOnNewIntentActivity.this, 100));
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		// 把新的intent重新绑定给这个activity
		setIntent(intent);
		showIntentData(intent);
		String content = "get intent from onNewIntent().";
		String duration = "long";
		AndroidUtils.showToastAt(MyOnNewIntentActivity.this, content, duration,
				Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0,
				AndroidUtils.dip2px(MyOnNewIntentActivity.this, 100));
	}

	private void showIntentData(Intent intent) {
		// do something init when this activity show up

	}

	public void initViews() {

		bn1 = (Button) findViewById(R.id.bn_back_1);
		bn2 = (Button) findViewById(R.id.bn_back_2);

		bn1.setOnClickListener(this);
		bn2.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bn_back_1:
			String content = "startActivity()方式  此activity将由系统决定 随机finish() 下次再调用";
			String duration = "long";
			AndroidUtils.showToastAt(MyOnNewIntentActivity.this, content,
					duration, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
					AndroidUtils.dip2px(MyOnNewIntentActivity.this, -100));
			Intent intent = new Intent(MyOnNewIntentActivity.this,
					MainActivity.class);
			startActivity(intent);
			Log.d(TAG, "bn1 clicked");

			break;

		case R.id.bn_back_2:
			String content2 = "finishi()返回方式  ";
			String duration2 = "long";
			AndroidUtils.showToastAt(MyOnNewIntentActivity.this, content2,
					duration2, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
					AndroidUtils.dip2px(MyOnNewIntentActivity.this, -100));
			finish();
			Log.d(TAG, "bn2 clicked");
			break;

		default:
			break;
		}
	}

}

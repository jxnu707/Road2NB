package kinds;

import utils.AndroidUtils;

import com.xu.road2nb.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class MyResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myresultactivity_layout);
		
		Intent intent = new Intent();
		intent.putExtra("result", "msg come from MyResultActivity");
		setResult(Activity.RESULT_OK, intent);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AndroidUtils.showToast(MyResultActivity.this, "Im gonna send msg back to MainActivity.", "long");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onBackPressed();
	}

	


	
}

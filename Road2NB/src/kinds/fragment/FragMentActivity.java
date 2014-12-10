package kinds.fragment;

import com.slidingmenu.lib.app.SlidingActivity;
import com.xu.road2nb.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class FragMentActivity extends SlidingActivity {

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//正文布局
		setContentView(R.layout.fragment_frame_content);
		//设置划出栏的布局 
		setBehindContentView(R.layout.fragment_frame_menu);
	}

	
}

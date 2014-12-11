package kinds.fragment;

import views.slidingfragments.ContentFragment;
import views.slidingfragments.MenuFragment;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import com.xu.road2nb.R;

import android.app.Activity;
import android.app.FragmentTransaction;
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
		
		//给 menu content 添加fragment
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		MenuFragment mMenuFragment = new MenuFragment();
		ContentFragment mContentFragment = ContentFragment.newInstance("Welcome",true);
		ft.replace(R.id.menu, mMenuFragment);
		ft.replace(R.id.content, mContentFragment,"Welcome");
		ft.commit();
		
		//创建侧滑菜单的实例
		SlidingMenu sm = getSlidingMenu();
        sm.setShadowWidth(50);
//      sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffset(60);
        sm.setFadeDegree(0.35f);
        //设置slding menu的几种手势模式
        //TOUCHMODE_FULLSCREEN 全屏模式，在content页面中，滑动，可以打开sliding menu
        //TOUCHMODE_MARGIN 边缘模式，在content页面中，如果想打开slding ,你需要在屏幕边缘滑动才可以打开slding menu
        //TOUCHMODE_NONE 自然是不能通过手势打开啦
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        //使用左上方icon可点，这样在onOptionsItemSelected里面才可以监听到R.id.home
        getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	
}

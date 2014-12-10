package kinds.fragment;

import views.fragments.MyContentFragMent;
import views.fragments.MyContentFragMent.OnClickForContentFragMent;
import views.fragments.MyFragment;

import com.xu.road2nb.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentBreadCrumbs;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MyMainFragmentsActivity extends Activity implements OnClickListener,OnClickForContentFragMent {

	private MyFragment mFragMent;
	// 每个按钮对应显示界面
	private MyContentFragMent mFragMent1;
	private MyContentFragMent mFragMent2;
	private MyContentFragMent mFragMent3;
	// 底栏三按钮
	private TextView tv_bar1;
	private TextView tv_bar2;
	private TextView tv_bar3;
	private TextView tv_bar4;

	private FragmentManager mFragMentManager;
	// 作为Fragment的容器 是一个FrameLayout
	ViewGroup content_container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 静态添加使用的Fragment
		setContentView(R.layout.myfragments_activity_static_layout);
		initViews();
		setFragMentContent(2);

	}

	private void initViews() {
		this.findViewById(R.id.fragment1).findViewById(R.id.fragment_tv1)
				.setVisibility(View.VISIBLE);
		this.findViewById(R.id.fragment2).findViewById(R.id.fragment_tv2)
				.setVisibility(View.VISIBLE);

		content_container = (ViewGroup) findViewById(R.id.content_container);

		tv_bar1 = (TextView) findViewById(R.id.tv_bar1);
		tv_bar2 = (TextView) findViewById(R.id.tv_bar2);
		tv_bar3 = (TextView) findViewById(R.id.tv_bar3);
		tv_bar4 = (TextView) findViewById(R.id.tv_bar4);
		// 获取在这个activity中所有fragment的管理器
		mFragMentManager = getFragmentManager();

		tv_bar1.setOnClickListener(this);
		tv_bar2.setOnClickListener(this);
		tv_bar3.setOnClickListener(this);
		tv_bar4.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_bar1:
			setFragMentContent(0);
			break;

		case R.id.tv_bar2:
			setFragMentContent(1);
			break;

		case R.id.tv_bar3:
			setFragMentContent(2);
			break;
			
		case R.id.tv_bar4:
			Intent intent = new Intent();
			intent.setClass(MyMainFragmentsActivity.this, FragMentActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	private void setFragMentContent(int index) {
		clearContentViews();
		FragmentTransaction ft = mFragMentManager.beginTransaction();
		// 这个fragment不存在就new 再add进container 如果存在 因为之前都是hide起来 所以只要show就行
		// 没有用replace是因为会重复fragment的生命周期 慢
		switch (index) {
		case 0:
			if (mFragMent1 == null) {
//				mFragMent1.fm_tv.setText("Content Fragment 1");
				mFragMent1 = MyContentFragMent.newInstance(index);
				ft.add(R.id.content_container, mFragMent1);
				ft.show(mFragMent1);
			} else {
				ft.show(mFragMent1);
			}
			break;

		case 1:
			if (mFragMent2 == null) {
				mFragMent2 = MyContentFragMent.newInstance(index);
//				mFragMent2.fm_tv.setText("Content Fragment 2");
				ft.add(R.id.content_container, mFragMent2);
				ft.show(mFragMent2);
			} else {
				ft.show(mFragMent2);
			}
			break;

		case 2:
			if (mFragMent3 == null) {
				mFragMent3 = MyContentFragMent.newInstance(index);
//				mFragMent3 = new MyContentFragMent();
//				mFragMent3.setInt_index(index);
//				mFragMent3.fm_tv.setText("Content Fragment 3");
				ft.add(R.id.content_container, mFragMent3);
				ft.show(mFragMent3);
			} else {
				ft.show(mFragMent3);
			}
			break;

		default:
			break;
		}
		// 提交事务
		ft.commit();
	}

	private void clearContentViews() {
		// 开启一个Fragment事务 指的是一系列对fragment的操作
		FragmentTransaction ft = mFragMentManager.beginTransaction();
		// 隐藏所有的fragment
		if (mFragMent1 != null)
			ft.hide(mFragMent1);
		if (mFragMent2 != null)
			ft.hide(mFragMent2);
		if (mFragMent3 != null)
			ft.hide(mFragMent3);
		ft.commit();
	}

	@Override
	public void mOnClick(int viewId) {
		// TODO Auto-generated method stub
		
	}

}

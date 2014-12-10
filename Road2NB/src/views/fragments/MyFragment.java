package views.fragments;

import com.xu.road2nb.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * 对于单个Fragment 是如下方法顺序的生命周期
 * 如果有多个Fragment 各自先单独执行前三个方法（abc abc） 之后的每个方法执行连续两边（dd ee ff...）
 * @author Lin_Xi
 *
 */
@SuppressLint("NewApi")
public class MyFragment extends Fragment {
	
	public static String TAG = "MyFragment";

	@Override
	public void onAttach(Activity activity) {//Fragment和Activity建立关联的时候调用。
		// TODO Auto-generated method stub
		Log.d(TAG, "onAttach() invoked!");
		super.onAttach(activity);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreate() invoked!");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,//为Fragment加载布局时调用。
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreateView() invoked!");
		
		return inflater.inflate(R.layout.fragments_layout, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {//当Activity中的onCreate方法执行完后调用。
		// TODO Auto-generated method stub
		Log.d(TAG, "onActivityCreated() invoked!");
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onStart() {

		Log.d(TAG, "onStart() invoked!");
		super.onStart();
	}
	
	@Override
	public void onResume() {
		Log.d(TAG, "onResume() invoked!");
		super.onResume();
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onPause() invoked!");
		super.onPause();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStop() invoked!");
		super.onStop();
	}
	
	@Override
	public void onDestroyView() {//Fragment中的布局被移除时调用。
		// TODO Auto-generated method stub
		Log.d(TAG, "onDestroyView() invoked!");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDestroy() invoked!");
		super.onDestroy();
	}

	@Override
	public void onDetach() {//Fragment和Activity解除关联的时候调用。
		// TODO Auto-generated method stub
		Log.d(TAG, "onDetach() invoked!");
		super.onDetach();
	}

	

}

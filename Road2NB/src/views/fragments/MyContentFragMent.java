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
import android.widget.TextView;

@SuppressLint("NewApi")
public class MyContentFragMent extends Fragment {

	public static String TAG = "MyContentFragment";
	private TextView fm_tv;
	private OnClickForContentFragMent mListener;
	private int int_index;

	public static MyContentFragMent newInstance(int index){
		MyContentFragMent cf = new MyContentFragMent();
		
		Bundle mBundle = new Bundle();
		mBundle.putInt("index", index);
		cf.setArguments(mBundle);
		return cf;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,// 为Fragment加载布局时调用。
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreateView() invoked!");
		View view = inflater.inflate(R.layout.fragments_dymanic_layout,
				container, false);
//		fm_tv = (TextView) view.findViewById(R.id.fm_tv);
//		fm_tv.setText("Content " + getIndex());

		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		
	}

	public int getInt_index() {
		return int_index;
	}

//	public void setInt_index(int int_index) {
//		this.int_index = int_index;
//		fm_tv.setText("Content " + getInt_index());
//	}

	public int getIndex(){
		int index;
		index = getArguments().getInt("index");
		return index;
	}
	
	//设置回调
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			mListener = (OnClickForContentFragMent) activity;
		} catch (Exception e) {
			new Throwable("This activity must implement OnClickForContentFragMent.");
		}
	}

	

	//定义与Activity交互的回调接口  进而能通过activity.getFragMentManager与其他FragMent交互
	public interface OnClickForContentFragMent{
		public void mOnClick(int viewId);
	}
}

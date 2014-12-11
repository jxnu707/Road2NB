package views.slidingfragments;

import com.xu.road2nb.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ContentFragment extends Fragment {

	private String contentStr;
	private boolean isShow = false;
	
	public static ContentFragment newInstance(String content){
		Bundle b = new Bundle();
		b.putString("content", content);
		return ContentFragment.newInstance(b);
	}
	
	public static ContentFragment newInstance(Bundle bundle){
		ContentFragment f = new ContentFragment();
		
		f.setArguments(bundle);
		return f;
	}
	
	public static ContentFragment newInstance(String content, boolean isShow){
		Bundle b = new Bundle();
		b.putBoolean("isShow", isShow);
		b.putString("content", content);
		
		return ContentFragment.newInstance(b);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//setRetainInstance(true) 在onCreate中设置为true后 当fragment重建时 会跳过onCreate方法 从onAttach->onCrateView 所以不要在onCreate中作初始化操作
		//fragment not in the back stack.
		setRetainInstance(true);
		super.onCreate(savedInstanceState);
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.contentStr = getArguments().getString("content");
		this.isShow = getArguments().getBoolean("isShow");
		
		View view;
		view = inflater.inflate(R.layout.fragments_layout, null);
		TextView tv = (TextView) view.findViewById(R.id.fragment_tv1);
		tv.setVisibility(View.VISIBLE);
		tv.setText(contentStr);
		return view;
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Bundle b = getArguments();
		b.putBoolean("isShow", false);
		setArguments(b);
		
	}

	public boolean isShow(){
		return this.isShow;
	}

}

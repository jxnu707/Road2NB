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
	
	public static ContentFragment newInstance(String content){
		ContentFragment f = new ContentFragment();
		
		Bundle b = new Bundle();
		b.putString("content", content);
		f.setArguments(b);
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.contentStr = getArguments().getString("content");
		
		View view;
		view = inflater.inflate(R.layout.fragments_layout, null);
		TextView tv = (TextView) view.findViewById(R.id.fragment_tv1);
		tv.setVisibility(View.VISIBLE);
		tv.setText(contentStr);
		return view;
	}
	
	

}

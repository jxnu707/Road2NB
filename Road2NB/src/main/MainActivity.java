package main;


import java.util.zip.Inflater;

import utils.AndroidUtils;

import com.xu.road2nb.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private String[] item_Content;
	private Resources res;
	private ListView mList;
	private MyAdatpter mAdatper;
	private static final String BASE_ACTION = "com.xu.";
	private final String TAG = "com.xu.MainActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//无title 且必须在setContentView之前
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_layout);
		//这里initData必须在前 其中有与adapter先关的数据初始化 否则MyAdapter无法得到count总数完成构造
		initData();
		initViews();
		
	}
	
	private void initViews(){
		mList = (ListView) findViewById(R.id.lv_content_list);
		mAdatper = new MyAdatpter();
		mList.setAdapter(mAdatper);
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.d(TAG, "position:  "+ position);
				//eg:  com.xu.onNewIntent
				String tempAction = item_Content[position].trim();
				String action = tempAction.substring((tempAction.lastIndexOf(":") + 2));
				action = BASE_ACTION + action;
				Log.d(TAG, "action:  "+ action);
				
				Intent intent = new Intent(action);
				intent.putExtra("value", "msg come from MainActivity.");
				startActivityForResult(intent, position);
			}
			
		});
	}
	
	private void initData(){
		res = getResources();
		item_Content = res.getStringArray(R.array.item_content_array);
	}
	
	
	//接受由此activity以startActivityForResult方式启动的B activity 返回的数据
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:
			if (resultCode == Activity.RESULT_OK){
				String content = data.getStringExtra("result");
				AndroidUtils.showToast(MainActivity.this, content , "long");
			}
			break;

		default:
			break;
		}
	}



	private class MyAdatpter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return item_Content.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder vh;
			if (convertView == null){
				vh = new ViewHolder();
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.item_layout, null);
				vh.tv_item_content = (TextView) convertView.findViewById(R.id.tv_item_content);
				convertView.setTag(vh);
			}else{
				vh = (ViewHolder) convertView.getTag();
			}
				vh.tv_item_content.setText(item_Content[position]);
				
			
			return convertView;
		}
		
	}
	
	private static class ViewHolder{
		private TextView tv_item_content;
	}
	
	/*private class MyOnClickListener implements OnClickListener{

		private int position_id;
		
		@Override
		public void onClick(View v) {
				Log.d(TAG, "position_id:  "+ position_id);
				//eg:  com.xu.onNewIntent
				String tempAction = item_Content[position_id].trim();
				String action = tempAction.substring((tempAction.lastIndexOf(":") + 2));
				action = BASE_ACTION + action;
				Log.d(TAG, "action:  "+ action);
				
				Intent intent = new Intent(action);
				intent.putExtra("value", "msg come from MainActivity.");
				startActivity(intent);
			}

		public int getPosition_id() {
			return position_id;
		}

		public void setPosition_id(int position_id) {
			this.position_id = position_id;
		}
	}*/
	
}

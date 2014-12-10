package views.slidingfragments;

import com.xu.road2nb.R;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;

public class MenuFragment extends PreferenceFragment implements OnPreferenceClickListener{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//加载这个fragment的布局 从preference的方式
		addPreferencesFromResource(R.xml.menu_preference);
		//给这个preferenceFragment添加监听器
		findPreference("key_checkBox").setOnPreferenceClickListener(this);
		findPreference("key_preference").setOnPreferenceClickListener(this);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		String key = preference.getKey();
		return false;
	}

	
	
}

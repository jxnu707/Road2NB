package views.slidingfragments;

import kinds.fragment.FragMentActivity;

import com.xu.road2nb.R;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;

public class MenuFragment extends PreferenceFragment implements
		OnPreferenceClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 加载这个fragment的布局 从preference的方式
		addPreferencesFromResource(R.xml.menu_preference);
		// 给这个preferenceFragment添加监听器
		findPreference("key_checkBox").setOnPreferenceClickListener(this);
		findPreference("key_preference").setOnPreferenceClickListener(this);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		String key = preference.getKey();
		ContentFragment fragmentCheck;

		fragmentCheck = (ContentFragment) getActivity().getFragmentManager()
				.findFragmentByTag(key);
		if (fragmentCheck != null && fragmentCheck.isShow()) {
			//跳转：  从菜单跳转至content 即菜单消失
			((FragMentActivity)getActivity()).getSlidingMenu().toggle();
			return true;
		}else{
			FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
			ft.replace(R.id.content, fragmentCheck == null?ContentFragment.newInstance(key):fragmentCheck, key);
		}
		
		//anyway,need to toggle the slidingMenu.
		((FragMentActivity)getActivity()).getSlidingMenu().toggle();
		return true;
	}

}

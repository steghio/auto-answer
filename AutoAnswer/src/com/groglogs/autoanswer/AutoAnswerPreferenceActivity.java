package com.groglogs.autoanswer;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;


public class AutoAnswerPreferenceActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	private AutoAnswerNotifier mNotifier;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNotifier = new AutoAnswerNotifier(this);
		mNotifier.updateNotification();
		SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
		sharedPreferences.registerOnSharedPreferenceChangeListener(this);
		addPreferencesFromResource(R.xml.preferences);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onDestroy() {
		getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
		super.onDestroy();
	}
	
	//@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals("enabled")) {
			mNotifier.updateNotification();
		}
	}
}

package com.groglogs.autoanswer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AutoAnswerBootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		AutoAnswerNotifier notifier = new AutoAnswerNotifier(context);
		notifier.updateNotification();
	}

}

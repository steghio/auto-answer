package com.groglogs.autoanswer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class AutoAnswerNotifier {

	private final static int NOTIFICATION_ID = 1;
	
	private Context mContext;
	private NotificationManager mNotificationManager;
	private SharedPreferences mSharedPreferences;
	
	public AutoAnswerNotifier(Context context) {
		mContext = context;
		mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public void updateNotification() {
		if (mSharedPreferences.getBoolean("enabled", false)) {
			this.enableNotification();
		}
		else {
			this.disableNotification();
		}		
	}
	
	@SuppressWarnings("deprecation")
	private void enableNotification() {
		// Intent to call to turn off AutoAnswer
		Intent notificationIntent = new Intent(mContext, AutoAnswerPreferenceActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0);
		
		// Create the notification
		Notification n = new Notification(R.drawable.ic_launcher, null, 0);
		n.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
		n.setLatestEventInfo(mContext, mContext.getString(R.string.notification_title), mContext.getString(R.string.notification_text), pendingIntent);
		mNotificationManager.notify(NOTIFICATION_ID, n);
	}
	
	private void disableNotification() {
		mNotificationManager.cancel(NOTIFICATION_ID);
	}	
}


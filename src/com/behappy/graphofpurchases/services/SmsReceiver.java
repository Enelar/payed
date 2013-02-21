package com.behappy.graphofpurchases.services;

import com.behappy.graphofpurchases.screens.SearchFormActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();
		if (extras == null)
			return;

		Object[] pdus = (Object[]) extras.get("pdus");
		for (int i = 0; i < pdus.length; i++) {
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdus[i]);
			String phone = sms.getOriginatingAddress();
			String body = sms.getMessageBody().toString();

			Intent smsIntent = new Intent(SearchFormActivity.ACTION_NEW_SMS);
			smsIntent.putExtra(SearchFormActivity.KEY_SMS_PHONE, phone);
			smsIntent.putExtra(SearchFormActivity.KEY_SMS_TEXT, body);

			context.sendBroadcast(smsIntent);
		}
	}
}
